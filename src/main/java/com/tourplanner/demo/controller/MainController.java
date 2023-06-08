package com.tourplanner.demo.controller;

import com.tourplanner.demo.ITINERARY_STATUS;
import com.tourplanner.demo.mapper.CityMapper;
import com.tourplanner.demo.mapper.ItineraryMapper;
import com.tourplanner.demo.model.City;
import com.tourplanner.demo.model.Itinerary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private ItineraryMapper itineraryMapper;

    Logger log = LoggerFactory.getLogger(MainController.class);

    /**
     * CITY SECTION
     */

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
            //TODO decomment after creating Stays CRUD
//            List<Stay> stays = stayMapper.findAllByCityID(ID);
//            if (stays != null && !stays.isEmpty()) {
//                throw new IllegalArgumentException("Some itineraries have at least a stay in this city, delete those first");
//            }
            return cityMapper.deleteCity(ID);
        } catch (IllegalArgumentException iae) {
            log.error("failed due to: " + iae.getMessage());
            throw iae;
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return 0;
        }
    }

    /**
     * ITINERARY SECTION
     */

    public Itinerary getItineraryByID(Long ID) {
        try {
            log.info("called with param: " + ID);
            return itineraryMapper.findByID(ID);
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return null;
        }
    }

    public List<Itinerary> getAllItineraries() {
        try {
            log.info("called");
            return itineraryMapper.findAll();
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return null;
        }
    }

    public Itinerary createItinerary(Itinerary itinerary) {
        try {
            log.info("called");
            int result = itineraryMapper.insertItinerary(null, itinerary.getUserID(), ITINERARY_STATUS.NOT_READY.getID(), itinerary.getDescription(), itinerary.getStartDate(), itinerary.getEndDate());

            if (result == 0) {
                throw new Exception("error while inserting");
            }

            Long itineraryID = itineraryMapper.findLatestID();

            if (itineraryID == null) {
                throw new Exception("error while inserting");
            }

            itinerary = itineraryMapper.findByID(itineraryID);

            return itinerary;
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return null;
        }
    }

    public Itinerary updateItinerary(Itinerary itinerary) {
        try {
            log.info("called");
            Itinerary itineraryToUpdate = itineraryMapper.findByID(itinerary.getID());
            if (itineraryToUpdate == null) {
                throw new IllegalArgumentException("Itinerary does not exists");
            }
            itineraryMapper.updateItinerary(itinerary.getID(), itinerary.getUserID(), itineraryToUpdate.getStatusID(), itinerary.getDescription(), itinerary.getStartDate(), itinerary.getEndDate());

            itinerary = itineraryMapper.findByID(itinerary.getID());

            if (itinerary == null) {
                throw new Exception("error while updating");
            }

            return itinerary;
        } catch (IllegalArgumentException iae) {
            log.error("failed due to: " + iae.getMessage());
            throw iae;
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return null;
        }
    }

    public int deleteItinerary(Long ID) {
        try {
            log.info("called");
            //TODO decomment after creating Stays CRUD
//          stayMapper.deleteByItineraryID(ID);
            return itineraryMapper.deleteItinerary(ID);
        } catch (IllegalArgumentException iae) {
            log.error("failed due to: " + iae.getMessage());
            throw iae;
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return 0;
        }
    }
}
