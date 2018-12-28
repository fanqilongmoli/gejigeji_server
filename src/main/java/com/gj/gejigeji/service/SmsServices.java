package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.SmsSendFailException;
import com.gj.gejigeji.model.SmsCode;
import com.gj.gejigeji.repository.SmsCodeRepository;
import com.gj.gejigeji.schedule.OrderJob;
import com.gj.gejigeji.util.ConstUtil;
import com.gj.gejigeji.util.SmsUtil;
import com.gj.gejigeji.vo.OkResult;
import com.gj.gejigeji.vo.SmsParam;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SmsServices {

    private static final Logger logger = LoggerFactory.getLogger(SmsServices.class);

    @Autowired
    SmsCodeRepository smsCodeRepository;

    /**
     * 获取注册验证码
     *
     * @param smsParam
     * @return
     */
    public OkResult register(SmsParam smsParam) {
        SmsCode s = new SmsCode();
        // 默认验证码的666666
        String checkCode = RandomStringUtils.randomNumeric(6);
        logger.info("验证码:" + checkCode);

        boolean b = SmsUtil.sendSms(smsParam.getPhone(), checkCode);
        if (b) {
            s.setAuthCode(checkCode);
            s.setPhone(smsParam.getPhone());
            s.setType(ConstUtil.SMS_CODE_REG);
            s.setCreateTime(new Date());
            s.setDelete_flag(ConstUtil.Delete_Flag_No);
            smsCodeRepository.save(s);
            return new OkResult(true);
        } else {
            throw new SmsSendFailException();
        }

    }

}
