package com.gj.gejigeji.controller;

import com.gj.gejigeji.vo.AddFriendVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("friends")
public class FriendsController {

    @ApiOperation("获取好友列表")
    @GetMapping("getFriends/{accountID}")
    public void getFriends(@PathVariable String accountID){

    }

    @ApiOperation("获取好友申请列表")
    @GetMapping("getApply/{accountID}")
    public void getApply(@PathVariable String accountID){

    }

    @ApiOperation("添加好友")
    @PostMapping("addFriend")
    public void addFriend(@RequestBody AddFriendVo addFriendVo){

    }

    @ApiOperation("同意好友请求/忽略好友请求")
    @PostMapping("applyFriend")
    public void applyFriend(){

    }

    @ApiOperation("赠送好友鸡蛋")
    @PostMapping("sendEgg")
    public void sendEgg(){

    }

    @ApiOperation("赠送好友金币")
    @PostMapping("sendCoin")
    public void sendCoin(){

    }



}
