package com.example.sularm.api.interfaces;

import com.example.sularm.model.Schedule;
import com.example.sularm.model.request.RequestSchedule;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("Schedule")
    Call<List<Schedule>>  getSchedule();

    @POST("Schedule/")
    Call<Schedule> createSchedule(@Body RequestSchedule requestSchedule);

    @PUT("Schedule/{id}")
    Call<Schedule> editSchedule(@Path("id") int id,
                                @Body RequestSchedule requestSchedule);

    @DELETE("Schedule/{id}")
    Call<Schedule> deleteSchedule(@Path("id") int id);
}
