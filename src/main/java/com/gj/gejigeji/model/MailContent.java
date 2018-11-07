package com.gj.gejigeji.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 邮件内容
 */


@Document(collection = "mail_content")
public class MailContent {

    @Id
    private String Id;

    private String mailId;
    //道具ID
    private Integer itemId;
    //道具数量
    private Integer itemCount;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }
}
