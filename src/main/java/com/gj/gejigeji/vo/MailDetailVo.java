package com.gj.gejigeji.vo;

import com.gj.gejigeji.model.MailContent;

import java.util.List;

public class MailDetailVo {
    private String message;

    private List<MailContent> itemInfos;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MailContent> getItemInfos() {
        return itemInfos;
    }

    public void setItemInfos(List<MailContent> itemInfos) {
        this.itemInfos = itemInfos;
    }
}
