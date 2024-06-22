package com.example.sularm.model.request;

import com.google.gson.annotations.SerializedName;

public class RequestSchedule {
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

    public RequestSchedule(String time, String arrivedBefore, String estimatedTravelTime, String preparationTime, String locationEnd, String endCoor, String locationStart, String startCoor, Integer status) {
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
}
