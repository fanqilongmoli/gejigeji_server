package com.gj.gejigeji.controller;

import com.gj.gejigeji.repository.UserRepository;
import com.gj.gejigeji.service.UserService;
import com.gj.gejigeji.vo.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("获取金币")
    @PostMapping("getCoin")
    public GetCoinVo getCoin(@RequestBody ActionParam actionParam){
        return  userService.getCoin(actionParam);
    }

    @ApiOperation("修改昵称")
    @PostMapping("updateUserName")
    public OkResult updateUserName(@RequestBody UpdateUserNameParam updateUserNameParam){
        return userService.updateUserName(updateUserNameParam);
    }

    @ApiOperation("修改配送信息")
    @PostMapping("updateSite")
    public OkResult updateSite(@RequestBody UpdateSiteParam updateSiteParam){
        return userService.updateSite(updateSiteParam);
    }

    @ApiOperation("修改密码")
    @PostMapping("updatePassword")
    public OkResult updatePassword(@RequestBody UpdatePasswordParam updatePasswordParam){
        return userService.updatePassword(updatePasswordParam);
    }


    @ApiOperation("用户认证")
    @PostMapping("authentication")
    public OkResult authentication(@RequestBody AuthenticationParam authenticationParam){
        return userService.authentication(authenticationParam);
    }

    @ApiOperation("下蛋")
    @RequestMapping("egg")
    public EggCountVo egg(@RequestBody ActionParam actionParam){
        return userService.egg(actionParam);
    }


}
