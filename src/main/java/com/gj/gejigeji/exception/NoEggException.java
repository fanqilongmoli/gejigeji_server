package com.gj.gejigeji.exception;

/**
 * 鸡蛋数量不足
 */
public class NoEggException extends BaseRuntimeException{
    public NoEggException() {
        setStatus(400);
        setCode("no.egg");
    }
}
