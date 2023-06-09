package com.tourplanner.demo.model;

import java.math.BigDecimal;

public class WeatherCondition {
    private Long ID;
    private Long stayID;
    private Double temperature;
    private Double humidity;
    private Long skyConditionID;

    public WeatherCondition() {
    }

    public WeatherCondition(Long ID, Long stayID, Double temperature, Double humidity, Long skyConditionID) {
        this.ID = ID;
        this.stayID = stayID;
        this.temperature = temperature;
        this.humidity = humidity;
        this.skyConditionID = skyConditionID;
    }

    public WeatherCondition(Double temperature, Double humidity, Long skyConditionID) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.skyConditionID = skyConditionID;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getStayID() {
        return stayID;
    }

    public void setStayID(Long stayID) {
        this.stayID = stayID;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Long getSkyConditionID() {
        return skyConditionID;
    }

    public void setSkyConditionID(Long skyConditionID) {
        this.skyConditionID = skyConditionID;
    }
}
