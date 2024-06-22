package com.example.sularm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sularm.R;
import com.example.sularm.adapter.OnSwitch;
import com.example.sularm.adapter.ScheduleAdapter;
import com.example.sularm.model.Schedule;
import com.example.sularm.viewmodel.ScheduleViewModel;
import com.mapbox.maps.MapView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnSwitch {
    private RecyclerView scheduleList;
    private List<Schedule> scList = new ArrayList<>();
    private ScheduleAdapter scheduleAdapter;
    private ScheduleViewModel scheduleViewModel;
    private Button newSchedule;

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
        scheduleAdapter = new ScheduleAdapter(getApplicationContext(),scList, this);
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

        newSchedule = (Button) findViewById(R.id.newScBtn);
        newSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddAlarmActivity.class);
//                intent.putExtra("viewModel", scheduleViewModel);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onSwitchClick(int position, Schedule schedule) {
//        Toast.makeText(getApplicationContext(), schedule.getLocation(),Toast.LENGTH_LONG).show();
        scList.set(position, schedule);
        MutableLiveData<List<Schedule>> sL = new MutableLiveData<>();
        sL.postValue(scList);
        scheduleViewModel.setScheduleList(sL);
        scheduleViewModel.changeStatus(schedule, getApplicationContext());
    }

    @Override
    public void onDeleteClick(int id) {
        scheduleViewModel.deleteSchedule(id,getApplicationContext());
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