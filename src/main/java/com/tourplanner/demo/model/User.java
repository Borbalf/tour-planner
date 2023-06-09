package com.tourplanner.demo.model;

import java.util.Date;
import java.util.List;

public class User {
    private Long ID;
    private String name;
    private String surname;
    private String email;
    List<Itinerary> itineraries;

    public User() {
    }

    public User(Long ID, String name, String surname, String email) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.email = email;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }
}
