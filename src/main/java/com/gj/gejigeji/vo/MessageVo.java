package com.gj.gejigeji.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 聊天消息对象
 */
public class MessageVo implements Serializable {
    //发送者
    private String from;
    //接收者
    private String to;
    //消息类型
    private Integer msgType;
    //消息内容  第一版只存在文字
    private String content;
    //消息时间
    private Date createTime;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
