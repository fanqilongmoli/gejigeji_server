package com.gj.gejigeji.controller;

import com.gj.gejigeji.service.GameService;
import com.gj.gejigeji.vo.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("game")
public class GameController {

    @Autowired
    GameService gameService;

    @ApiOperation(value = "获取用户所有小游戏可玩的次数")
    @PostMapping("gameCount")
    public GameCountVo gameCount(@RequestBody ActionParam actionParam){
       return gameService.gameCount(actionParam);
    }

    @ApiOperation(value = "打地鼠进入 进入游戏后扣除次数")
    @PostMapping("ddsStart")
    public OkResult ddsStart(@RequestBody ActionParam actionParam){
        return gameService.ddsStart(actionParam);
    }

    @ApiOperation(value = "打地鼠结算 打地鼠完成时结算")
    @PostMapping("ddsEnd")
    public OkResult ddsEnd(@RequestBody DDSEndParam ddsEndParam){
        return gameService.ddsEnd(ddsEndParam);
    }

    @ApiOperation(value = "老虎机开始请求")
    @PostMapping("lhjStart")
    public OkResult lhjStart(@RequestBody ActionParam actionParam){
        return gameService.lhjStart(actionParam);
    }

    @ApiOperation(value = "大转盘转动")
    @PostMapping("dzbStart")
    public GameResultVo dzbStart(@RequestBody ActionParam actionParam){
        return gameService.dzbStart(actionParam);
    }

    @ApiOperation(value = "打怪兽进入(允许时扣除次数)")
    @PostMapping("dgsStart")
    public OkResult dgsStart(@RequestBody ActionParam actionParam){
        return gameService.dgsStart(actionParam);
    }

    @ApiOperation(value = "打怪兽进入(允许时扣除次数)")
    @PostMapping("dgsEnd")
    public GameResultVo dgsEnd(@RequestBody DGSEndParam dgsEndParam){
        return gameService.dgsEnd(dgsEndParam);
    }



}
