package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.Friends;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FriendsRepository extends MongoRepository<Friends,String> {
}
