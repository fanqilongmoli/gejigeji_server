package com.gj.gejigeji.vo;

public class FeedBuyParam {
    //用户id
    private String accountID;
    //饲料id
    private String feedId;
    //购买数量
    private Integer amount;


    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
