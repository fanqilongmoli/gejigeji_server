package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.Feed;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeedRepository extends MongoRepository<Feed,String> {
}
