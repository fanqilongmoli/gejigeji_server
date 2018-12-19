package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.UserFeed;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserFeedRepository extends MongoRepository<UserFeed,String> {


    Optional<UserFeed> findByUserIdAndFeedId(String userId,String feedId);
}
