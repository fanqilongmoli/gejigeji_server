package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.RecycleRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecycleRecordRepository extends MongoRepository<RecycleRecord,String> {
}
