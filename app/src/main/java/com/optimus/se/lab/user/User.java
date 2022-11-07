package com.optimus.se.lab.user;

import android.content.Context;
import android.widget.ListView;

import androidx.fragment.app.FragmentManager;

import com.optimus.se.lab.TimeTable;
import com.optimus.se.lab.TimeTableAdapter;

import java.util.ArrayList;

public abstract class User {
    private String username;
    private String password;
    private int type;
    protected TimeTableAdapter timeTableAdapter;
    protected ListView lv_timeTable;

    public User(String username, String password, int type, TimeTableAdapter timeTableAdapter, ListView lv_timeTable) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.timeTableAdapter = timeTableAdapter;
        this.lv_timeTable = lv_timeTable;
    }

    public abstract void showTimeTable(Context context, ArrayList<TimeTable> timeTables,
                                       String ID);

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}


