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
import com.example.sularm.model.request.RequestSchedule;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public class ScheduleViewModel extends ViewModel {
    private MutableLiveData<List<Schedule>> scheduleList = new MutableLiveData<>(Collections.emptyList());
    InitDataFromAPI api = new InitDataFromAPI();
    public void readScheduleAPI() {
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
                Log.e("Eror API", "onFailure: ", throwable);
//                notifyAll();
            }
        });
    }
    public void changeStatus(Schedule schedule,Context context){
        RequestSchedule requestSchedule = new RequestSchedule(schedule.getTime(), schedule.getArrivedBefore(), schedule.getLocation(), schedule.getStatus(), schedule.getEstimatedTravelTime());

        api.getApiService().editSchedule(schedule.getId(), requestSchedule).enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                if (schedule.getStatus() == 1){
                    Toast.makeText(context, "Alarm Turned On", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context, "Alarm Turned Off", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable throwable) {
                Log.e("Eror bangsat",  "kece",throwable );
            }
        });
    }
    public void newSchedule(){

    }

    public void setScheduleList(MutableLiveData<List<Schedule>> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public MutableLiveData<List<Schedule>> getScheduleList() {
        return scheduleList;
    }
}
