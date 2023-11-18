package com.example.thebloomroom_105_53;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {
    private TextView textViewWelcome;
    ImageButton imageButtonViewCartItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        imageButtonViewCartItems = findViewById(R.id.imageButtonViewCartItems);

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
    }

    public void onLogOutClick(View view) {
        // Handle logout logic here
        // For example, you might start a new LoginActivity
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);

        // Finish the current activity to prevent going back to it with the back button
        finish();
    }
}