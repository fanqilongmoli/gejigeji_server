package com.gj.gejigeji.vo;

import com.gj.gejigeji.model.Prop;
import com.gj.gejigeji.model.Theme;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class ThemeVo {

    @ApiModelProperty("主题Id")
    private String Id;

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


    @ApiModelProperty("主题包含的道具")
    private List<Prop> props;

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

    public Boolean getHave() {
        return have;
    }

    public void setHave(Boolean have) {
        this.have = have;
    }

    public List<Prop> getProps() {
        return props;
    }

    public void setProps(List<Prop> props) {
        this.props = props;
    }


    public void setTheme(Theme theme){
        this.Id = theme.getId();
        this.url = theme.getUrl();
        this.name = theme.getName();
        this.desc = theme.getDesc();
        this.price = theme.getPrice();
        this.have = theme.getHave();

    }
}
