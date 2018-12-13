package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.ResourceConf;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResourceConfRepository extends MongoRepository<ResourceConf,String> {
}
