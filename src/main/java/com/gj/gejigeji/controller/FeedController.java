package com.gj.gejigeji.controller;

import com.gj.gejigeji.model.Feed;
import com.gj.gejigeji.service.FeedService;
import com.gj.gejigeji.vo.FeedBuyParam;
import com.gj.gejigeji.vo.OkResult;
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
}
