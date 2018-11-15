package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

public class FeedingVo {
    @ApiModelProperty("饲料剩余的个数")
    private Integer count;
    @ApiModelProperty("好感度")
    private Integer likeValue;
    @ApiModelProperty("是否允许操作")
    private Boolean allow;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getLikeValue() {
        return likeValue;
    }

    public void setLikeValue(Integer likeValue) {
        this.likeValue = likeValue;
    }

    public Boolean getAllow() {
        return allow;
    }

    public void setAllow(Boolean allow) {
        this.allow = allow;
    }
}
