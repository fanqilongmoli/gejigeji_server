package com.gj.gejigeji.model;

import io.swagger.models.auth.In;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 鸡
 */
@Document(collection = "chicken")
public class Chicken {

    @Id
    private String Id;

    //鸡的名字
    private String name;

    //每天最大下单的个数
    private Integer maxEgg;

    private Date createTime;

    private Date updateTime;

    private Byte deleteFlag;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxEgg() {
        return maxEgg;
    }

    public void setMaxEgg(Integer maxEgg) {
        this.maxEgg = maxEgg;
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
}
