package com.example.sularm.viewmodel;

import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.core.content.ContextCompat.startActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.sularm.activity.AddAlarmActivity;
import com.example.sularm.activity.AlarmReceiver;
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
    public RequestSchedule convertToBodyRequest(Schedule schedule){
        RequestSchedule requestSchedule = new RequestSchedule(
                schedule.getTime(),
                schedule.getArrivedBefore(),
                schedule.getEstimatedTravelTime(),
                schedule.getPreparationTime(),
                schedule.getLocationEnd(),
                schedule.getEndCoor(),
                schedule.getLocationStart(),
                schedule.getStartCoor(),
                schedule.getStatus()
        );
        return requestSchedule;
    }
    public void deleteSchedule(Integer id, Context context){
        api.getApiService().deleteSchedule(id).enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                Toast.makeText(context, "Alarm Deleted", Toast.LENGTH_LONG).show();
//                readScheduleAPI();
            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable throwable) {

            }
        });
    }
    public void changeStatus(Schedule schedule,Context context){
        Log.e("APALAH INI", "changeStatus: "+schedule.getId() +" "+schedule.getTime()+
                        " "+schedule.getArrivedBefore()+
                " "+schedule.getEstimatedTravelTime()+
                " "+schedule.getPreparationTime()+
                " "+schedule.getLocationEnd()+
                " "+schedule.getEndCoor()+
                " "+schedule.getLocationStart()+
                " "+schedule.getStartCoor()+
                " "+schedule.getStatus());

        api.getApiService().editSchedule(schedule.getId(), convertToBodyRequest(schedule)).enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
//                if (schedule.getStatus() == 1){
//                    Toast.makeText(context, "Alarm Turned On", Toast.LENGTH_LONG).show();
//                }else{
//                    Toast.makeText(context, "Alarm Turned Off", Toast.LENGTH_LONG).show();
//                }
            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable throwable) {
                Log.e("Eror bangsat",  "kece",throwable );
            }
        });
    }

    public void newSchedule(Schedule schedule, Context context){
        api.initApi();
        api.getApiService().createSchedule(convertToBodyRequest(schedule)).enqueue(new Callback<Schedule>() {
            @Override
            public void onResponse(Call<Schedule> call, Response<Schedule> response) {
                Toast.makeText(context, "New Schedule Added", Toast.LENGTH_LONG).show();
                readScheduleAPI();
            }

            @Override
            public void onFailure(Call<Schedule> call, Throwable throwable) {
                Log.e("APASIH", "onFailure: ",throwable );
            }
        });
    }

    public void setScheduleList(MutableLiveData<List<Schedule>> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public MutableLiveData<List<Schedule>> getScheduleList() {
        return scheduleList;
    }
}
