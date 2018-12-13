package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

public class GetMessagesParam {

    @ApiModelProperty("用户ID")
    private String accountID;

    @ApiModelProperty("好友ID")
    private String friendID;

    private Integer page;

    private Integer size;

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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
