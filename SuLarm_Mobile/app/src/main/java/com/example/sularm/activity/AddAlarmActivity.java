package com.example.sularm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.sularm.R;
import com.example.sularm.api.RetrofitClient;
import com.example.sularm.api.interfaces.MapboxGeocodingService;
import com.example.sularm.model.CarmenFeature;
import com.example.sularm.model.GeocodingResponse;
import com.example.sularm.model.Schedule;
import com.example.sularm.viewmodel.ScheduleViewModel;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.mapbox.api.directions.v5.DirectionsCriteria;

import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.api.directions.v5.models.RouteOptions;

import com.mapbox.geojson.Point;

import com.mapbox.navigation.base.options.NavigationOptions;

import com.mapbox.navigation.base.route.NavigationRoute;
import com.mapbox.navigation.base.route.NavigationRouterCallback;
import com.mapbox.navigation.base.route.RouterFailure;
import com.mapbox.navigation.base.route.RouterOrigin;
import com.mapbox.navigation.core.MapboxNavigation;

import java.util.Arrays;
import java.util.List;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAlarmActivity extends AppCompatActivity {
    private ScheduleViewModel scheduleViewModel;
    private MapboxNavigation mapboxNavigation;
    private Button newSchedule, findRoute;
    private MaterialTimePicker timePicker, arrivedPicker, preparationPicker, estimatedPicker;
    private TextView time,arrivedTime,preparationTime,backbtn, estimatedTime;
    private AutoCompleteTextView locationEnd,locationStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_alarm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        AddressAutofill.create(accessToken);
//        PlaceAutocomplete.create();
        scheduleViewModel = new ViewModelProvider(this).get(ScheduleViewModel.class);
        time = (TextView) findViewById(R.id.timeInput);
        arrivedTime = (TextView) findViewById(R.id.arrivedBeforeInput);
        preparationTime = (TextView) findViewById(R.id.preparationInput);
        backbtn = (TextView) findViewById(R.id.backBtn);
        estimatedTime = (TextView) findViewById(R.id.estimatedTime);
        newSchedule = (Button) findViewById(R.id.newSchedule);
        findRoute = (Button) findViewById(R.id.findRoute);
        locationEnd = (AutoCompleteTextView) findViewById(R.id.locationEndInput);
        locationStart = (AutoCompleteTextView) findViewById(R.id.locationStartInput);

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .setHour(12)
                        .setMinute(0)
                        .setTitleText("Select Alarm Time")
                        .build();
                timePicker.show(getSupportFragmentManager(), "androidknowledge");
                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        time.setText(String.format("%02d",timePicker.getHour()) +":"+ String.format("%02d",timePicker.getMinute()));
                    }
                });
            }
        });
        arrivedTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrivedPicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .setHour(12)
                        .setMinute(0)
                        .setTitleText("Select Alarm Time")
                        .build();
                arrivedPicker.show(getSupportFragmentManager(), "androidknowledge");
                arrivedPicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        arrivedTime.setText(String.format("%02d",arrivedPicker.getHour()) +":"+ String.format("%02d",arrivedPicker.getMinute()));
                    }
                });
            }
        });
        preparationTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preparationPicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .setHour(12)
                        .setMinute(0)
                        .setTitleText("Select Alarm Time")
                        .build();
                preparationPicker.show(getSupportFragmentManager(), "androidknowledge");
                preparationPicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (preparationPicker.getHour() >= 12){
                            preparationTime.setText(String.format("%02d",preparationPicker.getHour() - 12) +":"+ String.format("%02d",preparationPicker.getMinute()));
                        }else{

                            preparationTime.setText(String.format("%02d",preparationPicker.getHour()) +":"+ String.format("%02d",preparationPicker.getMinute()));
                        }
                    }
                });
            }
        });


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddAlarmActivity.this, MainActivity.class));
                finish();
            }
        });

        newSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Schedule schedule = new Schedule(-1, time.getText().toString(), arrivedTime.getText().toString(), estimatedTime.getText().toString(),preparationTime.getText().toString(), locationEnd.getText().toString(),"00 00", locationStart.getText().toString() , "00 00", 1);
//                Log.e(TAG, "onClick: ", );
                scheduleViewModel.newSchedule(schedule, getApplicationContext());
                startActivity(new Intent(AddAlarmActivity.this, MainActivity.class));
                finish();
            }
        });
