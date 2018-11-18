package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

public class FeedingVo {
    @ApiModelProperty("饲料剩余的个数")
    private int count;
    @ApiModelProperty("好感度")
    private int likeValue;
    @ApiModelProperty("是否允许操作")
    private boolean allow;
    @ApiModelProperty("是否执行下蛋操作")
    private boolean egg;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getLikeValue() {
        return likeValue;
    }

    public void setLikeValue(int likeValue) {
        this.likeValue = likeValue;
    }

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public boolean isEgg() {
        return egg;
    }

    public void setEgg(boolean egg) {
        this.egg = egg;
    }
}
