package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

public class FeedingParam {
    @ApiModelProperty("用户ID")
    private String accountID;
    @ApiModelProperty("饲料ID")
    private String feedID;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getFeedID() {
        return feedID;
    }

    public void setFeedID(String feedID) {
        this.feedID = feedID;
    }
}
