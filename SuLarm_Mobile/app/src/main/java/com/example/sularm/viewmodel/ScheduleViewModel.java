package com.example.sularm.viewmodel;

import com.example.sularm.api.InitDataFromAPI;
import com.example.sularm.model.Schedule;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleViewModel {
    public List<Schedule> scheduleList;
    public void readScheduleAPI(){
        InitDataFromAPI api = new InitDataFromAPI();
        api.apiService.getSchedule().enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                scheduleList.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Schedule>> call, Throwable throwable) {

            }
        });
    }
}
