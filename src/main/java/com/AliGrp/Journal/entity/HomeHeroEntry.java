package com.AliGrp.Journal.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "homeHero")
public class HomeHeroEntry {
    @Id
    private String id;
    @Indexed(unique = true)
    private String title;
    private String subtitle;
    private String description;
    private String buttonText;
    private String videoText;
    private String videoLink;
    private String TrustedBy;
    private List<Company> companies;

    @Data
    public static class Company {
         private String name;
          private String src;
    }
}