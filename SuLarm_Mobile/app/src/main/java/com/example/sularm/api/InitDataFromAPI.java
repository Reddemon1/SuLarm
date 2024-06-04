package com.example.sularm.api;

import com.example.sularm.api.interfaces.ApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class InitDataFromAPI {
    public ApiService apiService;

    public void initApi(){
        OkHttpClient client = new OkHttpClient();
        Retrofit api = new Retrofit.Builder()
                .baseUrl("http://localhost:8000/api")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = api.create(ApiService.class);
    }

    public void getData(){
        apiService.getSchedule();
    }

    //    GitHubService service = retrofit.create(GitHubService.class);
}
