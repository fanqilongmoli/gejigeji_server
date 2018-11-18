package com.gj.gejigeji.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RequestMapping("order")
@RestController
public class OrderController {


    @ApiOperation("下单")
    @PostMapping("place")
    public void place(){

    }

    @ApiOperation("取消订单")
    @PostMapping("{orderId}/submitCancel")
    public void submitCancel(@PathVariable String orderId){

    }

    @ApiOperation("获取拍卖数据")
    @GetMapping("orders")
    public void orders(@PathVariable String accountID){

    }
}
