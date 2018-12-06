package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("申请添加好友参数")
public class AddFriendParam {

    @ApiModelProperty("用户ID")
    private String accountID;
    @ApiModelProperty("矶有名")
    private String username;
    @ApiModelProperty("附言")
    private String ps;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}
