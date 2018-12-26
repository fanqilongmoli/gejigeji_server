package com.gj.gejigeji.controller;

import com.gj.gejigeji.service.LoginService;
import com.gj.gejigeji.vo.BindPhoneParam;
import com.gj.gejigeji.vo.Login3rdParam;
import com.gj.gejigeji.vo.LoginParam;
import com.gj.gejigeji.vo.LoginVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @ApiOperation(value = "登录 U3D使用")
    @PostMapping("login")
    public LoginVo login(@RequestBody LoginParam loginParam){
        return loginService.login(loginParam);
    }

    @ApiOperation(value = "第三方登录 U3D使用")
    @PostMapping("login3rd")
    public LoginVo loginForOther(@RequestBody Login3rdParam login3rdParam){
        return loginService.login3rd(login3rdParam);
    }

    @ApiOperation(value = "第三方登录绑定手机 U3D使用")
    @PostMapping("bindPhone")
    public LoginVo bindPhone(@RequestBody BindPhoneParam bindPhoneParam){
        return loginService.bindPhone(bindPhoneParam);
    }

}
