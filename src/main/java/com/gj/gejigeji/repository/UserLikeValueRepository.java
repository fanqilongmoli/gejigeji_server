package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.UserLikeValue;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserLikeValueRepository extends MongoRepository<UserLikeValue,String> {
}
