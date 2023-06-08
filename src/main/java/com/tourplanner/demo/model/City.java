package com.tourplanner.demo.model;

import java.math.BigDecimal;

public class City {
    private Long ID;
    private String name;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal altitude;

    public City() {
    }

    public City(String name, BigDecimal latitude, BigDecimal longitude, BigDecimal altitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public City(String name, Double latitude, Double longitude, Double altitude) {
        this.name = name;
        this.latitude = BigDecimal.valueOf(latitude);
        this.longitude = BigDecimal.valueOf(longitude);
        this.altitude = BigDecimal.valueOf(altitude);
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getAltitude() {
        return altitude;
    }

    public void setAltitude(BigDecimal altitude) {
        this.altitude = altitude;
    }
}
