package com.example.sularm.api.interfaces;

import com.example.sularm.model.GeocodingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MapboxGeocodingService {
    @GET("geocoding/v5/mapbox.places/{query}.json")
    Call<GeocodingResponse> getCoordinates(
            @Query("access_token") String accessToken,
            @Query("query") String query,
            @Query("limit") int limit
    );
}
