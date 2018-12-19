package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.SmsCode;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.model.UserChicken;
import com.gj.gejigeji.repository.ChickenRepository;
import com.gj.gejigeji.repository.SmsCodeRepository;
import com.gj.gejigeji.repository.UserChickenRepository;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.util.ConstUtil;
import com.gj.gejigeji.vo.OkResult;
import com.gj.gejigeji.vo.RegParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RegService {

    @Autowired
    SmsCodeRepository smsCodeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;

    @Autowired
    PropService propService;

    @Autowired
    FeedService feedService;

    @Autowired
    UserChickenRepository userChickenRepository;

    @Autowired
    ChickenRepository chickenRepository;

    public OkResult register(RegParam regParam) {
        SmsCode ex = new SmsCode();
        ex.setDelete_flag(ConstUtil.Delete_Flag_No);
        ex.setType(ConstUtil.SMS_CODE_REG);
        ex.setPhone(regParam.getPhone());

        SmsCode smsCode = smsCodeRepository.findOne(Example.of(ex)).orElse(null);
        if (smsCode == null) {
            throw new BaseRuntimeException("auth.code.error");
        } else {
            if (smsCode.getAuthCode().equals(regParam.getAuthCode())) {
                //验证码验证成功  保存用户信息 修改smsCode表
                smsCode.setDelete_flag(ConstUtil.Delete_Flag_Yes);
                smsCodeRepository.save(smsCode);

                User user = new User();
                user.setPhone(regParam.getPhone());
                user.setPassword(regParam.getPassword());
                user.setJewel(0.0f);
                user.setCoin(1000f);
                user.setAward(false);
                user.setSkinID(0);
                user.setEggCount(0);
                user.setMiniGameCount1(10);
                user.setMiniGameCount2(10);
                user.setMiniGameCount3(10);
                user.setMiniGameCount4(10);

                //用户名 初始化为 电话号码
                user.setUserName(regParam.getPhone());

                User save = userRepository.save(user);

                //挂载用户邮件 道具 饲料
                mailService.mailTest(save.getId());
                propService.propTest(save.getId());
                feedService.feedTest(save.getId());

                // 默认给用户挂载上 一只鸡
                String chickenId = chickenRepository.findAll().get(0).getId();
                Date lastTime = new Date();
                UserChicken userChicken = new UserChicken();
                userChicken.setUserId(save.getId());
                userChicken.setDayEgg(0);
                userChicken.setChickenId(chickenId);
                userChicken.setBathe(0);
                userChicken.setBatheLastTime(lastTime);
                userChicken.setFeed(0);
                userChicken.setFeedLastTime(lastTime);
                userChicken.setStroke(0);
                userChicken.setStrokeLastTime(lastTime);
                userChicken.setTv(0);
                userChicken.setTvLastTime(lastTime);
                userChicken.setGame(0);
                userChicken.setGameLastTime(lastTime);
                userChickenRepository.save(userChicken);


                return new OkResult(true);
            } else {
                throw new BaseRuntimeException("auth.code.error");
            }

        }

    }
}
