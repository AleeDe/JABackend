package com.AliGrp.Journal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AliGrp.Journal.entity.JournalEntry;
import com.AliGrp.Journal.entity.User;
import com.AliGrp.Journal.service.HomeAboutService;
import com.AliGrp.Journal.service.HomeFeaturesService;
import com.AliGrp.Journal.service.HomeHeroService;
import com.AliGrp.Journal.service.HomeTestService;
import com.AliGrp.Journal.service.JournalEntryService;
import com.AliGrp.Journal.service.UserService;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;


    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if(userService.findByUsername(user.getUsername())!=null) {
            return new ResponseEntity<>("username alread Exist", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(userService.saveUser(user,"STUDENT"), HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String check = userService.loginUser(user);
        if(check!= null) {
            return new ResponseEntity<>(check, HttpStatus.OK);
        }
        return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
    }

    // Home Page API

    @Autowired
    private HomeHeroService homeService;

    @Autowired
    private HomeFeaturesService homeFeaturesService;

    @Autowired
    private HomeTestService homeTestService;

    @Autowired
    private HomeAboutService homeAboutService;
    
    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/getJournalEntries")
    public ResponseEntity<?> getAllJournalEntries() {
        List<JournalEntry> journalEntries = journalEntryService.getAllJournalEntries();
        if(journalEntries.isEmpty()) {
            return new ResponseEntity<>("No Journal Entries found", HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        }
    }
    @GetMapping("/home/get-about")
    public ResponseEntity<?> getAbout() {
        return new ResponseEntity<>(homeAboutService.getAbout(), HttpStatus.OK);
    }
    @GetMapping("/home/get-hero")
    public ResponseEntity<?> getHero() {
        return new ResponseEntity<>(homeService.getHeroEntry(), HttpStatus.OK);
    }
    @GetMapping("/home/get-features")
    public ResponseEntity<?> getFeatures() {
        return new ResponseEntity<>(homeFeaturesService.getFeatures(), HttpStatus.OK);
    }
    @GetMapping("/home/get-test")
    public ResponseEntity<?> getTest() {
        return new ResponseEntity<>(homeTestService.getTest(), HttpStatus.OK);
    }

}