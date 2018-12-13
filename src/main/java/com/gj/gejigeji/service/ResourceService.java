package com.gj.gejigeji.service;

import com.gj.gejigeji.model.ResourceConf;
import com.gj.gejigeji.repository.ResourceConfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    ResourceConfRepository resourceConfRepository;

    /**
     * 获取资源配置
     *
     * @return
     */
    public List<ResourceConf> getResource() {

        return resourceConfRepository.findAll();
    }
}
