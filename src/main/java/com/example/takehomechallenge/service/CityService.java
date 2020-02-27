package com.example.takehomechallenge.service;

import com.example.takehomechallenge.config.CityMap;
import com.example.takehomechallenge.dao.CityRouteRepo;
import com.example.takehomechallenge.exception.CityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CityService {

    private CityRouteRepo cityRouteRepo;

    @Autowired
    public CityService(CityRouteRepo cityRouteRepo) {
        this.cityRouteRepo = cityRouteRepo;
    }


    public boolean doCitiesConnect(String origin, String destination) throws CityNotFoundException {
        System.out.println("doCitiesConnect called......");
       int cityIndex1 = cityRouteRepo.findCityIndexByCityName(origin);
       int cityIndex2 = cityRouteRepo.findCityIndexByCityName(destination);

       if ( cityIndex1 == cityIndex2)
           return true;

        return false;
    }
}
