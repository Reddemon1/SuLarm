package com.example.sularm.api;

import android.util.Log;

import com.example.sularm.api.interfaces.ApiService;
import com.example.sularm.model.Schedule;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class InitDataFromAPI {
    private ApiService apiService;

    public void initApi(){
        OkHttpClient client = new OkHttpClient();
        Retrofit api = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = api.create(ApiService.class);

//        apiService.getSchedule().enqueue(new Callback<List<Schedule>>() {
//            @Override
//            public void onResponse(Call<List<Schedule>> call, Response<List<Schedule>> response) {
//                Log.e("CUKY", "bisa ");
//            }
//
//            @Override
//            public void onFailure(Call<List<Schedule>> call, Throwable throwable) {
//                Log.e("CUKY", "gabisa ");
//            }
//        });
    }

    public ApiService getApiService() {
        return apiService;
    }

//    public Ca
    //    GitHubService service = retrofit.create(GitHubService.class);
}
