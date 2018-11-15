package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.UserAuthentication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAuthenticationRepository extends MongoRepository<UserAuthentication,String> {
}
