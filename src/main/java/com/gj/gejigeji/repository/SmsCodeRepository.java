package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.SmsCode;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SmsCodeRepository extends MongoRepository<SmsCode,String> {
}
