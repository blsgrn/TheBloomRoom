package com.example.thebloomroom_105_53;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {
    private RecyclerView recyclerViewFlowers;
    private ShopAdapter shopAdapter;
    private List<Flower> flowerList;
    private TextView textViewLogin;

    private Button buttonViewCart;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        recyclerViewFlowers = findViewById(R.id.recyclerViewFlowers);
        flowerList = new ArrayList<>();

        fetchFlowersFromDatabase();

        // Set up RecyclerView
        shopAdapter = new ShopAdapter(flowerList, this);
        recyclerViewFlowers.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFlowers.setAdapter(shopAdapter);

        //view cart button
        buttonViewCart = findViewById(R.id.buttonViewCart);
        textViewLogin = findViewById(R.id.textViewLogin);


        //bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.shop);

        // In any other activity where you want to access the username
        SharedPreferences preferences = getSharedPreferences("user_credentials", MODE_PRIVATE);
        String username = preferences.getString("username", "Please Login!");

        // Display the username in the TextView
        String hiMessage = "Hi" + username + "!";
        textViewLogin.setText(hiMessage);



            buttonViewCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!"Please Login!".equals(username)){
                        Intent intent = new Intent(v.getContext(), CartActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    } else {
                        Toast.makeText(ShopActivity.this, "Please login to add to cart!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(v.getContext(), LoginActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    }

                }
            });



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                } else if(item.getItemId()== R.id.dashboard){
                    //logged in status based on shared preferences
                    if (!"Please Login!".equals(username)){
                        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                        overridePendingTransition(0,0);
                    } else {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        overridePendingTransition(0,0);
                    }
                    return true;
                } else if(item.getItemId()==R.id.info){
                    startActivity(new Intent(getApplicationContext(), InfoActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }

                return true;
            }
        });


    }
    private void fetchFlowersFromDatabase() {
        DBHelper dbHelper = new DBHelper(this);
        flowerList = dbHelper.getAllFlowers();
    }
}