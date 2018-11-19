package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.UserEgg;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserEggRepository extends MongoRepository<UserEgg,String> {

    Optional<UserEgg> findUserEggByUserIdAndFeedId(String userId, String feedId);
}
