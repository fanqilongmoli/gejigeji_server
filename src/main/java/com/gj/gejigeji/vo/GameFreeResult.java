package com.gj.gejigeji.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GameFreeResult {
    @ApiModelProperty("是否可以免费玩儿游戏：true，可以；false，不可。")
    private boolean ok;
    @ApiModelProperty("余额是否够扣一次：true，够；false，不够。")
    private boolean enough;
    @ApiModelProperty("每次游戏扣除金币数")
    private int fee;

}
