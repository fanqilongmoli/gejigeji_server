package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;


public class ApplyFriendParam {

    @ApiModelProperty("用户ID")
    private String accountID;

    @ApiModelProperty("好友ID")
    private String friendId;

    @ApiModelProperty("(action： 1同意 2忽略)")
    private int action;



    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

}
