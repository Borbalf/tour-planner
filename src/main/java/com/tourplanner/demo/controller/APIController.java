package com.tourplanner.demo.controller;

import com.tourplanner.demo.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class APIController {

    @Autowired
    private MainController mainController;

    Logger log = LoggerFactory.getLogger(APIController.class);

    @GetMapping(value = "/cityByName/{name}")
    public ResponseEntity<City> getCityByName(@PathVariable String name) {
        try {
            log.info("called");
            City city = mainController.getCityByName(name);
            if (city != null) {
                return new ResponseEntity<>(city, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAllCities")
    public ResponseEntity<List<City>> getAllCities() {
        try {
            log.info("called");
            return new ResponseEntity<>(mainController.getAllCities(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/city/{ID}")
    public ResponseEntity<City> getCity(@PathVariable Long ID) {
        try {
            log.info("called");
            City city = mainController.getCityByID(ID);
            if (city != null) {
                return new ResponseEntity<>(city, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/city/{name}")
    public ResponseEntity<City> createCity(@PathVariable String name) {
        try {
            log.info("called");
            City city = mainController.createCity(name);
            if (city != null) {
                return new ResponseEntity<>(city, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IllegalArgumentException iae) {
            log.error("failed due to: " + iae.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/city/{ID}")
    public ResponseEntity<City> updateCity(@RequestBody String name, @PathVariable Long ID) {
        try {
            log.info("called");
            City updatedCity = mainController.updateCity(name, ID);
            if (updatedCity != null) {
                return new ResponseEntity<>(updatedCity, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IllegalArgumentException iae) {
            log.error("failed due to: " + iae.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/city/{ID}")
    public ResponseEntity<City> deleteCity(@PathVariable Long ID) {
        try {
            log.info("called");
            int result = mainController.deleteCity(ID);
            if (result > 0) {
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IllegalArgumentException iae) {
            log.error("failed due to: " + iae.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
