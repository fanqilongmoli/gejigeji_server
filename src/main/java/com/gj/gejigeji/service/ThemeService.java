package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.*;
import com.gj.gejigeji.repository.*;
import com.gj.gejigeji.vo.ActionParam;
import com.gj.gejigeji.vo.OkResult;
import com.gj.gejigeji.vo.ThemeBuyParam;
import com.gj.gejigeji.vo.ThemeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ThemeService {

    @Autowired
    ThemeRepository themeRepository;

    @Autowired
    UserThemeRepository userThemeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PropRepository propRepository;

    @Autowired
    UserPropRepository userPropRepository;

    public List<ThemeVo> getAll(ActionParam actionParam) {

        List<ThemeVo> allThemeVo = new ArrayList<>();

        // 获取所有主题
        List<Theme> allTheme = themeRepository.findAll();
        for (Theme theme : allTheme) {
            theme.setHave(false);
        }
        UserTheme userThemeEx = new UserTheme();
        userThemeEx.setUserId(actionParam.getAccountID());
        List<UserTheme> userThemes = userThemeRepository.findAll(Example.of(userThemeEx));
        for (Theme theme : allTheme) {
            //判断出已拥有主题
            for (UserTheme userTheme : userThemes) {
                if (theme.getId().equals(userTheme.getThemeId())) {
                    theme.setHave(true);
                }
            }

            //获取主题下面带有的道具
            Prop propEx = new Prop();
            propEx.setThemeId(theme.getId());
            List<Prop> allProps = propRepository.findAll(Example.of(propEx));
            for (Prop allProp : allProps) {
                allProp.setHave(false);
            }
            UserProp userPropEx = new UserProp();
            userPropEx.setUserId(actionParam.getAccountID());
            List<UserProp> UserProps = userPropRepository.findAll(Example.of(userPropEx));

            for (Prop prop : allProps) {
                for (UserProp userProp : UserProps) {
                    if (prop.getId().equals(userProp.getPropId())){
                        prop.setHave(true);
                    }
                }

            }

            ThemeVo themeVo = new ThemeVo();
            themeVo.setTheme(theme);
            themeVo.setProps(allProps);
            allThemeVo.add(themeVo);
        }



        return allThemeVo;
    }

    public OkResult buy(ThemeBuyParam themeBuyParam) {

        User userEx = new User();
        userEx.setId(themeBuyParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }

        // 检查是否已经购买过主题
        UserTheme ex = new UserTheme();
        ex.setThemeId(themeBuyParam.getThemeId());
        ex.setUserId(themeBuyParam.getAccountID());
        UserTheme userTheme = userThemeRepository.findOne(Example.of(ex)).orElse(null);
        if (userTheme != null) {
            throw new BaseRuntimeException("has.theme");
        }

        // 检查主题是否存在
        Theme themeEx = new Theme();
        themeEx.setId(themeBuyParam.getThemeId());
        Theme theme = themeRepository.findOne(Example.of(themeEx)).orElse(null);

        if (theme == null) {
            throw new BaseRuntimeException("no.theme");
        }


        // 检查金币是否充足
        Float resultCoin = user.getCoin() - theme.getPrice();
        if (resultCoin < 0) {
            throw new BaseRuntimeException("no.money");

        }
        // 更新用户金币数
        user.setCoin(resultCoin);
        userRepository.save(user);

        //保存用户的主题
        userTheme = new UserTheme();
        userTheme.setUserId(themeBuyParam.getAccountID());
        userTheme.setThemeId(themeBuyParam.getThemeId());
        userTheme.setCreateTime(new Date());
        userTheme.setPrice(theme.getPrice());
        userThemeRepository.save(userTheme);

        return new OkResult(true);
    }
}
