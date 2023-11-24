package com.example.thebloomroom_105_53;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    private EditText editTextUsername, editTextPassword, editTextConfirmPw, editTextEmail;

    private TextView textViewError;

    private Button buttonSignup, buttonLogin;
    boolean containsLetter = false;
    boolean containsNumber = false;

BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dbHelper = new DBHelper(this);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPw = findViewById(R.id.editTextConfirmPassword);
        editTextEmail = findViewById(R.id.editTextEmail);
        textViewError = findViewById(R.id.textViewError);

        buttonSignup = findViewById(R.id.buttonSignup);
        buttonLogin = findViewById(R.id.buttonLogin);


        //button logic
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
                {
                    // Get user input
                    String username = editTextUsername.getText().toString();
                    String password = editTextPassword.getText().toString();
                    String confirmPassword = editTextConfirmPw.getText().toString();
                    String email = editTextEmail.getText().toString();

                    // Simple input validation

                    containsLetter = false;
                    containsNumber = false;
                    //Checking if the user has entered values for all the input fields
                    if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty()) {
                        textViewError.setText("Please fill in all fields.");
                    } else if (username.length() < 6 ) {
                        textViewError.setText("Username must be at least 6 characters.");
                    } else if (password.length() < 6 ) {
                        textViewError.setText("Password must be at least 6 characters.");
                    }else if (!containsLetterAndNumber(password)) {
                        textViewError.setText("Password must contain at least one letter and one number.");
                    } else if (!password.equals(confirmPassword)) { //Checking if the user entered values in Password input field & Confirm Password input filed match
                        textViewError.setText("Passwords do not match.");
                    }else if (!isValidEmail(email)) {//Checking if the user has entered a proper email, to check this Email format checking regular expression is used
                        textViewError.setText("Invalid email address.");
                    } else {
                        // Open the database for writing
                        SQLiteDatabase db = dbHelper.getWritableDatabase();

                        // Prepare user data for insertion
                        ContentValues values = new ContentValues();
                        values.put("username", username);
                        values.put("password", password); // You should hash the password in practice
                        values.put("email", email);

                        // Insert user data into the database
                        long newRowId = db.insert("users", null, values);

//                         Close the database
                        dbHelper.close();


                        if (newRowId != -1) {
                            Toast.makeText(SignUpActivity.this, "Sign up successful!", Toast.LENGTH_SHORT).show();
                            finish(); // Finish the activity and return to the previous screen
                        } else {
                            textViewError.setText("Sign up failed.");
                        }
                    }
                }

        });

        // Function to validate email format using a regular expression


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                // Start the SignupActivity
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });


        //bottom navigation

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);

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


    private boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+$";
        return Pattern.matches(emailPattern, email);
    }

    //function to check if password contains a letter and a number

    private boolean containsLetterAndNumber(String password) {
         containsLetter = false;
        containsNumber = false;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                containsLetter = true;
            } else if (Character.isDigit(c)) {
                containsNumber = true;
            }

            // Break the loop if both conditions are met
            if (containsLetter && containsNumber) {
                break;
            }
        }

        return containsLetter && containsNumber;
    }
}