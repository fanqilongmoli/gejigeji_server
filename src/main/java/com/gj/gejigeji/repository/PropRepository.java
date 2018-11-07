package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.Prop;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PropRepository extends MongoRepository<Prop,String> {
}
