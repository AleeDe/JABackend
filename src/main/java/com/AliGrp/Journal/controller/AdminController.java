package com.AliGrp.Journal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.AliGrp.Journal.entity.JournalEntry;
import com.AliGrp.Journal.entity.User;
import com.AliGrp.Journal.service.JournalEntryService;
import com.AliGrp.Journal.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
    
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

    @PutMapping("/updateJournalEntry/{id}")
    public ResponseEntity<?> updateJournalEntry(@RequestBody JournalEntry journalEntry, @PathVariable String id) {
        JournalEntry journalEntryUpdated = journalEntryService.updateJournalEntry(journalEntry, id);
        if(journalEntryUpdated!=null) {
            return new ResponseEntity<>(journalEntryUpdated, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Journal Entry not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteJournalEntry/{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable String id) {
        if(journalEntryService.deleteJournalEntry(id)) {
            return new ResponseEntity<>("Journal Entry deleted", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Journal Entry not found", HttpStatus.NOT_FOUND);
        }
    }



    // User Section
    @Autowired
    private UserService userService;

    @PostMapping("/createAdmin")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user,"ADMIN"), HttpStatus.CREATED);
    }

    @GetMapping("/getUsers")
    public ResponseEntity<?> getAllUsers() {
        if(userService.findAll().isEmpty()) {
            return new ResponseEntity<>("No Users found", HttpStatus.NOT_FOUND);
        }
        else {
            List<User> users = userService.findAll();
            users.stream().forEach(user -> user.setPassword(""));
            users.removeIf(user -> user.getRoles().equals("ADMIN"));
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String id) {
        User userUpdated = userService.AupdateUser(user, id);
        if(userUpdated!=null) {
            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        User user = userService.findById(id);
        if(user!=null) {
            userService.deleteUser(user);
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
}
