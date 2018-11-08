package com.gj.gejigeji.vo;

public class DDSEndParam {
    private String accountID;
    //打中狼的个数
    private Integer wolfCount;
    //打中炸弹个数
    private Integer bombCount;
    //打中地鼠个数
    private Integer diglettCount ;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Integer getWolfCount() {
        return wolfCount;
    }

    public void setWolfCount(Integer wolfCount) {
        this.wolfCount = wolfCount;
    }

    public Integer getBombCount() {
        return bombCount;
    }

    public void setBombCount(Integer bombCount) {
        this.bombCount = bombCount;
    }

    public Integer getDiglettCount() {
        return diglettCount;
    }

    public void setDiglettCount(Integer diglettCount) {
        this.diglettCount = diglettCount;
    }
}
