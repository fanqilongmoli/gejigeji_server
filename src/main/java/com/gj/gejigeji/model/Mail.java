package com.gj.gejigeji.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * 邮件
 */
@Document(collection = "mail")
public class Mail {

    @Id
    private String id;
    // 用户id
    @ApiModelProperty(value = "用户id")
    private String userId;
    // 邮件类型
    @ApiModelProperty(value = "邮件类型")
    private Integer mailType;
    // 是否已读
    @ApiModelProperty(value = "是否已读")
    private Boolean read;
    @ApiModelProperty(value = "是否已经领取奖励(邮件类型为奖励类型)")
    private Boolean get;
    // 时间
    @ApiModelProperty(value = "时间")
    private Date mailTime;
    // 邮件图标
    @ApiModelProperty(value = "邮件图标")
    private String logo;
    // 邮件标题
    @ApiModelProperty(value = "邮件标题")
    private String title;
    // 邮件详细内容
    @ApiModelProperty(value = "邮件详细内容")
    private String message;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getMailType() {
        return mailType;
    }

    public void setMailType(Integer mailType) {
        this.mailType = mailType;
    }

    public Date getMailTime() {
        return mailTime;
    }

    public void setMailTime(Date mailTime) {
        this.mailTime = mailTime;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getGet() {
        return get;
    }

    public void setGet(Boolean get) {
        this.get = get;
    }
}
