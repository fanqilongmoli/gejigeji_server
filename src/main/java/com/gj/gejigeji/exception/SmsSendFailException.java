package com.gj.gejigeji.exception;

/**
 * 验证码发送失败
 */
public class SmsSendFailException extends BaseRuntimeException {
    public SmsSendFailException() {
        setStatus(400);
        setCode("sms.code.fail");
    }
}
