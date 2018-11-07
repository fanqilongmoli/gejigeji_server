package com.gj.gejigeji.controller;

import com.gj.gejigeji.service.PropService;
import com.gj.gejigeji.vo.OkResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("prop")
public class PropController {

    @Autowired
    PropService propService;

    @ApiOperation(value ="道具测试添加")
    @GetMapping("propTest")
    public OkResult propTest(@RequestParam String userId){
       return propService.propTest(userId);
    }
}
