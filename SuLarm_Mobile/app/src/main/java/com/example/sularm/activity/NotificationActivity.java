package com.example.sularm.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.sularm.R;
import com.example.sularm.model.Schedule;
import com.example.sularm.viewmodel.ScheduleViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    public ScheduleViewModel scheduleViewModel;
    private List<Schedule> scList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        int position = data.getInt("position", -1);

        scheduleViewModel = new ViewModelProvider(this).get(ScheduleViewModel.class);
        scheduleViewModel.getScheduleList().observe(this, new Observer<List<Schedule>>() {
            @Override
            public void onChanged(List<Schedule> schedules) {
                scList.clear();
                scList.addAll(schedules);

//                scList.get(position).setStatus(0);
//                scheduleViewModel.setScheduleList(new MutableLiveData<>(scList));
//                scheduleViewModel.changeStatus(scList.get(position), getApplicationContext());
            }
        });
        scheduleViewModel.readScheduleAPI();



    }
}