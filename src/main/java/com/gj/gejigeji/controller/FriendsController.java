package com.gj.gejigeji.controller;

import com.gj.gejigeji.service.FriendsService;
import com.gj.gejigeji.vo.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("friends")
public class FriendsController {

    @Autowired
    FriendsService friendsService;

    @ApiOperation("获取好友列表")
    @GetMapping("getFriends/{accountID}")
    public List<FriendVo> getFriends(@PathVariable String accountID){
        return friendsService.getFriends(accountID);
    }

    @ApiOperation("获取好友申请列表")
    @GetMapping("getApply/{accountID}")
    public List<FriendVo> getApply(@PathVariable String accountID){
        return friendsService.getApply(accountID);
    }

    @ApiOperation("添加好友申请")
    @PostMapping("addFriend")
    public OkResult addFriend(@RequestBody AddFriendParam addFriendParam){
        return friendsService.addFriend(addFriendParam);
    }

    @ApiOperation("同意好友请求/忽略好友请求")
    @PostMapping("applyFriend")
    public OkResult applyFriend(@RequestBody ApplyFriendParam applyFriendParam){
        return friendsService.applyFriend(applyFriendParam);
    }

    @ApiOperation("赠送好友鸡蛋")
    @PostMapping("sendEgg")
    public OkResult sendEgg(@RequestBody SendEggParam sendEggParam){
        return friendsService.sendEgg(sendEggParam);
    }

    @ApiOperation("赠送好友金币")
    @PostMapping("sendCoin")
    public OkResult sendCoin(@RequestBody SendCoinParam sendCoinParam){
        return friendsService.sendCoin(sendCoinParam);
    }



}