//        setupNavigation();
        findRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // masih input manual, API  estimasi masih eror
                estimatedPicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_12H)
                        .setHour(12)
                        .setMinute(0)
                        .setTitleText("Select Alarm Time")
                        .build();
                estimatedPicker.show(getSupportFragmentManager(), "androidknowledge");
                estimatedPicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (estimatedPicker.getHour() >= 12){
                            estimatedTime.setText(String.format("%02d",estimatedPicker.getHour() - 12) +":"+ String.format("%02d",estimatedPicker.getMinute()));
                        }else{

                            estimatedTime.setText(String.format("%02d",estimatedPicker.getHour()) +":"+ String.format("%02d",estimatedPicker.getMinute()));
                        }
                    }
                });
                //Masih Eror
//                searchAndNavigate(locationStart.getText().toString(),locationEnd.getText().toString());
            }
        });
//        MapboxMap map = new MapboxMap(new );
    }
    private void setupNavigation() {
        NavigationOptions navigationOptions = new NavigationOptions.Builder(this).build();
        mapboxNavigation = new MapboxNavigation(navigationOptions);
    }
    private void searchAndNavigate(String originPlace, String destinationPlace) {
        MapboxGeocodingService service = RetrofitClient.getRetrofitInstance().create(MapboxGeocodingService.class);

        // Get origin coordinates
        Call<GeocodingResponse> callOrigin = service.getCoordinates(getString(R.string.mapbox_access_token), originPlace, 1);
        callOrigin.enqueue(new Callback<GeocodingResponse>() {
            @Override
            public void onResponse(Call<GeocodingResponse> call, Response<GeocodingResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<CarmenFeature> features = response.body().getFeatures();
                    if (!features.isEmpty()) {
                        CarmenFeature feature = features.get(0);
                        Point origin = feature.getGeometry();
                        Call<GeocodingResponse> callDestination = service.getCoordinates(getString(R.string.mapbox_access_token), originPlace, 1);
                        callDestination.enqueue(new Callback<GeocodingResponse>() {
                            @Override
                            public void onResponse(Call<GeocodingResponse> call, Response<GeocodingResponse> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    List<CarmenFeature> features = response.body().getFeatures();
                                    if (!features.isEmpty()) {
                                        CarmenFeature feature = features.get(0);
                                        Point destination = feature.getGeometry();
                                        findRoute(origin, destination);
                                    } else {
                                        Log.i("MapboxGeocoding", "ga ketemu woi");
                                    }
                                } else {
                                    Log.e("MapboxGeocoding", "gagalll: " + response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<GeocodingResponse> call, Throwable t) {
                                Log.e("MapboxGeocoding", "Network error for destination", t);
                            }
                        });
                    } else {
                        Log.i("MapboxGeocoding", "start tak da");
                    }
                } else {
                    Log.e("MapboxGeocoding", "gagalllll start: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<GeocodingResponse> call, Throwable throwable) {
                Log.e("Geocoding", "Network error", throwable);
            }
        });
    }
    private void findRoute(Point origin, Point destination) {

        RouteOptions routeOptions = RouteOptions.builder()
                .coordinatesList(Arrays.asList(origin, destination))
                .profile(DirectionsCriteria.PROFILE_DRIVING)
                .build();

        mapboxNavigation.requestRoutes(
                routeOptions, new NavigationRouterCallback() {
                    @Override
                    public void onRoutesReady(@NonNull List<NavigationRoute> list, @NonNull String s) {
                        if (!list.isEmpty()) {
                            DirectionsRoute route = list.get(0).getDirectionsRoute();
                            Log.i("MapboxNavigation", "Route distance: " + route.distance() + " meters");
                            Log.i("MapboxNavigation", "Estimated travel time: " + route.duration() + " seconds");
//                            route.duration();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull List<RouterFailure> list, @NonNull RouteOptions routeOptions) {
                        Log.e("MapboxNavigation", "Error: " + list.get(0).getMessage());
                    }

                    @Override
                    public void onCanceled(@NonNull RouteOptions routeOptions, @NonNull String s) {
                        Log.e("MapboxNavigation", "Route request canceled");
                    }
                }

        );
    }
}