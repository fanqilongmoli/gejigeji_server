package com.gj.gejigeji.controller;

import com.gj.gejigeji.service.SwitchSkinService;
import com.gj.gejigeji.vo.OkResult;
import com.gj.gejigeji.vo.SwitchSkinParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SwitchSkinController {

    @Autowired
    SwitchSkinService switchSkinService;

    @ApiOperation(value = "切换皮肤")
    @PostMapping("switchSkin")
    public OkResult switchSkin(@RequestBody SwitchSkinParam switchSkinParam){
        return switchSkinService.switchSkin(switchSkinParam);
    }
}
