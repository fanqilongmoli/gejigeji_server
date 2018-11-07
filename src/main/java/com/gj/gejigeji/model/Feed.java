package com.gj.gejigeji.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 饲料 Document
 */
@Document(collection = "feed")
public class Feed {

    @Id
    private String Id;

    // 图片
    private String url;

    // 名字
    private String name;

    // 描述
    private String desc;

    // 价格
    private Float price;

    //最小购买数
    private Integer min;


    public Feed() {
    }

    public Feed(String id, String url, String name, String desc, Float price,Integer min) {
        Id = id;
        this.url = url;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.min = min;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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
}
