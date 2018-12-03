package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

public class GameResultVo {
    @ApiModelProperty("是否同意执行")
    private Boolean allow;
    @ApiModelProperty("剩余游戏次数")
    private Integer count;
    @ApiModelProperty("最后结果的ID->需要返回字段时会有（最后结果的ID（看配置表）")
    private Integer allowID;
    @ApiModelProperty("金币数量")
    private Integer coin;
    @ApiModelProperty("奖励ID（对照表ID）")
    private Integer awardId;

    public Boolean getAllow() {
        return allow;
    }

    public void setAllow(Boolean allow) {
        this.allow = allow;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAllowID() {
        return allowID;
    }

    public void setAllowID(Integer allowID) {
        this.allowID = allowID;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }
}
