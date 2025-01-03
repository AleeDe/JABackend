package com.AliGrp.Journal.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "homeFeatures")
public class HomeFeatures {

    @Id
    private String id;

    private String heading;
    private String subheading;
    private List<Features> features;

    @Data
    public static class Features {
        private String icon;
        private String title;
        private String description;
    }


}
