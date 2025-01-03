package com.AliGrp.Journal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.AliGrp.Journal.entity.HomeFeatures;
import com.AliGrp.Journal.repository.HomeFeaturesRepostiory;

@Component
public class HomeFeaturesService {
    
    @Autowired
    private HomeFeaturesRepostiory homeFeaturesRepository;
    
    public HomeFeatures saveFeatures(HomeFeatures homeFeatures) {
        return homeFeaturesRepository.save(homeFeatures);
    }

    public HomeFeatures getFeatures(){
        return homeFeaturesRepository.findAll().get(0);
    }

    public HomeFeatures updateFeatures(HomeFeatures homeFeatures){
        HomeFeatures homeFeaturesToUpdate = homeFeaturesRepository.findAll().get(0);
        if (homeFeaturesToUpdate != null) {
            homeFeaturesToUpdate.setHeading(homeFeatures.getHeading());
            homeFeaturesToUpdate.setSubheading(homeFeatures.getSubheading());
            homeFeaturesToUpdate.setFeatures(homeFeatures.getFeatures());
            return homeFeaturesRepository.save(homeFeaturesToUpdate);
        }
        return null;
    }

    

}
