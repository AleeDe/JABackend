package com.AliGrp.Journal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AliGrp.Journal.entity.HomeAbout;
import com.AliGrp.Journal.entity.HomeFeatures;
import com.AliGrp.Journal.entity.HomeHeroEntry;
import com.AliGrp.Journal.entity.HomeTestimonialEntry;
import com.AliGrp.Journal.service.HomeAboutService;
import com.AliGrp.Journal.service.HomeFeaturesService;
import com.AliGrp.Journal.service.HomeHeroService;
import com.AliGrp.Journal.service.HomeTestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeHeroService homeService;

    @Autowired
    private HomeFeaturesService homeFeaturesService;

    @Autowired
    private HomeTestService homeTestService;

    @Autowired
    private HomeAboutService homeAboutService;

    @PostMapping("/create-hero")
    public ResponseEntity<?> createHero(@RequestBody HomeHeroEntry homeHeroEntry) {
        return new ResponseEntity<>(homeService.saveHero(homeHeroEntry), HttpStatus.CREATED);
    }
    @PostMapping("/create-features")
    public ResponseEntity<?> createFeatures(@RequestBody HomeFeatures homeFeatures) {
        return new ResponseEntity<>(homeFeaturesService.saveFeatures(homeFeatures), HttpStatus.CREATED);
    }
    @PostMapping("/create-test")
    public ResponseEntity<?> createTest(@RequestBody HomeTestimonialEntry homeTest) {
        return new ResponseEntity<>(homeTestService.saveTest(homeTest), HttpStatus.CREATED);
    }
    @PostMapping("/create-about")
    public ResponseEntity<?> saveAbout(@RequestBody HomeAbout homeAbout) {
        return new ResponseEntity<>(homeAboutService.saveAbout(homeAbout), HttpStatus.CREATED);
    }

    
    @PutMapping("/update-hero")
    public ResponseEntity<?> updateHero(@RequestBody HomeHeroEntry homeHeroEntry) {
        return new ResponseEntity<>(homeService.updateHero(homeHeroEntry), HttpStatus.OK);
    }
    @PutMapping("/update-features")
    public ResponseEntity<?> updateFeatures(@RequestBody HomeFeatures homeFeatures) {
        return new ResponseEntity<>(homeFeaturesService.updateFeatures(homeFeatures), HttpStatus.OK);
    }
    @PutMapping("/update-test")
    public ResponseEntity<?> updateTest(@RequestBody HomeTestimonialEntry homeTest) {
        return new ResponseEntity<>(homeTestService.updateTest(homeTest), HttpStatus.OK);
    }
    @PutMapping("/update-about")
    public ResponseEntity<?> updateAbout(@RequestBody HomeAbout homeAbout) {
        return new ResponseEntity<>(homeAboutService.updateAbout(homeAbout), HttpStatus.OK);
    }
}