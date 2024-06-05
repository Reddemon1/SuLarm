package com.example.sularm.api;

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
    public ApiService apiService;

    public void initApi(){
        OkHttpClient client = new OkHttpClient();
        Retrofit api = new Retrofit.Builder()
                .baseUrl("http://localhost:8000/api")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = api.create(ApiService.class);
    }

    //    GitHubService service = retrofit.create(GitHubService.class);
}
