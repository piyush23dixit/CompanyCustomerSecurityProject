package com.example1.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example1.Collection.Company;

public interface CompanyRepo extends MongoRepository<Company, String>{


}
