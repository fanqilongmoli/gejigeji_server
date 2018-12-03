package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

public class DGSEndParam {
    private String accountID;
    @ApiModelProperty("最后剩余生命")
    private Integer lifeCount;
    @ApiModelProperty("是否胜利")
    private Boolean isWin;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Integer getLifeCount() {
        return lifeCount;
    }

    public void setLifeCount(Integer lifeCount) {
        this.lifeCount = lifeCount;
    }

    public Boolean getWin() {
        return isWin;
    }

    public void setWin(Boolean win) {
        isWin = win;
    }
}
