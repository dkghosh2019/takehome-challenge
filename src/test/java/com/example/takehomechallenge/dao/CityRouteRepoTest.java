package com.example.takehomechallenge.dao;

import com.example.takehomechallenge.config.CityMap;
import com.example.takehomechallenge.exception.CityNotFoundException;
import io.swagger.models.auth.In;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityRouteRepoTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private CityMap cityMap;

    @InjectMocks
    private CityRouteRepo cityRouteRepo;


    @Test
    public void shouldReturnAnIntValueForFoundCity() throws Exception, CityNotFoundException {
        String city="Boston";
        when(cityMap.getCityMap().get(city)).thenReturn(1);
        assertEquals(1, cityRouteRepo.findCityIndexByCityName(city));
    }

    @Test
    public void shouldThowCityNotFoundExceptionForAnUnListedity() throws Exception{
        String city="unlistedCity";
        when(cityMap.getCityMap().get(city)).thenReturn(null);

        assertThatThrownBy(() -> cityRouteRepo.findCityIndexByCityName(city))
                .isInstanceOf(CityNotFoundException.class)
                .hasMessage("Not fouund : " + city)
                .hasNoCause();
    }
}