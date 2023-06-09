package com.tourplanner.demo.controller;

import com.tourplanner.demo.ITINERARY_STATUS;
import com.tourplanner.demo.mapper.CityMapper;
import com.tourplanner.demo.mapper.ItineraryMapper;
import com.tourplanner.demo.mapper.StayMapper;
import com.tourplanner.demo.mapper.WeatherConditionMapper;
import com.tourplanner.demo.model.City;
import com.tourplanner.demo.model.Itinerary;
import com.tourplanner.demo.model.Stay;
import com.tourplanner.demo.model.WeatherCondition;
import com.tourplanner.demo.pojo.GeoCodingCity;
import com.tourplanner.demo.pojo.GeoCodingResponse;
import com.tourplanner.demo.pojo.WeatherData;
import com.tourplanner.demo.pojo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private ItineraryMapper itineraryMapper;

    @Autowired
    private StayMapper stayMapper;

    @Autowired
    private WeatherConditionMapper weatherConditionMapper;

    Logger log = LoggerFactory.getLogger(MainController.class);

    public static final List<Long> CLEAR_WEATHER_IDS = List.of(0L, 1L);
    public static final List<Long> OVERCAST_WEATHER_IDS = List.of(2L, 3L, 45L, 48L);
    public static final List<Long> RAIN_WEATHER_IDS = List.of(51L, 53L, 55L, 61L, 63L, 65L, 80L, 81L, 82L, 95L, 96L, 99L);
    public static final List<Long> SNOW_WEATHER_IDS = List.of(56L, 57L, 66L, 67L, 71L, 73L, 75L, 77L, 85L, 86L);

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

            city = geocodeCity(name);
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
            city = geocodeCity(name);
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
            List<Stay> stays = stayMapper.findAllByCityID(ID);
            if (stays != null && !stays.isEmpty()) {
                throw new IllegalArgumentException("Some itineraries have at least a stay in this city, delete those first");
            }
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
            List<Stay> itineraryStays = stayMapper.findAllByItineraryID(ID);
            if (itineraryStays != null) {
                for (Stay stay : itineraryStays) {
                    deleteStay(stay.getID()); //this also takes care of WeatherConditions
                }
            }
            return itineraryMapper.deleteItinerary(ID);
        } catch (IllegalArgumentException iae) {
            log.error("failed due to: " + iae.getMessage());
            throw iae;
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return 0;
        }
    }

    /**
     * STAY SECTION
     */

    public Stay getStayByID(Long ID) {
        try {
            log.info("called with param: " + ID);
            return stayMapper.findByID(ID);
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return null;
        }
    }

    public List<Stay> getAllStays() {
        try {
            log.info("called");
            return stayMapper.findAll();
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return null;
        }
    }

    public List<Stay> getAllItineraryStays(Long itineraryID) {
        try {
            log.info("called");
            return stayMapper.findAllByItineraryID(itineraryID);
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return null;
        }
    }

    public Stay createStay(Stay stay) {
        try {
            log.info("called");

            Itinerary itinerary = itineraryMapper.findByID(stay.getItineraryID());
            if (itinerary == null) {
                throw new IllegalArgumentException("Itinerary does not exist");
            }
            City city = cityMapper.findByID(stay.getCityID());
            if (city == null) {
                throw new IllegalArgumentException("City does not exist");
            }

            int result = stayMapper.insertStay(stay.getItineraryID(), stay.getCityID(), stay.getDescription(), stay.getStayDate());

            if (result == 0) {
                throw new Exception("error while inserting");
            }

            Long stayID = stayMapper.findLatestID();

            if (stayID == null) {
                throw new Exception("error while inserting");
            }

            stay = stayMapper.findByID(stayID);
            if (itinerary.getStatusID().equals(ITINERARY_STATUS.NOT_READY.getID())) {
                List<Stay> itineraryStays = stayMapper.findAllByItineraryID(itinerary.getID());
                if (itineraryStays.size() >= 2) {
                    itineraryMapper.updateItinerary(itinerary.getID(), itinerary.getUserID(), ITINERARY_STATUS.READY.getID(), itinerary.getDescription(), itinerary.getStartDate(), itinerary.getEndDate());
                }
            }

            WeatherCondition weatherCondition = getWeather(city.getLatitude().doubleValue(), city.getLongitude().doubleValue(), city.getAltitude().doubleValue(), stay.getStayDate());
            weatherCondition.setStayID(stayID);
            weatherConditionMapper.insertWeatherCondition(weatherCondition.getStayID(), weatherCondition.getTemperature(), weatherCondition.getHumidity(), weatherCondition.getSkyConditionID());

            return stay;
        } catch (IllegalArgumentException iae) {
            log.error("failed due to: " + iae.getMessage());
            throw iae;
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return null;
        }
    }

    public Stay updateStay(Stay stay) {
        try {
            log.info("called");

            Itinerary itinerary = itineraryMapper.findByID(stay.getItineraryID());
            if (itinerary == null) {
                throw new IllegalArgumentException("Itinerary does not exist");
            }
            City city = cityMapper.findByID(stay.getCityID());
            if (city == null) {
                throw new IllegalArgumentException("City does not exist");
            }

            Stay stayToUpdate = stayMapper.findByID(stay.getID());
            if (stayToUpdate == null) {
                throw new IllegalArgumentException("Stay does not exists");
            }
            Long oldItineraryID = stayToUpdate.getItineraryID();
            Long oldCityID = stayToUpdate.getCityID();
            Date oldStayDate = stayToUpdate.getStayDate();
            stayMapper.updateStay(stay.getID(), stay.getItineraryID(), stay.getCityID(), stay.getDescription(), stay.getStayDate());

            stay = stayMapper.findByID(stay.getID());

            if (stay == null) {
                throw new Exception("error while updating");
            }

            if (!oldCityID.equals(stay.getCityID()) || !oldStayDate.equals(stay.getStayDate())) {
                weatherConditionMapper.deleteWeatherConditionByStayID(stay.getID());
                WeatherCondition weatherCondition = getWeather(city.getLatitude().doubleValue(), city.getLongitude().doubleValue(), city.getAltitude().doubleValue(), stay.getStayDate());
                weatherCondition.setStayID(stay.getID());
                weatherConditionMapper.insertWeatherCondition(weatherCondition.getStayID(), weatherCondition.getTemperature(), weatherCondition.getHumidity(), weatherCondition.getSkyConditionID());
            }

            if (!oldItineraryID.equals(stay.getItineraryID())) {
                Itinerary oldItinerary = itineraryMapper.findByID(oldItineraryID);
                if (oldItinerary.getStatusID().equals(ITINERARY_STATUS.READY.getID())) {
                    List<Stay> itineraryStays = stayMapper.findAllByItineraryID(oldItineraryID);
                    if (itineraryStays.size() < 2) {
                        itineraryMapper.updateItinerary(oldItinerary.getID(), oldItinerary.getUserID(), ITINERARY_STATUS.NOT_READY.getID(), oldItinerary.getDescription(), oldItinerary.getStartDate(), oldItinerary.getEndDate());
                    }
                }
            }

            return stay;
        } catch (IllegalArgumentException iae) {
            log.error("failed due to: " + iae.getMessage());
            throw iae;
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return null;
        }
    }

    public int deleteStay(Long ID) {
        try {
            log.info("called");
            //TODO decomment after weatherCondition CRUD
            //weatherConditionMapper.deleteByStayID(ID);
            return stayMapper.deleteStay(ID);
        } catch (IllegalArgumentException iae) {
            log.error("failed due to: " + iae.getMessage());
            throw iae;
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return 0;
        }
    }

    /**
     * WEATHER CONDITION SECTION
     */

    public List<WeatherCondition> getAllWeatherConditions() {
        try {
            log.info("called");
            return weatherConditionMapper.findAll();
        } catch (Exception e) {
            log.error("failed due to: " + e.getMessage());
            return null;
        }
    }

    /**
     * HELPER METHODS
     */

    private static City geocodeCity(String name) {
        City city;
        WebClient webClient = WebClient.create("https://geocoding-api.open-meteo.com");

        GeoCodingResponse geoCodingResponse = webClient.get().uri("/v1/search?count=10&language=en&format=json&name=" + name).retrieve().bodyToMono(GeoCodingResponse.class).block();

        if (geoCodingResponse == null || geoCodingResponse.getResults() == null || geoCodingResponse.getResults().isEmpty()) {
            throw new IllegalArgumentException("Could not geocode city");
        }

        GeoCodingCity geoCodingCity = geoCodingResponse.getResults().get(0);

        city = new City(name, geoCodingCity.getLatitude(), geoCodingCity.getLongitude(), geoCodingCity.getElevation());
        return city;
    }

    private static WeatherCondition getWeather(Double latitude, Double longitude, Double elevation, Date stayDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(stayDate);
        WebClient webClient = WebClient.create("https://api.open-meteo.com");

        WeatherResponse weatherResponse = webClient.get().uri("/v1/forecast?latitude="+ latitude + "&longitude=" + longitude + "&elevation=" + elevation + "&hourly=temperature_2m,relativehumidity_2m,weathercode&start_date=" + dateString + "&end_date=" + dateString + "&timezone=Europe/Berlin").retrieve().bodyToMono(WeatherResponse.class).block();

        if (weatherResponse == null || weatherResponse.getHourly() == null) {
            throw new IllegalArgumentException("Could not geocode city");
        }

        WeatherData weatherData = weatherResponse.getHourly();
        Double meanTemperature = weatherData.getTemperature_2m().stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
        Double meanHumidity = weatherData.getRelativehumidity_2m().stream().mapToDouble(Integer::doubleValue).average().orElse(Double.NaN);
        Long maxWeather = weatherData.getWeathercode().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue()).get().getValue();
        Long skyConditionID = 1L;
        if (OVERCAST_WEATHER_IDS.contains(maxWeather)) {
            skyConditionID = 2L;
        } else if (RAIN_WEATHER_IDS.contains(maxWeather)) {
            skyConditionID = 3L;
        } else if (SNOW_WEATHER_IDS.contains(maxWeather)) {
            skyConditionID = 4L;
        }

        return new WeatherCondition(meanTemperature, meanHumidity, skyConditionID);
    }
}
