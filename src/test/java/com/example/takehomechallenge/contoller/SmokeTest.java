package com.example.takehomechallenge.contoller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private  CityConnectionController controller;

    @Test
    public void contextLoads() throws Exception{
        assertThat(controller).isNotNull();
    }

}