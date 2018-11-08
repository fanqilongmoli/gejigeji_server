package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

public class PropBuyParam {

    @ApiModelProperty("用户id")
    private String accountID;

    @ApiModelProperty("道具id")
    private String propId;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getPropId() {
        return propId;
    }

    public void setPropId(String propId) {
        this.propId = propId;
    }
}
