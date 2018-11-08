package com.gj.gejigeji.service;

import com.gj.gejigeji.model.Henhouse;
import com.gj.gejigeji.repository.HenhouseRepository;
import com.gj.gejigeji.vo.HenhouseBuyParam;
import com.gj.gejigeji.vo.OkResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HenhouseService {

    @Autowired
    HenhouseRepository henhouseRepository;

    public List<Henhouse> getAll() {
        return henhouseRepository.findAll();
    }

    public OkResult buy(HenhouseBuyParam henhouseBuyParam) {
        // TODO 鸡舍 购买需要完成
        return new OkResult(true);
    }
}
