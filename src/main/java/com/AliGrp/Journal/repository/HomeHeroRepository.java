package com.AliGrp.Journal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.AliGrp.Journal.entity.HomeHeroEntry;

public interface HomeHeroRepository extends MongoRepository<HomeHeroEntry, String> {

    
}