package com.example.thebloomroom_105_53;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.shop) {
                    startActivity(new Intent(getApplicationContext(), ShopActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                } else if(item.getItemId()== R.id.dashboard){
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
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
}