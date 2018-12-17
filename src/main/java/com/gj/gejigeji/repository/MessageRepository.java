package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.Message;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message,String> {

    List<Message> findByFromAndToOrToAndFromOrderByCreateTimeDesc(String from, String to, String to1, String from1, Pageable pageable);

    List<Message> findByFromAndToAndStatus(String from, String to, Byte status);
}
