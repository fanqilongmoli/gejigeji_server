package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,String> {
}
