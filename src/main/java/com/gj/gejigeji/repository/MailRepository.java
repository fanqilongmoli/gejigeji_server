package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.Mail;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MailRepository extends MongoRepository<Mail,String> {
}
