package com.AliGrp.Journal.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Home-testimonials")
public class HomeTestimonialEntry {
    @Id
    private String id;
    private String heading;
    private List<Testimonials> testimonials;
    
    @Data
    public static class Testimonials {
        private String quote;
        private String author;
        private String role;
        private String image;
    }
}
