package com.AliGrp.Journal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.AliGrp.Journal.entity.HomeHeroEntry;
import com.AliGrp.Journal.repository.HomeHeroRepository;

@Service
@Component
public class HomeHeroService {
    @Autowired
    private HomeHeroRepository homeHeroRepository;

    public HomeHeroEntry saveHero(HomeHeroEntry homeHeroEntry) {
        return homeHeroRepository.save(homeHeroEntry);
    }

    public HomeHeroEntry getHeroEntry(){
        return homeHeroRepository.findAll().get(0);
    }

    public HomeHeroEntry updateHero(HomeHeroEntry homeHeroEntry){
        HomeHeroEntry homeHeroEntryToUpdate = homeHeroRepository.findAll().get(0);
        if (homeHeroEntryToUpdate != null) {
            homeHeroEntryToUpdate.setTitle(homeHeroEntry.getTitle());
            homeHeroEntryToUpdate.setSubtitle(homeHeroEntry.getSubtitle());
            homeHeroEntryToUpdate.setDescription(homeHeroEntry.getDescription());
            homeHeroEntryToUpdate.setButtonText(homeHeroEntry.getButtonText());
            homeHeroEntryToUpdate.setVideoText(homeHeroEntry.getVideoText());
            homeHeroEntryToUpdate.setVideoLink(homeHeroEntry.getVideoLink());
            homeHeroEntryToUpdate.setTrustedBy(homeHeroEntry.getTrustedBy());
            homeHeroEntryToUpdate.setCompanies(homeHeroEntry.getCompanies());
            return homeHeroRepository.save(homeHeroEntryToUpdate);
        }
        return null;
    }
}
