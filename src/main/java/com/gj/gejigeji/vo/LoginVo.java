package com.gj.gejigeji.vo;

import com.gj.gejigeji.model.*;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class LoginVo {
    private String accountID;
    private int eggCount;
    @ApiModelProperty("金币")
    private Float coin;
    @ApiModelProperty("钻石")
    private int jewel;
    @ApiModelProperty("好感度总和")
    private int likeValue;
    private Boolean award;
    private int skinID;
    private List<Mail> mailInfo;
    @ApiModelProperty(value = "是否需要绑定手机号")
    private boolean bindPhone = false;
    private String phone;
    @ApiModelProperty("配送信息")
    private UserSite site;
    // 小游戏打地鼠的次数
    private Integer miniGameCount1;
    // 小游戏大转盘次数
    private Integer miniGameCount2;
    // 小游戏老虎机次数
    private Integer miniGameCount3;
    // 小游戏大怪兽次数
    private Integer miniGameCount4;
    // 用户名
    private String userName;
    private List<ItemInfo> itemInfos;
    @ApiModelProperty("好感度")
    private UserChicken likeValues;
    @ApiModelProperty("是否已经下过蛋")
    private boolean egged;


    public LoginVo() {
    }

    public LoginVo(String accountID, int eggCount, Float coin, int jewel, int likeValue, Boolean award, int skinID, List<Mail> mailInfo, boolean bindPhone) {
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

    public Float getCoin() {
        return coin;
    }

    public void setCoin(Float coin) {
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

    public List<Mail> getMailInfo() {
        return mailInfo;
    }

    public void setMailInfo(List<Mail> mailInfo) {
        this.mailInfo = mailInfo;
    }

    public boolean isBindPhone() {
        return bindPhone;
    }

    public void setBindPhone(boolean bindPhone) {
        this.bindPhone = bindPhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserSite getSite() {
        return site;
    }

    public void setSite(UserSite site) {
        this.site = site;
    }

    public Integer getMiniGameCount1() {
        return miniGameCount1;
    }

    public void setMiniGameCount1(Integer miniGameCount1) {
        this.miniGameCount1 = miniGameCount1;
    }

    public Integer getMiniGameCount2() {
        return miniGameCount2;
    }

    public void setMiniGameCount2(Integer miniGameCount2) {
        this.miniGameCount2 = miniGameCount2;
    }

    public Integer getMiniGameCount3() {
        return miniGameCount3;
    }

    public void setMiniGameCount3(Integer miniGameCount3) {
        this.miniGameCount3 = miniGameCount3;
    }

    public Integer getMiniGameCount4() {
        return miniGameCount4;
    }

    public void setMiniGameCount4(Integer miniGameCount4) {
        this.miniGameCount4 = miniGameCount4;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ItemInfo> getItemInfos() {
        return itemInfos;
    }

    public void setItemInfos(List<ItemInfo> itemInfos) {
        this.itemInfos = itemInfos;
    }

    public UserChicken getLikeValues() {
        return likeValues;
    }

    public void setLikeValues(UserChicken likeValues) {
        this.likeValues = likeValues;
    }

    public boolean isEgged() {
        return egged;
    }

    public void setEgged(boolean egged) {
        this.egged = egged;
    }
}
