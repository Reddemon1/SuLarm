package com.example.sularm.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sularm.R;
import com.example.sularm.adapter.ScheduleAdapter;
import com.example.sularm.model.Schedule;
import com.example.sularm.viewmodel.ScheduleViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView scheduleList;
//    private List<Schedule> scList;
    private ScheduleViewModel scheduleViewModel = new ScheduleViewModel();
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

        scheduleViewModel.readScheduleAPI();
        scheduleList = findViewById(R.id.scheduleList);
        scheduleList.setLayoutManager(new LinearLayoutManager(this));
        scheduleList.setAdapter(new ScheduleAdapter(getApplicationContext(),scheduleViewModel.getScheduleList()));
        scheduleViewModel.getScheduleList().forEach(schedule -> {
            Log.e("Get_Data",  schedule.getLocation()+" "+schedule.getTime()+" "+schedule.getArrivedBefore());
        });
    }
}