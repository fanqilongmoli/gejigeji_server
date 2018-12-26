package com.gj.gejigeji.controller;

import com.gj.gejigeji.model.Theme;
import com.gj.gejigeji.service.ThemeService;
import com.gj.gejigeji.vo.ActionParam;
import com.gj.gejigeji.vo.OkResult;
import com.gj.gejigeji.vo.ThemeBuyParam;
import com.gj.gejigeji.vo.ThemeVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("theme")
public class ThemeController {

    @Autowired
    ThemeService themeService;


    @ApiOperation(value = "获取所有的主题(鸡舍就是主题) U3D使用")
    @PostMapping("getAll")
    public List<ThemeVo> getAll(@RequestBody ActionParam actionParam){
        return themeService.getAll(actionParam);
    }


    @ApiOperation("购买主题")
    @PostMapping("buy")
    public OkResult buy(@RequestBody ThemeBuyParam themeBuyParam){
        return themeService.buy(themeBuyParam);
    }
}
