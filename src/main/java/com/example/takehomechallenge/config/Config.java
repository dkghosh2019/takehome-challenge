package com.example.takehomechallenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.Map;

@Configuration
public class Config {
   @Value("${file.location}")
    private  Resource resource;


    @Bean
    public CityMap getCityMap(){
        CityMap cityMap=new CityMap();
        cityMap.setCityMap(getCiyMap());
        return  cityMap;

    }

    private Map<String, Integer> getCiyMap() {
        CitySetsUtil citySetsUtil=new CitySetsUtil();
        return citySetsUtil.getCityMap(resource);
    }
}
