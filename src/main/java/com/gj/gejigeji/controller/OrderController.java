package com.gj.gejigeji.controller;

import com.gj.gejigeji.model.AuctionChart;
import com.gj.gejigeji.model.Order;
import com.gj.gejigeji.service.OrderService;
import com.gj.gejigeji.vo.GetChartParam;
import com.gj.gejigeji.vo.OkResult;
import com.gj.gejigeji.vo.PageParam;
import com.gj.gejigeji.vo.PlaceParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("order")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;


    @ApiOperation("下单")
    @PostMapping("place")
    public OkResult place(@RequestBody PlaceParam  placeParam){
        return  orderService.place(placeParam);
    }

    @ApiOperation("取消订单")
    @PostMapping("/{accountID}/{orderId}/submitCancel")
    public OkResult submitCancel(@PathVariable String accountID,@PathVariable String orderId){
        return orderService.submitCancel(accountID,orderId);
    }

    @ApiOperation("获取用户未完成的订单")
    @PostMapping("openOrders/{accountID}")
    public Page<Order> openOrders(@PathVariable String accountID, @RequestBody PageParam pageParam){
            return orderService.openOrders(accountID,pageParam);
    }

    @ApiOperation("获取用户已完成的订单")
    @PostMapping("successOrders/{accountID}")
    public Page<Order> successOrders(@PathVariable String accountID,@RequestBody PageParam pageParam){
        return orderService.successOrders(accountID,pageParam);
    }

    @ApiOperation("获取所有的拍卖数据列表")
    @PostMapping("orders")
    public Page<Order> orders(@RequestBody PageParam pageParam){
        return orderService.orders(pageParam);
    }

    @ApiOperation("获取对应的 月 日 图表")
    @PostMapping("getChart")
    public List<AuctionChart> getChart(@RequestBody GetChartParam getChartParam){
        return orderService.getChart(getChartParam);
    }

}
