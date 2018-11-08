package com.gj.gejigeji.controller;

import com.gj.gejigeji.model.Prop;
import com.gj.gejigeji.service.PropService;
import com.gj.gejigeji.vo.ActionParam;
import com.gj.gejigeji.vo.OkResult;
import com.gj.gejigeji.vo.PropBuyParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("获取所有道具")
    @PostMapping("getAll")
    public List<Prop> getAll(@RequestBody ActionParam actionParam){
        return propService.getAll(actionParam);
    }

    @ApiOperation("购买道具")
    @PostMapping("buy")
    public OkResult buy(@RequestBody PropBuyParam propBuyParam){
        return propService.buy(propBuyParam);
    }
}
