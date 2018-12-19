package com.gj.gejigeji.exception;

/**
 * 用户不存在
 */
public class LuckNoNumException extends BaseRuntimeException {
    public LuckNoNumException() {
        setStatus(400);
        setCode("luck.no.num");
    }
}
