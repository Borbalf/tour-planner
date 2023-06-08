package com.tourplanner.demo.controller;

import com.tourplanner.demo.model.City;
import com.tourplanner.demo.model.Itinerary;
import com.tourplanner.demo.model.Stay;
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

    /**
     * CITY SECTION
     */

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

    /**
     * ITINERARY SECTION
     */

    @GetMapping(value = "/getAllItineraries")
    public ResponseEntity<List<Itinerary>> getAllItineraries() {
        try {
            log.info("called");
            return new ResponseEntity<>(mainController.getAllItineraries(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/itinerary/{ID}")
    public ResponseEntity<Itinerary> getItinerary(@PathVariable Long ID) {
        try {
            log.info("called");
            Itinerary itinerary = mainController.getItineraryByID(ID);
            if (itinerary != null) {
                return new ResponseEntity<>(itinerary, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/itinerary", consumes = "application/json")
    public ResponseEntity<Itinerary> createItinerary(@RequestBody Itinerary itinerary) {
        try {
            log.info("called");
            itinerary = mainController.createItinerary(itinerary);
            if (itinerary != null) {
                return new ResponseEntity<>(itinerary, HttpStatus.OK);
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

    @PutMapping(value = "/itinerary", consumes = "application/json")
    public ResponseEntity<Itinerary> updateItinerary(@RequestBody Itinerary itinerary) {
        try {
            log.info("called");
            itinerary = mainController.updateItinerary(itinerary);
            if (itinerary != null) {
                return new ResponseEntity<>(itinerary, HttpStatus.OK);
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

    @DeleteMapping(value = "/itinerary/{ID}")
    public ResponseEntity<Itinerary> deleteItinerary(@PathVariable Long ID) {
        try {
            log.info("called");
            int result = mainController.deleteItinerary(ID);
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

    /**
     * STAY SECTION
     */

    @GetMapping(value = "/getAllStays")
    public ResponseEntity<List<Stay>> getAllStays() {
        try {
            log.info("called");
            return new ResponseEntity<>(mainController.getAllStays(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getAllItineraryStays/{itineraryID}")
    public ResponseEntity<List<Stay>> getAllItineraryStays(@PathVariable Long itineraryID) {
        try {
            log.info("called");
            return new ResponseEntity<>(mainController.getAllItineraryStays(itineraryID), HttpStatus.OK);
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/stay/{ID}")
    public ResponseEntity<Stay> getStay(@PathVariable Long ID) {
        try {
            log.info("called");
            Stay stay = mainController.getStayByID(ID);
            if (stay != null) {
                return new ResponseEntity<>(stay, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/stay", consumes = "application/json")
    public ResponseEntity<Stay> createStay(@RequestBody Stay stay) {
        try {
            log.info("called");
            stay = mainController.createStay(stay);
            if (stay != null) {
                return new ResponseEntity<>(stay, HttpStatus.OK);
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

    @PutMapping(value = "/stay", consumes = "application/json")
    public ResponseEntity<Stay> updateStay(@RequestBody Stay stay) {
        try {
            log.info("called");
            stay = mainController.updateStay(stay);
            if (stay != null) {
                return new ResponseEntity<>(stay, HttpStatus.OK);
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

    @DeleteMapping(value = "/stay/{ID}")
    public ResponseEntity<Stay> deleteStay(@PathVariable Long ID) {
        try {
            log.info("called");
            int result = mainController.deleteStay(ID);
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
