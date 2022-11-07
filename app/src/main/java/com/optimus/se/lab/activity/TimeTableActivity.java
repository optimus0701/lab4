package com.optimus.se.lab.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.optimus.se.lab.R;
import com.optimus.se.lab.TimeTable;
import com.optimus.se.lab.TimeTableAdapter;
import com.optimus.se.lab.user.Manager;
import com.optimus.se.lab.user.Student;
import com.optimus.se.lab.user.Teacher;

import java.util.ArrayList;

public class TimeTableActivity extends AppCompatActivity {

    private ListView lv_timeTable;
    private ArrayList<String> items;
    private TimeTableAdapter timeTableAdapter;
    private ArrayList<TimeTable> timeTables;
    private ImageView img_add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        lv_timeTable = findViewById(R.id.lv_time_table);
        img_add = findViewById(R.id.img_add);


        Intent intent = getIntent();
        int type = intent.getIntExtra("type", -1);
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");

        if (type == 2) {
            img_add.setVisibility(View.VISIBLE);
        }

        items = new ArrayList<>();
        timeTables = new ArrayList<>();

        input();
        timeTableAdapter = new TimeTableAdapter(this, R.layout.item_time_table, timeTables, type);
        switch (type) {
            case 0:
                Student student = new Student(username, password, timeTableAdapter, lv_timeTable);
                student.showTimeTable(this, timeTables, "default");
                break;
            case 1:
                Teacher teacher = new Teacher(username, password, timeTableAdapter, lv_timeTable);
                teacher.showTimeTable(this, timeTables, "default");
                break;
            case 2:
                Manager manager = new Manager(username, password, timeTableAdapter, lv_timeTable);
                manager.showTimeTable(this, timeTables, "default");
                break;
        }

        img_add.setOnClickListener(view -> {
            timeTables.add(new TimeTable("default", items));
            timeTableAdapter.notifyDataSetChanged();
            lv_timeTable.setAdapter(timeTableAdapter);
        });

    }



    private void input() {
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                if (row == 0) {
                    items.add("Thứ " + (col + 2));
                } else {
                    items.add(row + "" + col);
                }
            }
        }
        timeTables.add(new TimeTable("default", items));
    }






}
