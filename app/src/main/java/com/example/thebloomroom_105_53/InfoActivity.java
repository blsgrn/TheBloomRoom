package com.example.thebloomroom_105_53;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class InfoActivity extends AppCompatActivity {
    ImageButton btnImgEmail, btnImgPhone, btnFacebook, btnMap;
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        btnImgEmail = findViewById(R.id.imageButtonEmail);
        btnImgPhone = findViewById(R.id.imageButtonPhone);
        btnFacebook = findViewById(R.id.imageButtonFacebook);
        btnMap = findViewById(R.id.imageButtonMap);

        btnImgEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"hdcse10553@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Customer Inquiry");
                startActivity(emailIntent);
            }
        });
        btnImgPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:" + "0757952581"));
                startActivity(phoneIntent);
            }

        });
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String facebookUrl = "https://www.facebook.com/ICBTsrilanka";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
                startActivity(intent);
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coordinates = "https://goo.gl/maps/ywAqgBhQovC92Su99";

                Intent mapIntent = new Intent(Intent.ACTION_VIEW);

                mapIntent.setData(Uri.parse(coordinates));

                startActivity(mapIntent);

            }
        });

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.info);
        // In any other activity where you want to access the username
        SharedPreferences preferences = getSharedPreferences("user_credentials", MODE_PRIVATE);
        String username = preferences.getString("username", "Please Login!");

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.shop) {
                    startActivity(new Intent(getApplicationContext(), ShopActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                } else if(item.getItemId()== R.id.dashboard){
                    if (!"Please Login!".equals(username)){
                        startActivity(new Intent(getApplicationContext(), DashboardActivity.class));
                        overridePendingTransition(0,0);
                    } else {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        overridePendingTransition(0,0);
                    }
                    return true;
                } else if(item.getItemId()==R.id.home){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                }

                return true;
            }
        });
    }
}