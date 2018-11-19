package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分页参数
 */
public class PageParam {
    @ApiModelProperty("页数(从0开始)")
    private int page;
    @ApiModelProperty("size")
    private int size;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
