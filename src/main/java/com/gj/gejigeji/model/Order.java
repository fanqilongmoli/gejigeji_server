package com.gj.gejigeji.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 订单表
 */
@Document(collection = "order")
public class Order {

    @Id
    private String Id;

    @ApiModelProperty("用户Id")
    private String userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("挂单量")
    private Integer amount;

    @ApiModelProperty("单价")
    private Integer price;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("总价")
    private Integer allPrice;

    @ApiModelProperty("鸡蛋的类型")
    private String feedId;

    @ApiModelProperty("订单完成的状态")
    private Byte orderState;


    private Byte deleteFlag;

    public String getFeedId() {
        return feedId;
    }

    public Byte getOrderState() {
        return orderState;
    }

    public void setOrderState(Byte orderState) {
        this.orderState = orderState;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(Integer allPrice) {
        this.allPrice = allPrice;
    }
}
