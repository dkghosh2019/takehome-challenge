package com.example.takehomechallenge.service;

import com.example.takehomechallenge.dao.CityRouteRepo;
import com.example.takehomechallenge.exception.CityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityServiceIntegrationTest {
    @Autowired
    CityService cityService;

    @Test
    public void shouldRetunTrueForGivenCorretSourceAndDestination() throws Exception, CityNotFoundException {
        String source="Boston";
        String destination="Newark";

        assertTrue(cityService.doCitiesConnect(source, destination));
    }

    @Test
    public void shouldRetunFalseForGivenIncorretSourceAndDestination() throws Exception, CityNotFoundException {
        String source="Philadelphia";
        String destination="Albany";

        assertFalse(cityService.doCitiesConnect(source, destination));
    }

    @Test(expected = CityNotFoundException.class)
    public void shouldThrowExceptionForGivenUnknownSource() throws Exception, CityNotFoundException {
        String source="Los Angeles";
        String destination="Albany";
        cityService.doCitiesConnect(source, destination);
    }

    @Test()
    public void shouldThrowExceptionForGivenUnknownDestination() throws Exception, CityNotFoundException {
        String source="New York";
        String destination="Los Angeles";

        assertThatThrownBy(() -> cityService.doCitiesConnect(source, destination))
                .isInstanceOf(CityNotFoundException.class)
                .hasMessage("Not fouund : " + destination)
                .hasNoCause();
    }
}