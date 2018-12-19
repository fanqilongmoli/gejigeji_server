package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

public class LuckVo {

    @ApiModelProperty("是否中奖")
    private boolean luck;

    @ApiModelProperty("中奖类型 1 金币 2 钻石 3 饲料")
    private Integer luckType;

    @ApiModelProperty("中奖的图片")
    private String url;

    @ApiModelProperty("中奖数量")
    private Float num;

    @ApiModelProperty("名字")
    private String name;

    public boolean isLuck() {
        return luck;
    }

    public void setLuck(boolean luck) {
        this.luck = luck;
    }

    public Integer getLuckType() {
        return luckType;
    }

    public void setLuckType(Integer luckType) {
        this.luckType = luckType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
