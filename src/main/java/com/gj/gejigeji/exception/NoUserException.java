package com.gj.gejigeji.exception;

public class NoUserException extends BaseRuntimeException {
    public NoUserException() {
        setStatus(400);
        setCode("login.user.null");
    }
}
