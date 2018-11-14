package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户认证
 */
public class AuthenticationParam {
    private String accountID;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("证件号")
    private String papers;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPapers() {
        return papers;
    }

    public void setPapers(String papers) {
        this.papers = papers;
    }
}
