package com.gj.gejigeji.exception;

public class HasUserNameException extends BaseRuntimeException {

    public HasUserNameException() {
        setStatus(400);
        setCode("has.username");
    }
}
