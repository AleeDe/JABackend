package com.AliGrp.Journal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.AliGrp.Journal.entity.HomeTestimonialEntry;

public interface HomeTestRep extends MongoRepository<HomeTestimonialEntry, String> {
    
}
