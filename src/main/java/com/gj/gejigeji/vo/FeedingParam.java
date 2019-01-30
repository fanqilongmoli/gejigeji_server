package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

public class FeedingParam {
    @ApiModelProperty("用户ID")
    private String accountID;
    @ApiModelProperty("饲料ID")
    private Integer feedID;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Integer getFeedID() {
        return feedID;
    }

    public void setFeedID(Integer feedID) {
        this.feedID = feedID;
    }
}
