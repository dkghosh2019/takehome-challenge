package com.example.takehomechallenge.service;

import com.example.takehomechallenge.dao.CityRouteRepo;
import com.example.takehomechallenge.exception.CityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CityServiceTest {
    @InjectMocks
    CityService cityService;

    @Mock
    CityRouteRepo cityRouteRepo;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldRetunTrueForGivenCorretSourceAndDestination() throws Exception, CityNotFoundException {
        String source="Boston";
        String destination="Newark";

        when(cityRouteRepo.findCityIndexByCityName(source)).thenReturn(1);
        when(cityRouteRepo.findCityIndexByCityName(destination)).thenReturn(1);

        assertTrue(cityService.doCitiesConnect(source, destination));
    }

    @Test
    public void shouldRetunFalseForGivenIncorretSourceAndDestination() throws Exception, CityNotFoundException {
        String source="Philadelphia";
        String destination="Albany";

        when(cityRouteRepo.findCityIndexByCityName(source)).thenReturn(2);
        when(cityRouteRepo.findCityIndexByCityName(destination)).thenReturn(3);

        assertFalse(cityService.doCitiesConnect(source, destination));
    }

    @Test(expected = CityNotFoundException.class)
    public void shouldThrowExceptionForGivenUnknownSource() throws Exception, CityNotFoundException {
        String source="Los Angeles";
        String destination="Albany";

        when(cityRouteRepo.findCityIndexByCityName(source)).thenThrow(CityNotFoundException.class);
        when(cityRouteRepo.findCityIndexByCityName(destination)).thenReturn(5);

        cityService.doCitiesConnect(source, destination);
    }

    @Test()
    public void shouldThrowExceptionForGivenUnknownDestination() throws Exception, CityNotFoundException {
        String source="New York";
        String destination="Los Angeles";

        when(cityRouteRepo.findCityIndexByCityName(source)).thenReturn(1);
        when(cityRouteRepo.findCityIndexByCityName(destination))
                .thenThrow(new CityNotFoundException("Not fouund : " + destination));


        assertThatThrownBy(() -> cityService.doCitiesConnect(source, destination))
                .isInstanceOf(CityNotFoundException.class)
                .hasMessage("Not fouund : " + destination)
                .hasNoCause();
    }
}