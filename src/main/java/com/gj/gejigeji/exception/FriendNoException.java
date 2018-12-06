package com.gj.gejigeji.exception;

/**
 * 添加好友不存在
 */
public class FriendNoException extends BaseRuntimeException {
    public FriendNoException() {
        setStatus(400);
        setCode("friend.no");
    }
}
