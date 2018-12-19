package com.gj.gejigeji.controller;

import com.gj.gejigeji.model.UserLuck;
import com.gj.gejigeji.service.LuckService;
import com.gj.gejigeji.vo.ActionParam;
import com.gj.gejigeji.vo.LuckParam;
import com.gj.gejigeji.vo.LuckVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("luck")
public class LuckController {

    @Autowired
    LuckService luckService;

    @ApiOperation("获取用户抽奖位置")
    @GetMapping("{accountId}")
    public List<UserLuck>  getLuckNum(@PathVariable String accountId){
        return luckService.getLuckNum(accountId);
    }


    @ApiOperation("抽奖请求")
    @PostMapping
    public LuckVo luck(@RequestBody LuckParam luckParam){
        return luckService.luck(luckParam);
    }
}
