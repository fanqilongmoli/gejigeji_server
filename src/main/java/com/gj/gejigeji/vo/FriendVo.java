package com.gj.gejigeji.vo;

import java.util.Date;

public class FriendVo {

    private String friendId;

    private String userName;

    private String ps;

    private Date lastMsgTime;

    public FriendVo() {
    }

    public FriendVo(String friendId, String userName, String ps, Date lastMsgTime) {
        this.friendId = friendId;
        this.userName = userName;
        this.ps = ps;
        this.lastMsgTime = lastMsgTime;
    }

    public FriendVo(String friendId, String userName, String ps) {
        this.friendId = friendId;
        this.userName = userName;
        this.ps = ps;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public Date getLastMsgTime() {
        return lastMsgTime;
    }

    public void setLastMsgTime(Date lastMsgTime) {
        this.lastMsgTime = lastMsgTime;
    }
}
