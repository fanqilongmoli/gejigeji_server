package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.UserLuck;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserLuckRepository extends MongoRepository<UserLuck,String> {



    List<UserLuck> findByUserIdAndDeleteFlag(String userId,Byte deleteFlag);
}
