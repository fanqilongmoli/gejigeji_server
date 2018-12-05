package com.gj.gejigeji.exception;

public class NoCoinException extends BaseRuntimeException {
    public NoCoinException() {
        setStatus(400);
        setCode("no.coin");
    }
}
