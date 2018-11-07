package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.UserProp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserPropRepository extends MongoRepository<UserProp,String> {

}
