package com.gj.gejigeji.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 用户鸡蛋表
 */
@Document(collection = "user_egg")
public class UserEgg {

    @Id
    private String Id;

    //用户id
    @ApiModelProperty("用户id")
    private String userId;

    //饲料id
    @ApiModelProperty("饲料id")
    private String feedId;

    //个数
    @ApiModelProperty("总的鸡蛋个数")
    private Integer amount;

    @ApiModelProperty("鸡蛋冻结的个数")
    private Integer freeze;

    //创建时间
    @ApiModelProperty("创建时间")
    private Date createTime;

    //跟新时间
    @ApiModelProperty("创建时间")
    private Date updateTime;

    //删除标志位
    @ApiModelProperty("创建时间")
    private Byte deleteFlag;


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

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getFreeze() {
        return freeze;
    }

    public void setFreeze(Integer freeze) {
        this.freeze = freeze;
    }
}
