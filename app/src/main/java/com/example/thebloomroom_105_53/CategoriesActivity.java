package com.example.thebloomroom_105_53;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CategoriesActivity extends AppCompatActivity {

    Button btnSpecial, btnMixed, btnSeasonal, btnOrchid, btnLily, btnRose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        btnSpecial = findViewById(R.id.btnSpecial);
        btnMixed = findViewById(R.id.btnMixed);
        btnSeasonal = findViewById(R.id.btnSeasonal);
        btnOrchid = findViewById(R.id.btnOrchid);
        btnLily = findViewById(R.id.btnLily);
        btnRose = findViewById(R.id.btnRose);

        btnSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the category associated with the clicked button
                String selectedCategory = "Special"; // Change this to dynamically get the selected category

                // Create an Intent to start the ShopActivity
                Intent intent = new Intent(v.getContext(), ShopActivity.class);

                // Pass the selected category to the ShopActivity
                intent.putExtra("selectedCategory", selectedCategory);


                startActivity(intent);

                // Override the transition animation
                overridePendingTransition(0, 0);
            }
        });

        btnMixed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the category associated with the clicked button
                String selectedCategory = "Mixed"; // Change this to dynamically get the selected category

                // Create an Intent to start the ShopActivity
                Intent intent = new Intent(v.getContext(), ShopActivity.class);

                // Pass the selected category to the ShopActivity
                intent.putExtra("selectedCategory", selectedCategory);

                // Start the ShopActivity
                startActivity(intent);

                // Override the transition animation
                overridePendingTransition(0, 0);
            }
        });

        btnSeasonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the category associated with the clicked button
                String selectedCategory = "Seasonal";

                // Create an Intent to start the ShopActivity
                Intent intent = new Intent(v.getContext(), ShopActivity.class);

                // Pass the selected category to the ShopActivity
                intent.putExtra("selectedCategory", selectedCategory);

                // Start the ShopActivity
                startActivity(intent);

                // Override the transition animation
                overridePendingTransition(0, 0);
            }
        });
        btnOrchid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the category associated with the clicked button
                String selectedCategory = "Orchid";

                // Create an Intent to start the ShopActivity
                Intent intent = new Intent(v.getContext(), ShopActivity.class);

                // Pass the selected category to the ShopActivity
                intent.putExtra("selectedCategory", selectedCategory);

                // Start the ShopActivity
                startActivity(intent);

                // Override the transition animation
                overridePendingTransition(0, 0);
            }
        });
        btnLily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the category associated with the clicked button
                String selectedCategory = "Lily";

                // Create an Intent to start the ShopActivity
                Intent intent = new Intent(v.getContext(), ShopActivity.class);

                // Pass the selected category to the ShopActivity
                intent.putExtra("selectedCategory", selectedCategory);

                // Start the ShopActivity
                startActivity(intent);

                // Override the transition animation
                overridePendingTransition(0, 0);
            }
        });
        btnRose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the category associated with the clicked button
                String selectedCategory = "Rose";

                // Create an Intent to start the ShopActivity
                Intent intent = new Intent(v.getContext(), ShopActivity.class);

                // Pass the selected category to the ShopActivity
                intent.putExtra("selectedCategory", selectedCategory);

                // Start the ShopActivity
                startActivity(intent);

                // Override the transition animation
                overridePendingTransition(0, 0);
            }
        });

    }
}