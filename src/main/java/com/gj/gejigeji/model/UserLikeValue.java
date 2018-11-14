package com.gj.gejigeji.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    // 喂食
    private Integer feed;

    //洗澡
    private Integer bathe;

    //看电视
    private Integer tv;

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

    public void setStroke(Integer stroke) {
        this.stroke = stroke;
    }

    public Integer getFeed() {
        return feed;
    }

    public void setFeed(Integer feed) {
        this.feed = feed;
    }

    public Integer getBathe() {
        return bathe;
    }

    public void setBathe(Integer bathe) {
        this.bathe = bathe;
    }

    public Integer getTv() {
        return tv;
    }

    public void setTv(Integer tv) {
        this.tv = tv;
    }
}
