package com.tourplanner.demo.controller;

import com.tourplanner.demo.mapper.CityMapper;
import com.tourplanner.demo.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MainController {
    @Autowired
    private CityMapper cityMapper;

    Logger log = LoggerFactory.getLogger(MainController.class);

    public City getCityByName(String name) {
        try {
            log.info("called with param: " + name);
            return cityMapper.findByName(name);
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return null;
        }
    }
}
