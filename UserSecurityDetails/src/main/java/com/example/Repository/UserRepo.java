package com.example.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.Collections.User;

public interface UserRepo extends MongoRepository<User, String>{

     public Optional<User> findByUsername(String username);
}
