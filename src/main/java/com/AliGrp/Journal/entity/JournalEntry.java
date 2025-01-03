package com.AliGrp.Journal.entity;

import lombok.Data;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "journal")
public class JournalEntry {
    @Id
    private String id;

    private String title;
    private String content;
    private String createdBy;
    private LocalDate createdDate;
}