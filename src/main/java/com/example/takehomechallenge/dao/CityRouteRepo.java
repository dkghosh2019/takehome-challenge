package com.example.takehomechallenge.dao;

import com.example.takehomechallenge.config.CityMap;
import com.example.takehomechallenge.exception.CityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CityRouteRepo {
    private CityMap cityMap;

    @Autowired
    public CityRouteRepo(CityMap cityMap) {
        this.cityMap = cityMap;
    }

    private static Logger logger= LoggerFactory.getLogger("CityRouteRepo");

   public int findCityIndexByCityName(String city) throws CityNotFoundException{

     int cityIndex=  Optional.ofNullable(cityMap.getCityMap().get(city))
             .orElseThrow( () ->  new CityNotFoundException("Not fouund : " + city));

        logger.info("cityIndex : " + cityIndex);
        return cityIndex;
    }

}
