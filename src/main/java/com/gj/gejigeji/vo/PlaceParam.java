package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;

/**
 * 下单参数
 */
public class PlaceParam {

    @ApiModelProperty("用户id")
    private String accountID;

    @ApiModelProperty("单价")
    private Integer price;

    @ApiModelProperty("数量")
    private Integer amount;

    @ApiModelProperty("鸡蛋的类型")
    private String feedId;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }
}
