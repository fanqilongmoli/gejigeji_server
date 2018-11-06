package com.gj.gejigeji.controller;

import com.gj.gejigeji.model.Feed;
import com.gj.gejigeji.model.Theme;
import com.gj.gejigeji.service.ThemeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("theme")
public class ThemeController {

    @Autowired
    ThemeService themeService;


    @ApiOperation(value = "获取所有的饲料种类")
    @GetMapping("getAll")
    public List<Theme> getAll(){
        return themeService.getAll();
    }
}
