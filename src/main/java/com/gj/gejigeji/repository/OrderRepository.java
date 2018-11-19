package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order,String> {

    Page<Order> findByOrderStateIn(Pageable pageable, List<Byte> OrderState);

    Page<Order> findByUserIdAndOrderStateInOrderByCreateTimeDesc(Pageable pageable,String userId ,List<Byte> OrderState);
}
