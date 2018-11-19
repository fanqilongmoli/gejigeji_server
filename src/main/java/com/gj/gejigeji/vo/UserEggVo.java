package com.gj.gejigeji.vo;

import com.gj.gejigeji.model.UserEgg;
import io.swagger.annotations.ApiModelProperty;

public class UserEggVo {


    @ApiModelProperty("鸡蛋的类型（根据饲料区分）")
    private String feedId;

    @ApiModelProperty("鸡蛋的名字")
    private String eggName;

    @ApiModelProperty("总的鸡蛋个数")
    private Integer amount;

    @ApiModelProperty("鸡蛋冻结的个数")
    private Integer freeze;

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getEggName() {
        return eggName;
    }

    public void setEggName(String eggName) {
        this.eggName = eggName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }

    public void setUserEgg(UserEgg userEgg) {
        this.feedId = userEgg.getFeedId();
        this.amount = userEgg.getAmount();
        this.freeze = userEgg.getFreeze();
    }
}
