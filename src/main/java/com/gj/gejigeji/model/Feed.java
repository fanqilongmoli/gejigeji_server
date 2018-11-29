package com.gj.gejigeji.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 饲料 Document
 */
@Document(collection = "feed")
public class Feed {


    @Id
    private String id;

    @ApiModelProperty(value = "U3D饲料图片的唯一表示")
    private Integer feedUI;

    @ApiModelProperty(value = "图片的url")
    private String url;

    @ApiModelProperty(value = "饲料的名字")
    private String name;

    @ApiModelProperty("吃这种饲料下的蛋的名字")
    private String eggName;


    @ApiModelProperty(value = "U3D鸡蛋图片的唯一表示")
    private Integer eggUI;

    @ApiModelProperty(value = "鸡蛋的图片url")
    private String eggUrl;

    // 描述
    @ApiModelProperty(value = "饲料的描述")
    private String desc;

    // 价格
    @ApiModelProperty(value = "饲料的单价")
    private Float price;

    //最小购买数
    @ApiModelProperty(value = "饲料的最小购买数量")
    private Integer min;


    public Feed() {
    }

    public Feed(String id, String url, String name,String eggName, String desc, Float price,Integer min) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.eggName = eggName;
        this.desc = desc;
        this.price = price;
        this.min = min;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public String getEggName() {
        return eggName;
    }

    public void setEggName(String eggName) {
        this.eggName = eggName;
    }

    public Integer getFeedUI() {
        return feedUI;
    }

    public void setFeedUI(Integer feedUI) {
        this.feedUI = feedUI;
    }

    public Integer getEggUI() {
        return eggUI;
    }

    public void setEggUI(Integer eggUI) {
        this.eggUI = eggUI;
    }

    public String getEggUrl() {
        return eggUrl;
    }

    public void setEggUrl(String eggUrl) {
        this.eggUrl = eggUrl;
    }
}
