package com.nagarro.peeyoosh.simplethread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nagarro.peeyoosh.simplethread.threads.DataFillerTask;

import java.util.ArrayList;

public class AsynctaskActivity extends AppCompatActivity {
    private static final String TAG = "AsynctaskActivity";
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asynctask);

        listView = findViewById(R.id.listview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        listView.setAdapter(arrayAdapter);
        new DataFillerTask(arrayAdapter).execute();
    }
}
