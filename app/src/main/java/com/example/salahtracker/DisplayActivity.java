package com.example.salahtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ListView;

public class DisplayActivity extends AppCompatActivity {
    CalendarView calander;
    ListView dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        calander = findViewById(R.id.calendarView);
        dataList = findViewById(R.id.data);
    }
}