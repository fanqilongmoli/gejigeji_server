package com.gj.gejigeji.controller;

import com.gj.gejigeji.model.Henhouse;
import com.gj.gejigeji.service.HenhouseService;
import com.gj.gejigeji.vo.HenhouseBuyParam;
import com.gj.gejigeji.vo.OkResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("henhouse")
@Api("鸡舍")
public class HenhouseController {

    @Autowired
    HenhouseService henhouseService;

    @ApiOperation("获取全部的鸡舍")
    @GetMapping("getAll")
    public List<Henhouse> getAll(){
        return  henhouseService.getAll();
    }

    @ApiOperation("购买鸡舍")
    @PostMapping("buy")
    public OkResult buy(@RequestBody HenhouseBuyParam henhouseBuyParam){
        return  henhouseService.buy(henhouseBuyParam);
    }

}
