
package com.example.takehomechallenge.contoller;

import com.example.takehomechallenge.exception.CityNotFoundException;
import com.example.takehomechallenge.service.CityService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
  This test approach does not start the server at all but tests only the layer below .
  Spring handles the incoming HTTP request and hands it off to your controller. It also
  start full sprin application context.
 */
@SpringBootTest
@AutoConfigureMockMvc
class CityConnectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityService cityService;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnServerCurrentStatus() throws Exception {
        this.mockMvc
                .perform(get("/status/check"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("City Route API is UP!")));
    }

    @Test
    public void shouldReturnResponseYes() throws Exception, CityNotFoundException {
        when(cityService.doCitiesConnect("Boston", "Newark")).thenReturn(true);
        this.mockMvc
                .perform(get("/connected?origin=Boston&destination=Newark"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("yes")));

    }

    @Test
    public void shouldReturnResponseNo() throws Exception, CityNotFoundException {
        when(cityService.doCitiesConnect("Philadelphia", "Albany")).thenReturn(false);
        this.mockMvc
                .perform(get("/connected?origin=Philadelphia&destination=Albany"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("no")));

    }
}