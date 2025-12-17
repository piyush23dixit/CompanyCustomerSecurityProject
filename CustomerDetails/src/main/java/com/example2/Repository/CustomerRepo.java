package com.example2.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example2.Collections.Customer;

public interface CustomerRepo extends MongoRepository<Customer,String>{

}
