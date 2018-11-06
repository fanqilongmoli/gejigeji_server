package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.MailContent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MailContentRepository extends MongoRepository<MailContent,String> {
}
