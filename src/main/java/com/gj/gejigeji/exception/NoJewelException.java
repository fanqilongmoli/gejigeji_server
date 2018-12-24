package com.gj.gejigeji.exception;

/**
 * 钻石数量不足
 */
public class NoJewelException extends BaseRuntimeException {
    public NoJewelException() {
        setStatus(400);
        setCode("no.jewel");
    }
}
