package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.Theme;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ThemeRepository extends MongoRepository<Theme,String> {
}
