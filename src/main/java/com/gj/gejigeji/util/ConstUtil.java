package com.gj.gejigeji.util;

/**
 * 全局静态常量
 */
public class ConstUtil {

    // 删除
    public static final Byte Delete_Flag_Yes = 1;
    // 未删除
    public static final Byte Delete_Flag_No = 0;

    // 消息未读
    public static final Byte MSG_UNREAD = 1;
    // 消息已读
    public static final Byte MSG_READ = 0;


    public static final int SMS_CODE_REG = 1;

    // 邮件通知类型
    public static final int MAIL_TYPE_TZ = 1;
    // 邮件奖励类型
    public static final int MAIL_TYPE_JL = 2;


    // 订单 打开 全部未完成
    public static final Byte Order_Open = 0;
    // 订单 关闭 全部完成
    public static final Byte Order_Close_Finish_All= 1;
    // 订单 关闭
    public static final Byte Order_Close = 2;
    // 订单 打开 部分完成
    public static final Byte Order_Open_Finish_Part = 3;
    // 订单 关闭 部分完成
    public static final Byte Order_Close_Finish_Part = 4;

    // 好友 申请状态
    public static final int FRIEND_APPLY = 0;
    // 好友 好友状态
    public static final int FRIEND_OK = 1;
    // 好友 忽略状态
    public static final int FRIEND_HIND = 2;



    //时间格式
    public static final String yyyyMMddHHmm = "yyyy-MM-dd HH:mm";

}
