package com.gj.gejigeji.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageHisVo {


    @ApiModelProperty("发送者id")
    private String from;
    @ApiModelProperty("发送者用户名")
    private String fromName;
    @ApiModelProperty("接受者id")
    private String to;
    @ApiModelProperty("接收者用户名")
    private String toName;
    @ApiModelProperty("消息状态")
    private Byte status;
    @ApiModelProperty("消息类型")
    private Integer msgType;
    @ApiModelProperty("消息内容")
    private String content;
    @ApiModelProperty("消息时间")
    private Date createTime;
}
