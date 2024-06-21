package com.example.sularm.model.request;

import com.google.gson.annotations.SerializedName;

public class RequestSchedule {
    private String time;
    private String arrivedBefore;
    private String estimatedTravelTime;
    private String location;
    private Integer status;

    public RequestSchedule(String time, String arrivedBefore, String location, Integer status, String estimatedTravelTime) {
        this.time = time;
        this.arrivedBefore = arrivedBefore;
        this.location = location;
        this.status = status;
        this.estimatedTravelTime = estimatedTravelTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getArrivedBefore() {
        return arrivedBefore;
    }

    public void setArrivedBefore(String arrivedBefore) {
        this.arrivedBefore = arrivedBefore;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEstimatedTravelTime() {
        return estimatedTravelTime;
    }

    public void setEstimatedTravelTime(String estimatedTravelTime) {
        this.estimatedTravelTime = estimatedTravelTime;
    }

}
