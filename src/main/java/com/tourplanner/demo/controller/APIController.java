package com.tourplanner.demo.controller;

import com.tourplanner.demo.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

    @Autowired
    private MainController mainController;

    Logger log = LoggerFactory.getLogger(APIController.class);

    @GetMapping(value = "/hello")
    public ResponseEntity<String> hello() {
        try {
            log.info("called");
            return new ResponseEntity<>("Hello!", HttpStatus.OK);
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getCityByName/{name}")
    public ResponseEntity<City> getCityByName(@PathVariable String name) {
        try {
            log.info("called");
            return new ResponseEntity<>(mainController.getCityByName(name), HttpStatus.OK);
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
