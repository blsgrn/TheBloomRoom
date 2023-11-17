package com.example.thebloomroom_105_53;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class FlowerListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewFlowers;
    private FlowerListAdapter flowerListAdapter;
    private List<Flower> flowerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower_list);

        recyclerViewFlowers = findViewById(R.id.recyclerViewFlowers);
        flowerList = new ArrayList<>();

        fetchFlowersFromDatabase();

        // Set up RecyclerView
        flowerListAdapter = new FlowerListAdapter(flowerList, this);
        recyclerViewFlowers.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFlowers.setAdapter(flowerListAdapter);

    }
    private void fetchFlowersFromDatabase() {
        DBHelper dbHelper = new DBHelper(this);
        flowerList = dbHelper.getAllFlowers();
    }

}