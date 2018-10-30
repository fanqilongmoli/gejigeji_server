package com.gj.gejigeji.controller;

import com.gj.gejigeji.service.SmsServices;
import com.gj.gejigeji.vo.OkResult;
import com.gj.gejigeji.vo.SmsParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("sms")
public class SmsController {

    @Autowired
    SmsServices smsServices;

    @PostMapping("reg")
    @ApiOperation(value = "注册获取验证码")
    public @ResponseBody OkResult register(@RequestBody SmsParam smsParam) {
        return smsServices.register(smsParam);
    }
}
