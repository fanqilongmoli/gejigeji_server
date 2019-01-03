package com.gj.gejigeji.exception;

/**
 * 户名已存在
 */
public class HasUserNameException extends BaseRuntimeException {

    public HasUserNameException() {
        setStatus(400);
        setCode("has.username");
    }
}
