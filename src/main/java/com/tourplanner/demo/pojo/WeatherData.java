package com.tourplanner.demo.pojo;

import java.util.List;

public class WeatherData {
    List<Double> temperature_2m;
    List<Integer> relativehumidity_2m;
    List<Integer> weathercode;

    public List<Double> getTemperature_2m() {
        return temperature_2m;
    }

    public void setTemperature_2m(List<Double> temperature_2m) {
        this.temperature_2m = temperature_2m;
    }

    public List<Integer> getRelativehumidity_2m() {
        return relativehumidity_2m;
    }

    public void setRelativehumidity_2m(List<Integer> relativehumidity_2m) {
        this.relativehumidity_2m = relativehumidity_2m;
    }

    public List<Integer> getWeathercode() {
        return weathercode;
    }

    public void setWeathercode(List<Integer> weathercode) {
        this.weathercode = weathercode;
    }
}
