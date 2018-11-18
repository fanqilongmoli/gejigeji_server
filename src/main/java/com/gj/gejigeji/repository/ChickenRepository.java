package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.Chicken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChickenRepository extends MongoRepository<Chicken,String> {
}
