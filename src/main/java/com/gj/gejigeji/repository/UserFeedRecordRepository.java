package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.UserFeedRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserFeedRecordRepository extends MongoRepository<UserFeedRecord,String> {
}
