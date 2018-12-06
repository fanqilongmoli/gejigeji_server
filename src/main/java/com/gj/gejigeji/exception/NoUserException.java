package com.gj.gejigeji.exception;

/**
 * 用户不存在
 */
public class NoUserException extends BaseRuntimeException {
    public NoUserException() {
        setStatus(400);
        setCode("login.user.null");
    }
}
