package com.gj.gejigeji.vo;

import java.util.List;

/**
 * Restful result containing errors.
 * @author wanglei, wangleilc@qq.com
 * @version 1.0.0
 * @since 1.0.0
 */
public class ErrorResult {

    /**
     * 错误说明
     */
    private List<String> errors;

    /**
     * 错误码
     */
    private String code;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
