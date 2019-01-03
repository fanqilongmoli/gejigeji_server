package com.gj.gejigeji.exception;

/**
 * 本日下蛋次数已用完
 */
public class LayEggException extends BaseRuntimeException{
    public LayEggException() {
        setStatus(400);
        setCode("lay.egg");
    }
}
