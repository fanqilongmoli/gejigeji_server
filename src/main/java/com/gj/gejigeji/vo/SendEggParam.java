package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

public class SendEggParam {

    @ApiModelProperty("用户ID")
    private String accountID;

    @ApiModelProperty("好友ID")
    private String friendID;

    @ApiModelProperty("鸡蛋数量")
    private int egg;

    @ApiModelProperty("饲料id 绑定鸡蛋类型")
    private String feedId;
    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getFriendID() {
        return friendID;
    }

    public void setFriendID(String friendID) {
        this.friendID = friendID;
    }

    public int getEgg() {
        return egg;
    }

    public void setEgg(int egg) {
        this.egg = egg;
    }
}
