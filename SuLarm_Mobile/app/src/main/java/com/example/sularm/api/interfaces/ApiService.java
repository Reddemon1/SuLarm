package com.example.sularm.api.interfaces;

import com.example.sularm.model.Schedule;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @GET("Schedule")
    Call<List<Schedule>>  getSchedule();

    @POST("Schedule/")
    Call<Schedule> createSchedule(@Body Schedule schedule);
}
