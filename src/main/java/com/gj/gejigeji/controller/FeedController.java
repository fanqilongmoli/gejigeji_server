package com.gj.gejigeji.controller;

import com.gj.gejigeji.model.Feed;
import com.gj.gejigeji.service.FeedService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
