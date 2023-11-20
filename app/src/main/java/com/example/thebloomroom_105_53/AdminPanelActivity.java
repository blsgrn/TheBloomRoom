package com.example.thebloomroom_105_53;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminPanelActivity extends AppCompatActivity {

    ImageButton imageButtonAddFlower, imageButtonViewFlowers, imageButtonReceipt, imageButtonAccounts;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        imageButtonAddFlower = findViewById(R.id.imageButtonAddFlower);
        imageButtonViewFlowers = findViewById(R.id.imageButtonViewFlowers);
        imageButtonReceipt = findViewById(R.id.imageButtonReceipt);
        imageButtonAccounts = findViewById(R.id.imageButtonAccounts);

        imageButtonAddFlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddFlowerActivity.class);
                // Start the SignupActivity
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        imageButtonViewFlowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FlowerListActivity.class);
                // Start the SignupActivity
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });

        imageButtonReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PaymentRecordsActivity.class);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });


    }
}