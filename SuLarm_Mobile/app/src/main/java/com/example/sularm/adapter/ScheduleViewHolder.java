package com.example.sularm.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sularm.R;

public class ScheduleViewHolder extends RecyclerView.ViewHolder {

    TextView arrivedBefore, time, location;
    Switch powerSwitch;
    Button deleteBtn;
    public ScheduleViewHolder(@NonNull View itemView) {
        super(itemView);
        arrivedBefore = itemView.findViewById(R.id.arrivedBefore);
        location = itemView.findViewById(R.id.location);
        time = itemView.findViewById(R.id.estTime);
        powerSwitch = itemView.findViewById(R.id.power_switch);
        deleteBtn = itemView.findViewById(R.id.deleteBtn);
    }
}
