package com.example.takehomechallenge.dao;

import com.example.takehomechallenge.config.CityMap;
import com.example.takehomechallenge.exception.CityNotFoundException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class CityRouteRepoIntegrationTest {

    @Autowired
    private CityMap cityMap;

    @Autowired
    private CityRouteRepo cityRouteRepo;



    @Test
    public void shouldReturnAnIntValueForFoundCity() throws Exception, CityNotFoundException {
        String city="Boston";
        Integer returnedValue=cityRouteRepo.findCityIndexByCityName(city);
        assertEquals(1, returnedValue);
    }

    @Test
    public void shouldThowCityNotFoundExceptionForAnUnListedity() throws Exception{
        String city="unlistedCity";

        assertThatThrownBy(() -> cityRouteRepo.findCityIndexByCityName(city))
                .isInstanceOf(CityNotFoundException.class)
                .hasMessage("Not fouund : " + city)
                .hasNoCause();
    }
}