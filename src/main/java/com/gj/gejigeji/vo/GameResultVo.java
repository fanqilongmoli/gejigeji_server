package com.gj.gejigeji.vo;

import io.swagger.models.auth.In;

public class GameResultVo {
    private Boolean allow;
    private Integer count;
    private Integer allowID;

    public Boolean getAllow() {
        return allow;
    }

    public void setAllow(Boolean allow) {
        this.allow = allow;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAllowID() {
        return allowID;
    }

    public void setAllowID(Integer allowID) {
        this.allowID = allowID;
    }
}
