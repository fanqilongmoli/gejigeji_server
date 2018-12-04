package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

public class UserEggsAllVo {

    @ApiModelProperty("总的鸡蛋个数")
    private int amount;

    @ApiModelProperty("鸡蛋冻结的个数")
    private int freeze;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getFreeze() {
        return freeze;
    }

    public void setFreeze(int freeze) {
        this.freeze = freeze;
    }
}
