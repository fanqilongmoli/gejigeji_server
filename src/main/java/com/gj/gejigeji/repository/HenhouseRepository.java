package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.Henhouse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HenhouseRepository extends MongoRepository<Henhouse,String> {
}
