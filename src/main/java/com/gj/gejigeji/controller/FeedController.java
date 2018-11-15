package com.gj.gejigeji.controller;

import com.gj.gejigeji.model.Feed;
import com.gj.gejigeji.service.FeedService;
import com.gj.gejigeji.vo.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("feed")
public class FeedController {

    @Autowired
    FeedService feedService;

    @ApiOperation(value = "获取所有的饲料种类")
    @GetMapping("getAll")
    public List<Feed> getAll(){
        return feedService.getAll();
    }

    @ApiOperation(value = "购买饲料")
    @PostMapping("buy")
    public OkResult buy(@RequestBody FeedBuyParam feedBuyParam){
        return feedService.buy(feedBuyParam);
    }

    @ApiOperation("获取用户自己购买的饲料")
    @PostMapping("get")
    public List<FeedListVo> get(@RequestBody ActionParam actionParam){
        return feedService.get(actionParam);
    }

    @ApiOperation(value = "饲料添加测试")
    @GetMapping("feedTest")
    public OkResult feedTest(@RequestParam String userId){
        return feedService.feedTest(userId);
    }

    @ApiOperation(value = "喂食")
    @PostMapping("feeding")
    public FeedingVo feeding(@RequestBody FeedingParam feedingParam){
        return feedService.feeding(feedingParam);
    }

}
