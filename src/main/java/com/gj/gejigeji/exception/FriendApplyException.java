package com.gj.gejigeji.exception;

/**
 * 添加好友不存在
 */
public class FriendApplyException extends BaseRuntimeException {
    public FriendApplyException() {
        setStatus(400);
        setCode("friend.apply");
    }
}
