package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface AddressRepository extends MongoRepository<Address,String> {

}
