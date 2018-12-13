package com.gj.gejigeji.controller;

import com.gj.gejigeji.model.ResourceConf;
import com.gj.gejigeji.service.ResourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("resource")
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @ApiOperation("获取H5资源配置")
    @GetMapping
    public List<ResourceConf> getResource(){
        return resourceService.getResource();
    }
}
