package com.gj.gejigeji.exception;

/**
 * 好友关系已存
 */
public class FriendHasException extends BaseRuntimeException {
    public FriendHasException() {
        setStatus(400);
        setCode("friend.has");
    }
}
