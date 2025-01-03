package com.AliGrp.Journal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.AliGrp.Journal.entity.HomeTestimonialEntry;
import com.AliGrp.Journal.repository.HomeTestRep;

@Component
public class HomeTestService {
    
    @Autowired
    private HomeTestRep homeTestRepository;

    public HomeTestimonialEntry saveTest(HomeTestimonialEntry homeTest) {
        return homeTestRepository.save(homeTest);
    }

    public HomeTestimonialEntry getTest(){
        return homeTestRepository.findAll().get(0);
    }

    public HomeTestimonialEntry updateTest(HomeTestimonialEntry homeTest){
        HomeTestimonialEntry homeTestToUpdate = homeTestRepository.findAll().get(0);
        if (homeTestToUpdate != null) {
            homeTest.setId(homeTestToUpdate.getId());
            return homeTestRepository.save(homeTest);
        }
        return null;
    }
}
