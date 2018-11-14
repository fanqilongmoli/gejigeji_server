package com.gj.gejigeji.controller;

import com.gj.gejigeji.model.Mail;
import com.gj.gejigeji.service.MailService;
import com.gj.gejigeji.vo.MailDetailParam;
import com.gj.gejigeji.vo.MailDetailVo;
import com.gj.gejigeji.vo.MailReadParam;
import com.gj.gejigeji.vo.OkResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    MailService mailService;

    @PostMapping("mailDetail")
    @ApiOperation(value = "获取邮件详情（点击邮件详情时，设置邮件为已读")
    public MailDetailVo mailDetail(@RequestBody MailDetailParam mailDetailParam){
        return mailService.mailDetail(mailDetailParam);
    }

    @PostMapping("mailGet")
    @ApiOperation("获取邮件奖励---参数和返回暂定")
    public OkResult mailGet(@RequestBody MailDetailParam mailDetailParam){
        return mailService.mailGet(mailDetailParam);
    }


    @PostMapping("mailAll")
    @ApiOperation(value = "获取用户的全部邮件")
    public List<Mail> mailAll(@RequestBody MailReadParam mailReadParam){
        return mailService.mailAll(mailReadParam);
    }

    @PostMapping("mailRead")
    @ApiOperation(value = "标记邮件为全读")
    public OkResult mailRead(@RequestBody MailReadParam mailReadParam){
        return mailService.mailRead(mailReadParam);
    }

    @GetMapping("mailTest")
    @ApiOperation(value = "邮件测试添加")
    public OkResult mailTest(@RequestParam String userId){

        return mailService.mailTest(userId);
    }


}
