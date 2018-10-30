package com.gj.gejigeji.service;

import com.gj.gejigeji.model.SmsCode;
import com.gj.gejigeji.repository.SmsCodeRepository;
import com.gj.gejigeji.util.ConstUtil;
import com.gj.gejigeji.vo.OkResult;
import com.gj.gejigeji.vo.SmsParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SmsServices {

    @Autowired
    SmsCodeRepository smsCodeRepository;

    /**
     * 获取注册验证码
     * @param smsParam
     * @return
     */
    public OkResult register(SmsParam smsParam) {
        SmsCode s = new SmsCode();
        // 默认验证码的666666
        s.setAuthCode("666666");
        s.setPhone(smsParam.getPhone());
        s.setType(ConstUtil.SMS_CODE_REG);
        s.setCreateTime(new Date());
        s.setDelete_flag(ConstUtil.Delete_Flag_No);
        smsCodeRepository.save(s);
        return new OkResult(true);
    }

}
