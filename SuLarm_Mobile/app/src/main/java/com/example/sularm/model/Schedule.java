package com.example.sularm.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;

public class Schedule {
//            'arrived_before',
//            'estimated_travel_time',
//            'preparation_time',
//            'location_end',
//            'end_coor',
//            'location_start',
//            'start_coor',
//            'status'
    @SerializedName("id")
    private Integer id;
    @SerializedName("time")
    private String time;
    @SerializedName("arrived_before")
    private String arrivedBefore;
    @SerializedName("estimated_travel_time")
    private String estimatedTravelTime;
    @SerializedName("preparation_time")
    private String preparationTime;
    @SerializedName("location_end")
    private String locationEnd;
    @SerializedName("end_coor")
    private String endCoor;
    @SerializedName("location_start")
    private String locationStart;
    @SerializedName("start_coor")
    private String startCoor;
    @SerializedName("status")
    private Integer status;


    public Schedule(Integer id, String time, String arrivedBefore, String estimatedTravelTime, String preparationTime, String locationEnd, String endCoor, String locationStart, String startCoor, Integer status) {
        this.id = id;
        this.time = time;
        this.arrivedBefore = arrivedBefore;
        this.estimatedTravelTime = estimatedTravelTime;
        this.preparationTime = preparationTime;
        this.locationEnd = locationEnd;
        this.endCoor = endCoor;
        this.locationStart = locationStart;
        this.startCoor = startCoor;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEstimatedTravelTime() {
        return estimatedTravelTime;
    }

    public void setEstimatedTravelTime(String estimatedTravelTime) {
        this.estimatedTravelTime = estimatedTravelTime;
    }

    public String getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(String preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getLocationEnd() {
        return locationEnd;
    }

    public void setLocationEnd(String locationEnd) {
        this.locationEnd = locationEnd;
    }

    public String getEndCoor() {
        return endCoor;
    }

    public void setEndCoor(String endCoor) {
        this.endCoor = endCoor;
    }

    public String getLocationStart() {
        return locationStart;
    }

    public void setLocationStart(String locationStart) {
        this.locationStart = locationStart;
    }

    public String getStartCoor() {
        return startCoor;
    }

    public void setStartCoor(String startCoor) {
        this.startCoor = startCoor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
