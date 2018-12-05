package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

public class AddFriendVo {

    @ApiModelProperty("矶有名")
    private String username;
    @ApiModelProperty("附言")
    private String ps;

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
