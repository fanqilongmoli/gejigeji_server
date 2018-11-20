package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.RecycleRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RecycleRecordRepository extends MongoRepository<RecycleRecord,String> {

    Optional<RecycleRecord> findTopByOrderByCreateTimeDesc();
}
