package com.gj.gejigeji.exception;

import javax.servlet.http.HttpServletResponse;

/**
 * A base business runtime exception.
 * @author wanglei, wangleilc@qq.com
 * @version 1.0.0
 * @since 1.0.0
 */
public class BaseRuntimeException extends RuntimeException {
    public BaseRuntimeException() {
    }

    public BaseRuntimeException(String code) {
        this.code = code;
    }
    private String code = "system.error";//message code
    protected int status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
