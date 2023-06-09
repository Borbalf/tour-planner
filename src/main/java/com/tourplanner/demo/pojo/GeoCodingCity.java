package com.tourplanner.demo.pojo;

public class GeoCodingCity {
    private String name;
    private Double latitude;
    private Double longitude;
    private Double elevation;


    public GeoCodingCity() {
    }

    public GeoCodingCity(String name, Double latitude, Double longitude, Double elevation) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.elevation = elevation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }
}
