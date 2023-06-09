package com.tourplanner.demo.pojo;

import java.util.List;

public class WeatherResponse {
    private WeatherData hourly;

    public WeatherData getHourly() {
        return hourly;
    }

    public void setHourly(WeatherData hourly) {
        this.hourly = hourly;
    }
}
