package com.example.sularm.activity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
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

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnSwitch {
    private RecyclerView scheduleList;
    private List<Schedule> scList = new ArrayList<>();
    private ScheduleAdapter scheduleAdapter;
    private ScheduleViewModel scheduleViewModel;
    private AlarmManager alarmManager;
    private Calendar calendar;
    private PendingIntent pendingIntent;
    private Button newSchedule;
    private static final int REQUEST_CODE_NOTIFICATION = 1;

    public void initData(){
        createNotificationChannel();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE_NOTIFICATION);
            }
        }
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
    }
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
        initData();
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

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Notif Sularm";
            String desc = "Channel for Alarm Manager";
            int imp = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("apaseh", name, imp);
            channel.setDescription(desc);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    @Override
    public void onSwitchClick(int position, Schedule schedule) {
        scList.set(position, schedule);
        scheduleViewModel.setScheduleList(new MutableLiveData<>(scList));
        scheduleViewModel.changeStatus(schedule, getApplicationContext());

        String[] time = schedule.getArrivedBefore().split(":");
        String[] estimated = schedule.getEstimatedTravelTime().split(":");
        String[] prep = schedule.getPreparationTime().split(":");
        if (schedule.getStatus() == 0) { // Off
            cancelAlarm(position);
        } else { // On
            setAlarm(position, time, estimated,prep);
        }
    }


    private void setAlarm(int position, String[] time, String[] estimated,String[] prep ) {
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MINUTE, (Integer.parseInt(estimated[0]) * 60 + Integer.parseInt(estimated[1])) * -1);
        calendar.add(Calendar.MINUTE, (Integer.parseInt(prep[0]) * 60 + Integer.parseInt(prep[1])) * -1);
        if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        intent.putExtra("position", position);
        pendingIntent = PendingIntent.getBroadcast(this, position, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY , pendingIntent);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(MainActivity.this, "Alarm Set on "+String.format("%02d",calendar.get(Calendar.HOUR_OF_DAY))+ ":"+String.format("%02d",calendar.get(Calendar.MINUTE)), Toast.LENGTH_SHORT).show();
    }

    private void cancelAlarm(int position) {
        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, position, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
        if (alarmManager == null) {
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(MainActivity.this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
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