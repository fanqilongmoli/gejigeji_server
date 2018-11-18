package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.UserEgg;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEggRepository extends MongoRepository<UserEgg,String> {
}
