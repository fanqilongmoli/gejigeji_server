package com.gj.gejigeji.controller;

import com.gj.gejigeji.service.RegService;
import com.gj.gejigeji.vo.OkResult;
import com.gj.gejigeji.vo.RegParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/reg")
@Api(value = "用户注册")
public class RegController {

    @Autowired
    RegService regService;

    @ApiOperation(value = "用户注册")
    @PostMapping
    public OkResult register(@RequestBody @Valid RegParam regParam) {
        return regService.register(regParam);
    }
}
