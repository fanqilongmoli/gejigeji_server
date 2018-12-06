package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

public class SendCoinParam {

    @ApiModelProperty("用户ID")
    private String accountID;

    @ApiModelProperty("好友ID")
    private String friendID;

    @ApiModelProperty("金币数量")
    private int coin;

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

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
