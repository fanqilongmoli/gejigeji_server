package com.gj.gejigeji.controller;

import com.gj.gejigeji.service.ActionService;
import com.gj.gejigeji.vo.ActionParam;
import com.gj.gejigeji.vo.ActionVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("action")
public class ActionController {

    @Autowired
    ActionService actionService;

    @ApiOperation(value = "抚摸")
    @PostMapping("stroke")
    public ActionVo stroke(@RequestBody ActionParam actionParam){

        return actionService.stroke(actionParam);
    }


    @ApiOperation(value = "洗澡")
    @PostMapping("bathe")
    public ActionVo bathe(@RequestBody ActionParam actionParam){

        return actionService.bathe(actionParam);
    }

    @ApiOperation(value = "打扫")
    @PostMapping("clean")
    public ActionVo clean(@RequestBody ActionParam actionParam){

        return actionService.clean(actionParam);
    }




}
