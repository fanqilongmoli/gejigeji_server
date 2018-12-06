package com.gj.gejigeji.exception;

/**
 * 订单不存在
 */
public class NoOrderException extends BaseRuntimeException{
    public NoOrderException() {
        setStatus(400);
        setCode("no.order");
    }
}
