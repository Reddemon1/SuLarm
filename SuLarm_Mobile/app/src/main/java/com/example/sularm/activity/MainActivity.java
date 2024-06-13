package com.example.sularm.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sularm.R;
import com.example.sularm.adapter.ScheduleAdapter;
import com.example.sularm.model.Schedule;
import com.example.sularm.viewmodel.ScheduleViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView scheduleList;
    private List<Schedule> scList = new ArrayList<>();
    private ScheduleAdapter scheduleAdapter;
    private ScheduleViewModel scheduleViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        scheduleList = findViewById(R.id.scheduleList);
        scheduleList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        scheduleAdapter = new ScheduleAdapter(getApplicationContext(),scheduleViewModel.getScheduleList().getValue());
        scheduleAdapter = new ScheduleAdapter(getApplicationContext(),scList);
        scheduleList.setAdapter(scheduleAdapter);
        scheduleViewModel = new ViewModelProvider(this).get(ScheduleViewModel.class);
        scheduleViewModel.getScheduleList().observe(this, new Observer<List<Schedule>>() {
            @Override
            public void onChanged(List<Schedule> schedules) {
                scList.clear();
                scList.addAll(schedules);
                scheduleAdapter.notifyDataSetChanged();
            }
        });
        scheduleViewModel.readScheduleAPI();

    }
}