package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;

public class LoginVo {
    private String accountID;
    private int eggCount;
    private int coin;
    private int jewel;
    private int likeValue;
    private Boolean award;
    private int skinID;
    private String mailInfo;
    @ApiModelProperty(value = "是否需要绑定手机号")
    private boolean bindPhone = false;

    public LoginVo() {
    }

    public LoginVo(String accountID, int eggCount, int coin, int jewel, int likeValue, Boolean award, int skinID, String mailInfo, boolean bindPhone) {
        this.accountID = accountID;
        this.eggCount = eggCount;
        this.coin = coin;
        this.jewel = jewel;
        this.likeValue = likeValue;
        this.award = award;
        this.skinID = skinID;
        this.mailInfo = mailInfo;
        this.bindPhone = bindPhone;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public int getEggCount() {
        return eggCount;
    }

    public void setEggCount(int eggCount) {
        this.eggCount = eggCount;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getJewel() {
        return jewel;
    }

    public void setJewel(int jewel) {
        this.jewel = jewel;
    }

    public int getLikeValue() {
        return likeValue;
    }

    public void setLikeValue(int likeValue) {
        this.likeValue = likeValue;
    }

    public boolean isAward() {
        return award;
    }

    public void setAward(boolean award) {
        this.award = award;
    }

    public int getSkinID() {
        return skinID;
    }

    public void setSkinID(int skinID) {
        this.skinID = skinID;
    }

    public String getMailInfo() {
        return mailInfo;
    }

    public void setMailInfo(String mailInfo) {
        this.mailInfo = mailInfo;
    }

    public boolean isBindPhone() {
        return bindPhone;
    }

    public void setBindPhone(boolean bindPhone) {
        this.bindPhone = bindPhone;
    }
}
