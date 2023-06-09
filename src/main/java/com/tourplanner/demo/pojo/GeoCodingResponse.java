package com.tourplanner.demo.pojo;

import java.util.List;

public class GeoCodingResponse {
    private List<GeoCodingCity> results;

    public List<GeoCodingCity> getResults() {
        return results;
    }

    public void setResults(List<GeoCodingCity> results) {
        this.results = results;
    }
}
