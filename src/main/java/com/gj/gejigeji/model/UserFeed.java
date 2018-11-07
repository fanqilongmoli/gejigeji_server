package com.gj.gejigeji.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_feed")
public class UserFeed {
    @Id
    private String Id;

    // 用户id
    private String userId;

    // 饲料id
    private String feedId;

    // 价格
    private Float price;

    // 数量
    private Integer amount;

    // 总价
    private Float allPrice;

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

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Float getAllPrice() {
        return allPrice;
    }

    public void setAllPrice(Float allPrice) {
        this.allPrice = allPrice;
    }
}
