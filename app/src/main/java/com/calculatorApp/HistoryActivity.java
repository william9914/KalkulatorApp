package com.calculatorApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.calculatorApp.controller.HistoryAdapter;
import com.calculatorApp.model.MainData;
import com.calculatorApp.model.RoomDB;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RoomDB database;
    private HistoryAdapter historyAdapter;
    private List<MainData> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerView = findViewById(R.id.recycleView);
        Button btn_maps = findViewById(R.id.btn_location);

        database = RoomDB.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.mainDao().getAll());
        historyAdapter = new HistoryAdapter(this,database.mainDao().getAll());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(historyAdapter);

        btn_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HistoryActivity.this, MapsActivity.class));
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        list.clear();
        list.addAll(database.mainDao().getAll());
        historyAdapter.notifyDataSetChanged();
    }

}
