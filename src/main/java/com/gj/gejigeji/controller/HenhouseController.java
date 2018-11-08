package com.gj.gejigeji.controller;

import com.gj.gejigeji.model.Henhouse;
import com.gj.gejigeji.service.HenhouseService;
import com.gj.gejigeji.vo.HenhouseBuyParam;
import com.gj.gejigeji.vo.OkResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("henhouse")
public class HenhouseController {

    @Autowired
    HenhouseService henhouseService;

    @GetMapping("getAll")
    public List<Henhouse> getAll(){
        return  henhouseService.getAll();
    }

    @PostMapping("buy")
    public OkResult buy(@RequestBody HenhouseBuyParam henhouseBuyParam){
        return  henhouseService.buy(henhouseBuyParam);
    }

}
