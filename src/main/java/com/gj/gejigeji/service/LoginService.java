package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.*;
import com.gj.gejigeji.repository.*;
import com.gj.gejigeji.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
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

    @Autowired
    PropRepository propRepository;
    @Autowired
    UserThemeRepository userThemeRepository;
    @Autowired
    ThemeRepository themeRepository;
    @Autowired
    UserFeedRepository userFeedRepository;
    @Autowired
    FeedRepository feedRepository;
    @Autowired
    UserEggRepository userEggRepository;

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

        // 获取用户的道具列表
        List<ItemInfo> itemInfos = new ArrayList<>();
        ItemInfo itemInfo;
        UserProp upex = new UserProp();
        upex.setUserId(user.getId());
        List<UserProp> propList = userPropRepository.findAll(Example.of(upex));
        for (UserProp userProp : propList) {
            Prop prop = propRepository.findById(userProp.getPropId()).orElse(null);
            if (prop!=null){
                itemInfo = new ItemInfo();
                itemInfo.setItemId(prop.getPropUI());
                itemInfo.setItemCount(1);
                itemInfos.add(itemInfo);
            }

        }
        //获取用户主题列表
        UserTheme userThemeEx = new UserTheme();
        userThemeEx.setUserId(user.getId());
         List<UserTheme> userThemes = userThemeRepository.findAll(Example.of(userThemeEx));
        for (UserTheme userTheme : userThemes) {
            Theme theme = themeRepository.findById(userTheme.getThemeId()).orElse(null);
        if (theme!=null){
            itemInfo = new ItemInfo();
            itemInfo.setItemId(theme.getThemeUI());
            itemInfo.setItemCount(1);
            itemInfos.add(itemInfo);
        }

        }
        //获取用户饲料
        UserFeed userFeedEx = new UserFeed();
        userFeedEx.setUserId(user.getId());
        List<UserFeed> userFeeds = userFeedRepository.findAll(Example.of(userFeedEx));
        for (UserFeed userFeed : userFeeds) {
            Feed feed = feedRepository.findById(userFeed.getFeedId()).orElse(null);
            if (feed!=null){
                itemInfo = new ItemInfo();
                itemInfo.setItemId(feed.getFeedUI());
                itemInfo.setItemCount(userFeed.getAmount());
                itemInfos.add(itemInfo);
            }

        }

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

            loginVo.setLikeValues(userChicken);
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
        loginVo.setItemInfos(itemInfos);


        return loginVo;
    }
}
