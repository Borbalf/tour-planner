package com.tourplanner.demo;

public enum ITINERARY_STATUS {
    NOT_READY(1L, "Not ready"),
    READY(2L, "Ready"),
    CONFIRMED(3L, "Confirmed"),
    DONE(4L, "Done");

    private final Long ID;
    private final String status;

    ITINERARY_STATUS(Long id, String status) {
        ID = id;
        this.status = status;
    }

    public Long getID() {
        return ID;
    }

    public String getStatus() {
        return status;
    }
}
