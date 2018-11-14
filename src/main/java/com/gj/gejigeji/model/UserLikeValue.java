package com.gj.gejigeji.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "user_likeValue")
public class UserLikeValue {
    /**
     * 后台逻辑和接口服务。亲密值总共100点。
     * 分配情况：抚摸10点，喂食30点，洗澡10点，玩耍45点，看电视5点。
     * 亲密度增加逻辑：抚摸一次2点，喂食一次10点，洗澡一次5点，玩耍一次3点，看电视一次1点。
     * 亲密度衰减逻辑：均以最近一次操作时间为基准，抚摸，每1小时衰减1点，喂食，每小时衰减2点，洗澡每小时衰减1点，玩耍每小时3点，看电视每小时1点。
     */
    @Id
    private String Id;

    // 用户id
    private String userId;

    //抚摸
    private Integer stroke;

    private Date strokeLastTime;

    // 喂食
    private Integer feed;

    private Date feedLastTime;

    //洗澡
    private Integer bathe;

    private Date batheLastTime;

    //看电视 现在当成打扫用
    private Integer tv;

    private Date tvLastTime;

    // 玩耍
    private Integer game;

    private Date gameLastTime;

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
        this.feed = feed;
    }

    //洗澡 10点
    public void setBathe(Integer bathe) {
        if (bathe > 10) {
            bathe = 10;
        }
        this.bathe = bathe;
    }

    // 看电视 5点
    public void setTv(Integer tv) {
        if (tv > 5) {
            tv = 5;
        }
        this.tv = tv;
    }

    // 玩耍 45点
    public void setGame(Integer game) {
        if (game > 45) {
            game = 45;
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
}
