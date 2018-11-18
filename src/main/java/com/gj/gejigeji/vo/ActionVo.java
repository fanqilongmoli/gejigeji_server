package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

public class ActionVo {

    @ApiModelProperty("好感度")
    private Integer likeValue;
    @ApiModelProperty("是否可以下蛋")
    private boolean egg;

    public Integer getLikeValue() {
        return likeValue;
    }

    public void setLikeValue(Integer likeValue) {

        this.likeValue = likeValue;
    }

    public boolean isEgg() {
        return egg;
    }

    public void setEgg(boolean egg) {
        this.egg = egg;
    }
}
