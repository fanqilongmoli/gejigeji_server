package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.UserTheme;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserThemeRepository extends MongoRepository<UserTheme,String> {
}
