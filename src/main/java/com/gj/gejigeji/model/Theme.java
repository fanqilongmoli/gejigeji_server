package com.gj.gejigeji.model;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 主题
 */
@Document(collection = "theme")
public class Theme {

    @Id
    private String id;

    // 图片
    @ApiModelProperty("图片")
    private String url;

    // 名字
    @ApiModelProperty("名字")
    private String name;

    // 描述
    @ApiModelProperty("描述")
    private String desc;

    // 价格
    @ApiModelProperty("价格")
    private Float price;

    @ApiModelProperty("是否已经拥有")
    private Boolean have;

    @ApiModelProperty("U3D主题图片的唯一表示")
    private Integer themeUI;


    public Theme(String id, String url, String name, String desc, Float price) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public Theme() {
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

    public Boolean getHave() {
        return have;
    }

    public void setHave(Boolean have) {
        this.have = have;
    }

    public Integer getThemeUI() {
        return themeUI;
    }

    public void setThemeUI(Integer themeUI) {
        this.themeUI = themeUI;
    }
}
