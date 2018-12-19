package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

public class LuckParam {

    private String accountID;

    @ApiModelProperty("箱子的位置")
    private Integer open;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }
}
