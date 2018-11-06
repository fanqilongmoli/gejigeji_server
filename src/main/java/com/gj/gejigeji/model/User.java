package com.gj.gejigeji.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {
    @Id
    private String id;
    // 手机号码
    private String phone;
    // 密码
    private String password;
    //鸡蛋数量
    private Integer eggCount;
    // 金币数量
    private Integer coin;
    // 钻石数量
    private Integer jewel;
    // 好感度
    private Integer likeValue;
    // true可以领取奖励 false不能领取奖励
    private Boolean award;
    // 当前使用的皮肤ID
    private Integer skinID;
    //平台ID
    private String platformID;
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
    // 配送地址
    private String site;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEggCount() {
        return eggCount;
    }

    public void setEggCount(Integer eggCount) {
        this.eggCount = eggCount;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getJewel() {
        return jewel;
    }

    public void setJewel(Integer jewel) {
        this.jewel = jewel;
    }

    public Integer getLikeValue() {
        return likeValue;
    }

    public void setLikeValue(Integer likeValue) {
        this.likeValue = likeValue;
    }

    public Boolean getAward() {
        return award;
    }

    public void setAward(Boolean award) {
        this.award = award;
    }

    public Integer getSkinID() {
        return skinID;
    }

    public void setSkinID(Integer skinID) {
        this.skinID = skinID;
    }

    public String getPlatformID() {
        return platformID;
    }

    public void setPlatformID(String platformID) {
        this.platformID = platformID;
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

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
