package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.UserFeed;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserFeedRepository extends MongoRepository<UserFeed,String> {

}
