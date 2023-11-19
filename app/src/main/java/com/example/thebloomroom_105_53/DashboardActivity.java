package com.example.thebloomroom_105_53;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DashboardActivity extends AppCompatActivity {
    private TextView textViewWelcome;
    ImageButton imageButtonViewCartItems;

    //bottom navigation
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        imageButtonViewCartItems = findViewById(R.id.imageButtonViewCartItems);

        //bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.home);

        imageButtonViewCartItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CartActivity.class);
                // Start the SignupActivity
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        textViewWelcome = findViewById(R.id.textViewWelcome);
        // Retrieve username from Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("USERNAME")) {
            String username = intent.getStringExtra("USERNAME");

            // Display the username in the TextView
            String welcomeMessage = "Username: " + username;
            textViewWelcome.setText(welcomeMessage);
        }

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.shop) {
                    startActivity(new Intent(getApplicationContext(), ShopActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                } else if(item.getItemId()== R.id.home){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0,0);
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

    public void onLogOutClick(View view) {
        // Handle logout logic here
        // For example, you might start a new LoginActivity
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);

        SharedPreferences preferences = getSharedPreferences("user_credentials", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear(); // Remove all stored data related to user credentials
        editor.apply();


        // Finish the current activity to prevent going back to it with the back button
        finish();
    }


}