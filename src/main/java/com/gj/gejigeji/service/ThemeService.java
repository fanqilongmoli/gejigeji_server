package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.Theme;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.model.UserTheme;
import com.gj.gejigeji.repository.ThemeRepository;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.repository.UserThemeRepository;
import com.gj.gejigeji.vo.OkResult;
import com.gj.gejigeji.vo.ThemeBuyParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

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

    public List<Theme> getAll() {
        return themeRepository.findAll();
    }

    public OkResult buy(ThemeBuyParam themeBuyParam) {

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

        if (theme==null){
            throw new BaseRuntimeException("no.theme");
        }


        User userEx = new User();
        userEx.setId(themeBuyParam.getAccountID());
        User user = userRepository.findOne(Example.of(userEx)).orElse(null);
        if (user == null){
            throw new BaseRuntimeException("login.user.null");
        }

        // 检查金币是否充足
        Float resultCoin = user.getCoin() - theme.getPrice();
        if (resultCoin<0){
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
