package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GameResultVo {
    private Boolean allow;
    @ApiModelProperty("获取道具的数量")
    private Integer count;
    @ApiModelProperty("道具ID（对照道具基础数据表ID")
    private Integer itemId;
    @ApiModelProperty("奖励ID（对照表ID）")
    private Integer awardId;
}
