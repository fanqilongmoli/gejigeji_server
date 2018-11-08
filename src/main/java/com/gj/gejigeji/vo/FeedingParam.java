package com.gj.gejigeji.vo;

public class FeedingParam {
    //用户id
    private String accountID;
    // 饲料的类型
    private Integer feedType;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Integer getFeedType() {
        return feedType;
    }

    public void setFeedType(Integer feedType) {
        this.feedType = feedType;
    }
}
