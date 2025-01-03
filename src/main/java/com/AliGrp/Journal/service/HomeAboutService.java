package com.AliGrp.Journal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.AliGrp.Journal.entity.HomeAbout;
import com.AliGrp.Journal.repository.HomeAboutRepository;

@Component
public class HomeAboutService {
    
    @Autowired
    private HomeAboutRepository homeAboutRepository;

    public HomeAbout saveAbout(HomeAbout homeAbout) {
        return homeAboutRepository.save(homeAbout);
    }

    public HomeAbout getAbout(){
        return homeAboutRepository.findAll().get(0);
    }

    public HomeAbout updateAbout(HomeAbout homeAbout){
        HomeAbout homeAboutToUpdate = homeAboutRepository.findAll().get(0);
        if (homeAboutToUpdate != null) {
            homeAbout.setId(homeAboutToUpdate.getId());
            return homeAboutRepository.save(homeAbout);
        }
        return null;
    }
}
