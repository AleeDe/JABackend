package com.AliGrp.Journal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.AliGrp.Journal.entity.JournalEntry;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {
    List<JournalEntry> findByCreatedBy(String createdBy);
    Optional<JournalEntry> findById(String id);
}
