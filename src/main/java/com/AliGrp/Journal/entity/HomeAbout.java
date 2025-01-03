package com.AliGrp.Journal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "Home-About")
public class HomeAbout {
    @Id
    private String id;
    private String heading;
    private String paragraph;
    private String image;
}
