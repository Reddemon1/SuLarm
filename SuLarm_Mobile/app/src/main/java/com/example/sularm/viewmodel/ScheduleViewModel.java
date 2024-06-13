package com.example.sularm.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.sularm.activity.MainActivity;
import com.example.sularm.api.InitDataFromAPI;
import com.example.sularm.model.Schedule;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleViewModel extends ViewModel {
    private MutableLiveData<List<Schedule>> scheduleList = new MutableLiveData<>(Collections.emptyList());
    public void readScheduleAPI() {

        InitDataFromAPI api = new InitDataFromAPI();
        api.initApi();
        api.getApiService().getSchedule().enqueue(new Callback<List<Schedule>>() {
            @Override
            public  void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
                scheduleList.postValue(response.body());
               Log.e("Get_Data",  response.body().size()+"");
//               ScheduleViewModel.this.notifyAll();
            }

            @Override
            public void onFailure(Call<List<Schedule>> call, Throwable throwable) {
//
                Log.e("Eror API", "onFailure: ", throwable);
//                notifyAll();
            }
        });
//        Log.e("Get_Datas",  scheduleList.size()+"");

//        scheduleList.forEach(schedule -> {
//            Log.e("Get_Data",  schedule.getLocation()+" "+schedule.getTime()+" "+schedule.getArrivedBefore());
//        });
//        return scheduleList;
    }

    public MutableLiveData<List<Schedule>> getScheduleList() {
        return scheduleList;
    }
}
