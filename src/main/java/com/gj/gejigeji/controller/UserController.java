package com.gj.gejigeji.controller;

import com.gj.gejigeji.service.UserService;
import com.gj.gejigeji.vo.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("获取用户的金币余额")
    @PostMapping("getCoin")
    public GetCoinVo getCoin(@RequestBody ActionParam actionParam){
        return  userService.getCoin(actionParam);
    }

    @ApiOperation("用户获取金币--->随机添加同事返回用户当前的金币数量")
    @PostMapping("addCoin")
    public GetCoinVo addCoin(@RequestBody ActionParam actionParam){
        return userService.addCoin(actionParam);
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
    @PostMapping("egg")
    public EggCountVo egg(@RequestBody ActionParam actionParam){
        return userService.egg(actionParam);
    }

    @ApiOperation("获取用户的鸡蛋")
    @PostMapping("userEggs")
    public List<UserEggVo> userEggs(@RequestBody ActionParam actionParam){
        return userService.userEggs(actionParam);
    }

}
