package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

public class ThemeBuyParam {
    //用户id
    @ApiModelProperty("用户id")
    private String accountID;
    //饲料id
    @ApiModelProperty("主题id")
    private String themeId;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }
}
