package com.gj.gejigeji.controller;

import com.gj.gejigeji.annotation.SysLog;
import com.gj.gejigeji.service.GameService;
import com.gj.gejigeji.vo.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("game")
public class GameController {

    @Autowired
    GameService gameService;

    @ApiOperation(value = "获取用户所有小游戏可玩的次数 U3D使用")
    @PostMapping("gameCount")
    public GameCountVo gameCount(@RequestBody ActionParam actionParam){
       return gameService.gameCount(actionParam);
    }

    @ApiOperation(value = "打地鼠进入 进入游戏后扣除次数 U3D使用")
    @PostMapping("ddsStart")
    public GameResultVo ddsStart(@RequestBody ActionParam actionParam){
        return gameService.ddsStart(actionParam);
    }

    @ApiOperation(value = "打地鼠结算 打地鼠完成时结算 U3D使用")
    @PostMapping("ddsEnd")
    public GameResultVo ddsEnd(@RequestBody DDSEndParam ddsEndParam){
        return gameService.ddsEnd(ddsEndParam);
    }

    @ApiOperation(value = "老虎机进入请求 U3D使用")
    @PostMapping("lhjStart")
    public GameResultVo lhjStart(@RequestBody ActionParam actionParam){
        return gameService.lhjStart(actionParam);
    }

    @ApiOperation(value = "大转盘进入请求 U3D使用")
    @PostMapping("dzbStart")
    public GameResultVo dzbStart(@RequestBody ActionParam actionParam){
        return gameService.dzbStart(actionParam);
    }

    @ApiOperation(value = "打怪兽进入(允许时扣除次数) U3D使用")
    @PostMapping("dgsStart")
    public GameResultVo dgsStart(@RequestBody ActionParam actionParam){
        return gameService.dgsStart(actionParam);
    }

    @ApiOperation(value = "打怪兽结束 U3D使用")
    @PostMapping("dgsEnd")
    public GameResultVo dgsEnd(@RequestBody DGSEndParam dgsEndParam){
        return gameService.dgsEnd(dgsEndParam);
    }

    @SysLog
    @ApiOperation("每个游戏每天各有3次免费的机会，超过免费机会，每次10个金币（game：游戏类型 1打地鼠 2大转盘 3老虎机 4打怪兽）U3D使用")
    @PostMapping("checkGame4Free/{game}")
    public OkResult checkGame4Free(@RequestBody ActionParam actionParam, @PathVariable String game){
        return gameService.checkGame4Free(actionParam,game);
    }

}
