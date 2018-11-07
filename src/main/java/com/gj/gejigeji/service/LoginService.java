package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.Mail;
import com.gj.gejigeji.model.MailContent;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.model.UserProp;
import com.gj.gejigeji.repository.MailContentRepository;
import com.gj.gejigeji.repository.MailRepository;
import com.gj.gejigeji.repository.UserPropRepository;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.util.ConstUtil;
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


    public LoginVo login(LoginParam loginParam) {
        User ex = new User();
        ex.setPhone(loginParam.getPhone());

        User user = userRepository.findOne(Example.of(ex)).orElse(null);
        if (user == null) {
            throw new BaseRuntimeException("login.user.null");
        }


        //====拼装返回的vo====
        Mail mailEx = new Mail();
        mailEx.setUserId(user.getId());
        List<Mail> mailList = mailRepository.findAll(Example.of(mailEx));

        //==============
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
        loginVo.setLikeValue(user.getLikeValue());
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

    public LoginVo login3rd(Login3rdParam login3rdParam) {
        User ex = new User();
        ex.setPhone(login3rdParam.getPlatformID());

        User user = userRepository.findOne(Example.of(ex)).orElse(null);


        if (user == null) {
            LoginVo loginVo = new LoginVo();
            loginVo.setBindPhone(true);
            return loginVo;

        } else {
            return new LoginVo(user.getId(), user.getEggCount(), user.getCoin(), user.getJewel(), user.getLikeValue(), user.getAward(), user.getSkinID(), null, false);

        }

    }

    public LoginVo bindPhone(BindPhoneParam bindPhoneParam) {
        User user = new User();
        user.setPlatformID(bindPhoneParam.getPlatformID());
        user.setPhone(bindPhoneParam.getPhone());
        User save = userRepository.save(user);
        return new LoginVo(save.getId(), save.getEggCount(), save.getCoin(), save.getJewel(), save.getLikeValue(), save.getAward(), save.getSkinID(), null, false);
    }
}
