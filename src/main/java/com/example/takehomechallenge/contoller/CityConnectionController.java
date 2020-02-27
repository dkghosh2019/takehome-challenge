package com.example.takehomechallenge.contoller;

import com.example.takehomechallenge.exception.CityNotFoundException;
import com.example.takehomechallenge.service.CityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityConnectionController  {

    @Autowired
    private CityService cityService;

    private   Logger logger= LoggerFactory.getLogger("CityConnectionController");

    @GetMapping(path="/status/check")
    public  String getStatus(){
        return "City Route API is UP!";
    }

    @GetMapping("/connected")
    @ApiOperation(value = "Find if you can connect two cites by roads",
                    notes = "program responds with ‘yes’ if city1 is connected to city2, ’no’ if city1 is not connected to city2.\n" +
                            "\n" +
                            "Any unexpected input results in a ’no’ response",
                    response = String.class)
    public String citiConnectedResult(@ApiParam(value = "SOURCE value for te city you want to travel from", required = true) @DefaultValue ("none")@RequestParam String origin,
                                      @ApiParam(value = "DESTINATION value for te city you want to arrive at", required = true) @DefaultValue("none") @RequestParam String destination){

        logger.info("origin : " + origin + " , destination : " + destination);

        boolean result ;
        try {
            result = cityService.doCitiesConnect(origin, destination);
        } catch (CityNotFoundException e) {
            return "no";
        }
        logger.info("result :" + result);
        if (result == true)
            return  "yes";
        return  "no";
    }
}
