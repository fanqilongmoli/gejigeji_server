package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message,String> {

    List<Message> findByFromAndToOrToAndFromOrderByCreateTimeAsc(String from, String to, String to1, String from1, Pageable pageable);
}
