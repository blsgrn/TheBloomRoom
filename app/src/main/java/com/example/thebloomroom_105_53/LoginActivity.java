package com.example.thebloomroom_105_53;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewError;
    private DBHelper dbHelper;
    private Button buttonSignup;

    //bottom navigation
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonSignup = findViewById(R.id.buttonSignup);
        textViewError = findViewById(R.id.textViewError);

        //bottom navigation
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.login);


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                //Simple validation
                //Checking if the user has entered values for all the input fields
                if (username.isEmpty() || password.isEmpty()) {
                    textViewError.setText("Please fill in all fields.");
                } else if (checkUserCredentials(username, password)) { //Checking if the user entered values are matching with the data in the database
                    if (username.equals("admin")) { //Checking whether the username is admin -- if the username is admin, that user is an admin user
                        // Redirect to AdminPanelActivity -- only admin user will get to access the AdminPanel
                        Intent intent = new Intent(LoginActivity.this, AdminPanelActivity.class);
                        startActivity(intent);
                    } else {
                        // Redirect to DashboardActivity -- for all the non-admin users (regular users)
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        intent.putExtra("USERNAME", username);
                        startActivity(intent);
                    }
                    finish(); // Finish the LoginActivity to prevent returning to it
                } else { //If the login credentials are wrong, an error message will show
                    textViewError.setText("Login failed. Invalid credentials.");
                }
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SignUpActivity.class);
                // Start the SignupActivity
                startActivity(intent);
            }
        });

        //bottom navigation
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

    private boolean checkUserCredentials(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();//Open the database to read the data
        String selection = "username = ? AND password = ?"; //Creating a template to check the user entered username and password, "?" will be replaced with the user entered values
        String[] selectionArgs = {username, password}; //Parsing the actual values to the template
        //Using a cursor to screen the database, to check whether the user entered values are present in the database or not
        Cursor cursor = db.query("users", null, selection, selectionArgs, null, null, null);
        //if there is a record(username and password) in the database, similar to user entered username and password, count will be 1
        boolean credentialsMatch = cursor.getCount() > 0;
        cursor.close();
        dbHelper.close();
        return credentialsMatch; //will return 1 if there was a record
    }

}