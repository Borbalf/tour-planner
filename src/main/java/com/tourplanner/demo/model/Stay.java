package com.tourplanner.demo.model;

import java.util.Date;

public class Stay {
    private Long ID;
    private Long itineraryID;
    private Long cityID;
    private String description;
    private Date stayDate;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getItineraryID() {
        return itineraryID;
    }

    public void setItineraryID(Long itineraryID) {
        this.itineraryID = itineraryID;
    }

    public Long getCityID() {
        return cityID;
    }

    public void setCityID(Long cityID) {
        this.cityID = cityID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStayDate() {
        return stayDate;
    }

    public void setStayDate(Date stayDate) {
        this.stayDate = stayDate;
    }
}
