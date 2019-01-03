package com.gj.gejigeji.exception;

/**
 * 抽奖次数不足
 */
public class LuckNoNumException extends BaseRuntimeException {
    public LuckNoNumException() {
        setStatus(400);
        setCode("luck.no.num");
    }
}
