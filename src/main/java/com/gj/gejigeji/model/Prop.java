package com.gj.gejigeji.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 道具
 */
@Document(collection = "prop")
public class Prop {

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


    public Prop(String id, String url, String name, String desc, Float price) {
        Id = id;
        this.url = url;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public Prop() {
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
}
