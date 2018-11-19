package com.gj.gejigeji.exception;


public class NoOrderException extends BaseRuntimeException{
    public NoOrderException() {
        setStatus(400);
        setCode("no.order");
    }
}
