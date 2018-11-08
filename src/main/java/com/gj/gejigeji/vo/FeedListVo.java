package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;

public class FeedListVo {

    @ApiModelProperty(value = "饲料id")
    private String feedId;

    // 图片
    @ApiModelProperty(value = "图片的url")
    private String url;

    // 名字
    @ApiModelProperty(value = "饲料的名字")
    private String name;

    // 描述
    @ApiModelProperty(value = "饲料的描述")
    private String desc;

    // 价格
    @ApiModelProperty(value = "饲料的单价")
    private Float price;

    // 数量
    @ApiModelProperty(value = "数量")
    private Integer amount;

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
}
