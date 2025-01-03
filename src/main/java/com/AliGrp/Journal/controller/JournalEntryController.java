package com.AliGrp.Journal.controller;

import com.AliGrp.Journal.entity.JournalEntry;
import com.AliGrp.Journal.service.JournalEntryService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @PostMapping
    public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody JournalEntry journalEntry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id =authentication.getName();
        return new ResponseEntity<>(journalEntryService.createJournalEntry(journalEntry,id), HttpStatus.CREATED);
    }

    @GetMapping("/getJournalEntriesOfUser")
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id =authentication.getName();
        return new ResponseEntity<>(journalEntryService.getAllJournalEntriesOfUser(id), HttpStatus.OK);
    }
    
    @GetMapping("/getJournalEntries/{id}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable String id) {
        return new ResponseEntity<>(journalEntryService.getJournalEntryById(id), HttpStatus.OK);
    }

    @PutMapping("/updateJournalEntry/{id}")
    public ResponseEntity<?> updateJournalEntry(@RequestBody JournalEntry journalEntry, @PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId =authentication.getName();
        JournalEntry journalEntryUpdated = journalEntryService.updateJournalEntry(journalEntry, id);
        if(journalEntryUpdated.getCreatedBy().equals(userId) && journalEntryUpdated!=null) {
            journalEntryUpdated.setId(userId);
            journalEntryUpdated.setTitle(journalEntry.getTitle());
            journalEntryUpdated.setContent(journalEntry.getContent());
            return new ResponseEntity<>(journalEntryService.updateJournalEntry(journalEntryUpdated, id), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Journal Entry not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteJournalEntry/{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId =authentication.getName();
        JournalEntry journalEntry = journalEntryService.getJournalEntryById(id);
        if(journalEntry.getCreatedBy().equals(userId)) {
            if(journalEntryService.deleteJournalEntry(id)) {
                return new ResponseEntity<>("Journal Entry deleted", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("Journal Entry not found", HttpStatus.NOT_FOUND);
            }
        }
        else {
            return new ResponseEntity<>("Journal Entry not found", HttpStatus.NOT_FOUND);
        }
    }


    
}
