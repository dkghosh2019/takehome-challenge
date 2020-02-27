
package com.example.takehomechallenge.contoller;

import com.example.takehomechallenge.exception.CityNotFoundException;
import com.example.takehomechallenge.service.CityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CityConnectionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CityService cityService;

    @Test
    public void shouldReturnServerCurrentStatus() throws Exception {
        this.mockMvc
                .perform(get("/status/check"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("City Route API is UP!")));
    }


    @Test
    public void shouldReturnResponseYesIntegrationTest() throws Exception{

        this.mockMvc
                .perform(get("/connected?origin=Boston&destination=Newark"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("yes")));

    }

    @Test
    public void shouldReturnResponseNo_2() throws Exception{
       
        this.mockMvc
                .perform(get("/connected?origin=Philadelphia&destination=Albany"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("no")));

    }


}