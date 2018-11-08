package com.gj.gejigeji.vo;

public class OkResult {
    private Boolean isOk;
    private Integer coin;
    private String awardId;


    public OkResult(boolean isOk) {
        this.isOk = isOk;
    }

    public Boolean getOk() {
        return isOk;
    }

    public void setOk(Boolean ok) {
        isOk = ok;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }
}
