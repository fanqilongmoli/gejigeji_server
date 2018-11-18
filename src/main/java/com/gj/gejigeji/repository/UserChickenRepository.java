package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.UserChicken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserChickenRepository extends MongoRepository<UserChicken,String> {
}
