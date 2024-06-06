package com.example.sularm.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.sularm.activity.MainActivity;
import com.example.sularm.api.InitDataFromAPI;
import com.example.sularm.model.Schedule;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleViewModel {
    private List<Schedule> scheduleList = new ArrayList<Schedule>() ;
    public void readScheduleAPI(){
        InitDataFromAPI api = new InitDataFromAPI();
        api.initApi();
        api.getApiService().getSchedule().enqueue(new Callback<List<Schedule>>() {
            @Override
            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                scheduleList.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<Schedule>> call, Throwable throwable) {

            }
        });
        scheduleList.forEach(schedule -> {
            Log.e("Get_Data",  schedule.getLocation()+" "+schedule.getTime()+" "+schedule.getArrivedBefore());
        });
//        return scheduleList;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }
}
