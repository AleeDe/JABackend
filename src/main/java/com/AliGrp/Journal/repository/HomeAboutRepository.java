package com.AliGrp.Journal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.AliGrp.Journal.entity.HomeAbout;

public interface HomeAboutRepository extends MongoRepository<HomeAbout, String> {
}
