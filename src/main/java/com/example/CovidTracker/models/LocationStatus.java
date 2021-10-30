package com.example.CovidTracker.models;

public class LocationStatus {

    private String state;
    private String country;
    private int latestTotalCases;
    private int comparetoLastDay;

    public String getState() {
        return state;
    }

    public int getComparetoLastDay() {
        return comparetoLastDay;
    }

    public void setComparetoLastDay(int comparetoLastDay) {
        this.comparetoLastDay = comparetoLastDay;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    @Override
    public String toString() {
        return "LocationStatus{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                '}';
    }
}
