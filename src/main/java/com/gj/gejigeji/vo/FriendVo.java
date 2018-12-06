package com.gj.gejigeji.vo;

public class FriendVo {

    private String friendId;

    private String userName;

    private String ps;

    public FriendVo() {
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
}
