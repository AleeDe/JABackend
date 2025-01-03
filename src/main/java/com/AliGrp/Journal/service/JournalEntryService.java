package com.AliGrp.Journal.service;

import com.AliGrp.Journal.entity.JournalEntry;
import com.AliGrp.Journal.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public JournalEntry createJournalEntry(JournalEntry journalEntry, String id) {
        journalEntry.setCreatedBy(id);
        journalEntry.setCreatedDate(LocalDate.now());
        return journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAllJournalEntries() {
        return journalEntryRepository.findAll();
    }
    
    public List<JournalEntry> getAllJournalEntriesOfUser(String username) {
         return journalEntryRepository.findByCreatedBy(username); 
        }
    public JournalEntry getJournalEntryById(String id) {
        return journalEntryRepository.findById(id).orElse(null);
    }
    public JournalEntry updateJournalEntry(JournalEntry journalEntry, String id) {
        JournalEntry journalEntryToUpdate = journalEntryRepository.findById(id).orElse(null);
        if (journalEntryToUpdate != null) {
            journalEntryToUpdate.setTitle(journalEntry.getTitle());
            journalEntryToUpdate.setContent(journalEntry.getContent());
            return journalEntryRepository.save(journalEntryToUpdate);
        }
        return null;
    }
    public boolean deleteJournalEntry(String id) {
        JournalEntry journalEntryToDelete = journalEntryRepository.findById(id).orElse(null);
        if (journalEntryToDelete != null) {
            journalEntryRepository.delete(journalEntryToDelete);
            return true;
        }
        return false;
    }
}
