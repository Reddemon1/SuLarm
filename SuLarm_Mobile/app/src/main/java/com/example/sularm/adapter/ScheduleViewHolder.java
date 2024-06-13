package com.example.sularm.adapter;

import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sularm.R;

import java.sql.Time;

public class ScheduleViewHolder extends RecyclerView.ViewHolder {

    TextView arrivedBefore, time, location;
    Switch powerSwitch;
    public ScheduleViewHolder(@NonNull View itemView) {
        super(itemView);
        arrivedBefore = itemView.findViewById(R.id.arrivedBefore);
        location = itemView.findViewById(R.id.location);
        time = itemView.findViewById(R.id.time);
        powerSwitch = itemView.findViewById(R.id.power_switch);
    }
}
