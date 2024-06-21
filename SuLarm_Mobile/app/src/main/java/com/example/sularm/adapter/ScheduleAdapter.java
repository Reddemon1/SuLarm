package com.example.sularm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sularm.R;
import com.example.sularm.model.Schedule;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {
    Context context;
    List<Schedule> scheduleList;
    OnSwitch onSwitch;

    public ScheduleAdapter(Context context, List<Schedule> scheduleList,OnSwitch onSwitch) {
        this.context = context;
        this.scheduleList = scheduleList;
        this.onSwitch = onSwitch;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScheduleViewHolder(LayoutInflater.from(context).inflate(R.layout.schedule_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        holder.time.setText(scheduleList.get(position).getTime());
        holder.location.setText(scheduleList.get(position).getLocation());
        holder.arrivedBefore.setText(scheduleList.get(position).getArrivedBefore());
        if (scheduleList.get(position).getStatus().equals(1)){
            holder.powerSwitch.setChecked(true);
        }else {
            holder.powerSwitch.setChecked(false);
        }

        holder.powerSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.powerSwitch.isChecked()){
                    scheduleList.get(position).setStatus(1);
                }else {
                    scheduleList.get(position).setStatus(0);
                }
                onSwitch.onSwitchClick(position, scheduleList.get(position));
            }
        });
//        holder.
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }
}
