package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.SmsCode;
import com.gj.gejigeji.model.User;
import com.gj.gejigeji.repository.SmsCodeRepository;
import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.util.ConstUtil;
import com.gj.gejigeji.vo.OkResult;
import com.gj.gejigeji.vo.RegParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

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

    public OkResult register(RegParam regParam){
        SmsCode ex = new SmsCode();
        ex.setDelete_flag(ConstUtil.Delete_Flag_No);
        ex.setType(ConstUtil.SMS_CODE_REG);
        ex.setPhone(regParam.getPhone());

        SmsCode smsCode = smsCodeRepository.findOne(Example.of(ex)).orElse(null);
        if (smsCode == null){
            throw new BaseRuntimeException("auth.code.error");
        }else {
            if (smsCode.getAuthCode().equals(regParam.getAuthCode())) {
                //验证码验证成功  保存用户信息 修改smsCode表
                smsCode.setDelete_flag(ConstUtil.Delete_Flag_Yes);
                smsCodeRepository.save(smsCode);

                User user = new User();
                user.setPhone(regParam.getPhone());
                user.setPassword(regParam.getPassword());
                user.setLikeValue(0);
                user.setJewel(0);
                user.setCoin(0);
                user.setAward(false);
                user.setSkinID(0);
                user.setEggCount(0);
                user.setMiniGameCount1(10);
                user.setMiniGameCount2(10);
                user.setMiniGameCount3(10);
                user.setMiniGameCount4(10);


                User save = userRepository.save(user);

                //挂载用户邮件 道具
                mailService.mailTest(save.getId());
                propService.propTest(save.getId());



                return new OkResult(true);
            }else {
                throw new BaseRuntimeException("auth.code.error");
            }

        }

    }
}
