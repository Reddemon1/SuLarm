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
import com.example.sularm.model.Schedule;
import com.example.sularm.viewmodel.ScheduleViewModel;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.mapbox.bindgen.Expected;
import com.mapbox.geojson.BoundingBox;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.search.autocomplete.PlaceAutocomplete;
import com.mapbox.search.autocomplete.PlaceAutocompleteOptions;
import com.mapbox.search.autocomplete.PlaceAutocompleteResult;
import com.mapbox.search.autocomplete.PlaceAutocompleteSuggestion;

import java.util.List;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;

public class AddAlarmActivity extends AppCompatActivity {
    private MapView mapView;
    private ScheduleViewModel scheduleViewModel;
    private PlaceAutocomplete placeAutocomplete;
    private Button newSchedule;
    private List<PlaceAutocompleteSuggestion> suList;
    private MaterialTimePicker timePicker, arrivedPicker, preparationPicker;
    private TextView time,arrivedTime,preparationTime,backbtn;
    private AutoCompleteTextView locationEnd,locationStart;
    private PlaceAutocompleteOptions placeAutocompleteOptions = new PlaceAutocompleteOptions(3);
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
        String accessToken = getString(R.string.mapbox_access_token);
//        AddressAutofill.create(accessToken);
//        PlaceAutocomplete.create();
        scheduleViewModel = new ViewModelProvider(this).get(ScheduleViewModel.class);
        time = (TextView) findViewById(R.id.timeInput);
        arrivedTime = (TextView) findViewById(R.id.arrivedBeforeInput);
        preparationTime = (TextView) findViewById(R.id.preparationInput);
        backbtn = (TextView) findViewById(R.id.backBtn);
        newSchedule = (Button) findViewById(R.id.newSchedule);
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
                        preparationTime.setText(String.format("%02d",preparationPicker.getHour()) +":"+ String.format("%02d",preparationPicker.getMinute()));
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
                Schedule schedule = new Schedule(-1, time.getText().toString(), arrivedTime.getText().toString(), "07:00",preparationTime.getText().toString(), locationEnd.getText().toString(),"00 00", locationStart.getText().toString() , "00 00", 1);
//                Log.e(TAG, "onClick: ", );
                scheduleViewModel.newSchedule(schedule, getApplicationContext());
                startActivity(new Intent(AddAlarmActivity.this, MainActivity.class));
                finish();
            }
        });


        PlaceAutocomplete placeAutocomplete = new PlaceAutocomplete() {
            @Nullable
            @Override
            public Object suggestions(@NonNull String s, @Nullable BoundingBox boundingBox, @Nullable Point point, @NonNull PlaceAutocompleteOptions placeAutocompleteOptions, @NonNull Continuation<? super Expected<Exception, List<PlaceAutocompleteSuggestion>>> continuation) {
                return null;
            }

            @Nullable
            @Override
            public Object reverse(@NonNull Point point, @NonNull PlaceAutocompleteOptions placeAutocompleteOptions, @NonNull Continuation<? super Expected<Exception, List<PlaceAutocompleteSuggestion>>> continuation) {
                return null;
            }

            @Nullable
            @Override
            public Object select(@NonNull PlaceAutocompleteSuggestion placeAutocompleteSuggestion, @NonNull Continuation<? super Expected<Exception, PlaceAutocompleteResult>> continuation) {
                return null;
            }
        };
        placeAutocomplete.suggestions("apapun", null, null, placeAutocompleteOptions, new Continuation<Expected<Exception, List<PlaceAutocompleteSuggestion>>>() {
            @NonNull
            @Override
            public CoroutineContext getContext() {
                return null;
            }

            @Override
            public void resumeWith(@NonNull Object o) {

            }
        });
//        MapboxMap map = new MapboxMap(new );
    }
}