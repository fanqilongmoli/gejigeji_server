package com.gj.gejigeji.vo;

public class OkResult {
    private Boolean isOk;


    public OkResult(boolean isOk) {
        this.isOk = isOk;
    }

    public Boolean getOk() {
        return isOk;
    }

    public void setOk(Boolean ok) {
        isOk = ok;
    }
}
