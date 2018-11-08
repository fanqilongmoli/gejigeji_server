package com.gj.gejigeji.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 道具
 */
@Document(collection = "prop")
public class Prop {

    @Id
    private String Id;

    @ApiModelProperty("道具的图片")
    private String url;

    // 名字
    @ApiModelProperty("道具的名字")
    private String name;

    // 描述
    @ApiModelProperty("道具的描述")
    private String desc;

    // 价格
    @ApiModelProperty("道具的价格")
    private Float price;

    private Integer min;

    @ApiModelProperty("是否已经拥有")
    private Boolean have;


    public Prop(String id, String url, String name, String desc, Float price,Integer min) {
        Id = id;
        this.url = url;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.min = min;
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

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Boolean getHave() {
        return have;
    }

    public void setHave(Boolean have) {
        this.have = have;
    }
}
