package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.*;
import com.gj.gejigeji.repository.*;
import com.gj.gejigeji.vo.BindPhoneParam;
import com.gj.gejigeji.vo.Login3rdParam;
import com.gj.gejigeji.vo.LoginParam;
import com.gj.gejigeji.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MailContentRepository mailContentRepository;

    @Autowired
    MailRepository mailRepository;

    @Autowired
    UserPropRepository userPropRepository;

    @Autowired
    UserChickenRepository userChickenRepository;

    @Autowired
    UserSiteRepository userSiteRepository;

    /**
     * 登录
     *
     * @param loginParam
     * @return
     */
    public LoginVo login(LoginParam loginParam) {
        User ex = new User();
        ex.setPhone(loginParam.getPhone());

        User user = userRepository.findOne(Example.of(ex)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }

        return ret(user);
    }

    /**
     * 第三方登录
     *
     * @param login3rdParam
     * @return
     */
    public LoginVo login3rd(Login3rdParam login3rdParam) {
        User ex = new User();
        ex.setPhone(login3rdParam.getPlatformID());

        User user = userRepository.findOne(Example.of(ex)).orElse(null);


        if (user == null) {
            LoginVo loginVo = new LoginVo();
            loginVo.setBindPhone(true);
            return loginVo;

        } else {

            return ret(user);

        }

    }

    /**
     * 绑定手机
     *
     * @param bindPhoneParam
     * @return
     */
    public LoginVo bindPhone(BindPhoneParam bindPhoneParam) {
        User user = new User();
        user.setPlatformID(bindPhoneParam.getPlatformID());
        user.setPhone(bindPhoneParam.getPhone());
        User save = userRepository.save(user);

        return ret(save);
    }


    private LoginVo ret(User user) {
        //====拼装返回的vo====
        Mail mailEx = new Mail();
        mailEx.setUserId(user.getId());
        List<Mail> mailList = mailRepository.findAll(Example.of(mailEx));

        UserProp upex = new UserProp();
        upex.setUserId(user.getId());
        List<UserProp> propList = userPropRepository.findAll(Example.of(upex));

        LoginVo loginVo = new LoginVo();
        loginVo.setAccountID(user.getId());
        loginVo.setAward(false);
        loginVo.setCoin(user.getCoin());
        loginVo.setEggCount(user.getEggCount());
        loginVo.setJewel(user.getJewel());
        loginVo.setPhone(user.getPhone());
        // 获取好感度  不存在需要添加
        UserChicken userChickenEx = new UserChicken();
        userChickenEx.setUserId(user.getId());
        UserChicken userChicken = userChickenRepository.findOne(Example.of(userChickenEx)).orElse(null);
        if (userChicken!=null){
            int likeValue = userChicken.getFeed() + userChicken.getStroke() + userChicken.getBathe() + userChicken.getGame() + userChicken.getTv();
            loginVo.setLikeValue(likeValue);
        }
        // 获取配送地址
        UserSite userSiteEx = new UserSite();
        userSiteEx.setUserId(user.getId());
        UserSite userSite = userSiteRepository.findOne(Example.of(userSiteEx)).orElse(null);
        loginVo.setSite(userSite);

        loginVo.setSkinID(user.getSkinID());
        loginVo.setUserName(user.getUserName());
        loginVo.setMiniGameCount1(user.getMiniGameCount1());
        loginVo.setMiniGameCount2(user.getMiniGameCount2());
        loginVo.setMiniGameCount3(user.getMiniGameCount3());
        loginVo.setMiniGameCount4(user.getMiniGameCount4());
        loginVo.setMailInfo(mailList);
        loginVo.setItemInfos(propList);


        return loginVo;
    }
}
