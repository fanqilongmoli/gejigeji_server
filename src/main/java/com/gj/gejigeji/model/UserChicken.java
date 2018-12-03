package com.gj.gejigeji.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 用户------>>小鸡  表 (演示版)
 */
@Document(collection = "user_chicken")
public class UserChicken {

    @Id
    private String Id;
    // 用户id
    private String userId;
    //鸡的id
    private String chickenId;
    //每天下蛋个数记录
    private Integer dayEgg;
    //抚摸
    @ApiModelProperty("抚摸")
    private Integer stroke;

    private Date strokeLastTime;

    // 喂食
    @ApiModelProperty("喂食")
    private Integer feed;

    private Date feedLastTime;

    //洗澡
    @ApiModelProperty("洗澡")
    private Integer bathe;

    private Date batheLastTime;

    //看电视 现在当成打扫用
    @ApiModelProperty("看电视 现在当成打扫用")
    private Integer tv;

    private Date tvLastTime;

    // 玩耍
    @ApiModelProperty("玩耍")
    private Integer game;

    private Date gameLastTime;


    public Integer getStroke() {
        return stroke;
    }


    //抚摸10点
    public void setStroke(Integer stroke) {
        if (stroke > 10) {
            stroke = 10;
        }
        if (stroke < 0) {
            stroke = 0;
        }
        this.stroke = stroke;
    }

    //喂食30点
    public void setFeed(Integer feed) {
        if (feed > 30) {
            feed = 30;
        }

        if (feed < 0) {
            feed = 0;
        }
        this.feed = feed;
    }

    //洗澡 10点
    public void setBathe(Integer bathe) {
        if (bathe > 10) {
            bathe = 10;
        }

        if (bathe < 0) {
            bathe = 0;
        }
        this.bathe = bathe;
    }

    // 看电视 5点
    public void setTv(Integer tv) {
        if (tv > 5) {
            tv = 5;
        }
        if (tv < 0) {
            tv = 0;
        }
        this.tv = tv;
    }

    // 玩耍 45点
    public void setGame(Integer game) {
        if (game > 45) {
            game = 45;
        }
        if (game < 45) {
            game = 0;
        }
        this.game = game;
    }

    public Integer getFeed() {
        return feed;
    }


    public Integer getBathe() {
        return bathe;
    }


    public Integer getTv() {
        return tv;
    }


    public Date getStrokeLastTime() {
        return strokeLastTime;
    }

    public void setStrokeLastTime(Date strokeLastTime) {
        this.strokeLastTime = strokeLastTime;
    }

    public Date getFeedLastTime() {
        return feedLastTime;
    }

    public void setFeedLastTime(Date feedLastTime) {
        this.feedLastTime = feedLastTime;
    }

    public Date getBatheLastTime() {
        return batheLastTime;
    }

    public void setBatheLastTime(Date batheLastTime) {
        this.batheLastTime = batheLastTime;
    }

    public Date getTvLastTime() {
        return tvLastTime;
    }

    public void setTvLastTime(Date tvLastTime) {
        this.tvLastTime = tvLastTime;
    }

    public Integer getGame() {
        return game;
    }


    public Date getGameLastTime() {
        return gameLastTime;
    }

    public void setGameLastTime(Date gameLastTime) {
        this.gameLastTime = gameLastTime;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChickenId() {
        return chickenId;
    }

    public void setChickenId(String chickenId) {
        this.chickenId = chickenId;
    }

    public Integer getDayEgg() {
        return dayEgg;
    }

    public void setDayEgg(Integer dayEgg) {
        this.dayEgg = dayEgg;
    }
}
