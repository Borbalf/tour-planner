package com.tourplanner.demo.controller;

import com.tourplanner.demo.mapper.CityMapper;
import com.tourplanner.demo.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

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

    public City getCityByID(Long ID) {
        try {
            log.info("called with param: " + ID);
            return cityMapper.findByID(ID);
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return null;
        }
    }

    public List<City> getAllCities() {
        try {
            log.info("called");
            return cityMapper.findAll();
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return null;
        }
    }

    public City createCity(String name) {
        try {
            log.info("called");
            City city = cityMapper.findByName(name);
            if (city != null) {
                throw new IllegalArgumentException("City already exists");
            }
            //TODO fetch latitude, longitude and altitude from https://open-meteo.com/en/docs/geocoding-api
            city = new City(name, 44.5, 8.98, 0.0);
            int result = cityMapper.insertCity(city.getName(), city.getLatitude(), city.getLongitude(), city.getAltitude());

            if (result > 0) {
                city = cityMapper.findByName(city.getName());
            } else {
                throw new Exception("error while inserting");
            }

            return city;
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return null;
        }
    }

    public City updateCity(String name, Long ID) {
        try {
            log.info("called");
            City city = cityMapper.findByName(name);
            if (city != null) {
                throw new IllegalArgumentException("A city with this name exists already");
            }
            city = cityMapper.findByID(ID);
            if (city == null) {
                throw new IllegalArgumentException("City does not exists");
            }
            int result = cityMapper.updateCity(name, city.getLatitude(), city.getLongitude(), city.getAltitude(), ID);

            if (result > 0) {
                city = cityMapper.findByID(ID);
            } else {
                throw new Exception("error while updating");
            }

            return city;
        } catch (IllegalArgumentException iae) {
            log.error("failed due to: " + iae.getMessage());
            throw iae;
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return null;
        }
    }

    public int deleteCity(Long ID) {
        try {
            log.info("called");
            return cityMapper.deleteCity(ID);
        } catch (IllegalArgumentException iae) {
            log.error("failed due to: " + iae.getMessage());
            throw iae;
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return 0;
        }
    }
}
