package com.example.sularm.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;

public class Schedule {
    @SerializedName("id")
    private Integer id;
    @SerializedName("time")
    private Time time;
    @SerializedName("arrived_before")
    private Time arrivedBefore;
    @SerializedName("location")
    private String location;

    public Schedule(Integer id, Time time, Time arrivedBefore, String location) {
        this.id = id;
        this.time = time;
        this.arrivedBefore = arrivedBefore;
        this.location = location;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Time getArrivedBefore() {
        return arrivedBefore;
    }

    public void setArrivedBefore(Time arrivedBefore) {
        this.arrivedBefore = arrivedBefore;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
