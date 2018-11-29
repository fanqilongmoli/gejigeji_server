package com.gj.gejigeji.model;


import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "henhouse")
public class Henhouse {

    @Id
    private String Id;

    // 图片
    @ApiModelProperty(value = "图片的url")
    private String url;

    //价格
    @ApiModelProperty(value = "价格")
    private Float price;

    // 鸡舍的名字
    @ApiModelProperty(value = "名字")
    private String name;

    //描述
    @ApiModelProperty(value = "描述")
    private String desc;

    @ApiModelProperty("U3D鸡舍图片的唯一表示")
    private Integer henhouseUI;

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
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

    public Integer getHenhouseUI() {
        return henhouseUI;
    }

    public void setHenhouseUI(Integer henhouseUI) {
        this.henhouseUI = henhouseUI;
    }
}
