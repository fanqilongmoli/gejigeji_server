package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class GetChartParam {
    @ApiModelProperty("图表类型 0周 1月")
    private int chartType;
    @ApiModelProperty("开始时间")
    private Date time;
    @ApiModelProperty("鸡蛋类型")
    private String feedId;

    public int getChartType() {
        return chartType;
    }

    public void setChartType(int chartType) {
        this.chartType = chartType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }
}
