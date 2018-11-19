package com.gj.gejigeji.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 回收记录表
 */
@Document(collection = "recycle_record")
public class RecycleRecord {

    private String Id;

    private String userId;

    private Integer price;

    private Integer vol;

    private Date createTime;

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getVol() {
        return vol;
    }

    public void setVol(Integer vol) {
        this.vol = vol;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
