package com.AliGrp.Journal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.AliGrp.Journal.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    
}
