package com.example.sularm.model;

import com.google.gson.annotations.SerializedName;

import java.sql.Time;

public class Schedule {
    @SerializedName("id")
    private Integer id;
    @SerializedName("time")
    private String time;
    @SerializedName("arrived_before")
    private String arrivedBefore;
    @SerializedName("location")
    private String location;

    public Schedule(Integer id, String time, String arrivedBefore, String location) {
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
}
