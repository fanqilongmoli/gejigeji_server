package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message,String> {

    List<Message> findByFromAndToOrToAndFromOrderByCreateTimeDesc(String from,String to,String to1,String from2);
}
