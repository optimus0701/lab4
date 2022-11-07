package com.optimus.se.lab.user;

import android.content.Context;
import android.widget.ListView;

import com.optimus.se.lab.R;
import com.optimus.se.lab.TimeTable;
import com.optimus.se.lab.TimeTableAdapter;

import java.util.ArrayList;

public class Manager extends User {

    public Manager(String username, String password, TimeTableAdapter timeTableAdapter, ListView lv_timeTable) {
        super(username, password, 2, timeTableAdapter, lv_timeTable);
    }

    @Override
    public void showTimeTable(Context context, ArrayList<TimeTable> timeTables, String ID) {
        ArrayList<TimeTable> timeTableArrayList = new ArrayList<>();
        for (TimeTable timeTable : timeTables) {
            if (timeTable.getId().equals(ID)) {
                timeTableArrayList.add(timeTable);
            }
        }
        timeTableAdapter = new TimeTableAdapter(context, R.layout.item_time_table, timeTableArrayList, getType());

        lv_timeTable.setAdapter(timeTableAdapter);
    }
}
