package com.example.thebloomroom_105_53;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CategoriesActivity extends AppCompatActivity {

    Button btnSpecial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        btnSpecial = findViewById(R.id.btnSpecial);

        btnSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the category associated with the clicked button
                String selectedCategory = "Special"; // Change this to dynamically get the selected category

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