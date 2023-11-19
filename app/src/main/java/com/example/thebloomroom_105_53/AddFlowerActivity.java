package com.example.thebloomroom_105_53;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddFlowerActivity extends AppCompatActivity {
    private DBHelper dbHelper;

    private EditText editTextName, editTextDescription, editTextPrice, editTextImageFilename;
    private Spinner spinnerCategory;
    private TextView textViewError;
    private Button buttonAddFlower;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flower);

        dbHelper = new DBHelper(this);

        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextPrice = findViewById(R.id.editTextPrice);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        editTextImageFilename = findViewById(R.id.editTextImageFilename);
        buttonAddFlower = findViewById(R.id.btnAddFlower);
        textViewError = findViewById(R.id.textViewError);


        // Define the flower categories for spinner layout
        List<String> categories = new ArrayList<>();
        categories.add("Rose");
        categories.add("Lily");
        categories.add("Orchid");
        categories.add("Seasonal");
        categories.add("Mixed");
        categories.add("Special");

        // Create an ArrayAdapter using the array for the spinner (drop down)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCategory.setAdapter(adapter);

        buttonAddFlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flowerName = editTextName.getText().toString();
                String description = editTextDescription.getText().toString();
                String priceText = editTextPrice.getText().toString();
                String category = spinnerCategory.getSelectedItem().toString();
                String imageFilename = editTextImageFilename.getText().toString().trim();

                int maxDescriptionLength = 200;

                if (flowerName.isEmpty() || description.isEmpty() || priceText.isEmpty() || category.isEmpty() || imageFilename.isEmpty()) {
                    textViewError.setText("Please fill in all fields.");
                } else if (description.length() > maxDescriptionLength) {
                    textViewError.setText("Description is too long.");
                } else {
                    try {

                        // Open the database for writing
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        // Parse the price after checking for empty
                        double price = Double.parseDouble(priceText);
                        // Prepare user data for insertion
                        ContentValues values2 = new ContentValues();
                        values2.put("name", flowerName);
                        values2.put("description", description);
                        values2.put("price", price);
                        values2.put("category", category);
                        values2.put("image_filename", imageFilename);


                        // Insert user data into the database
                        long newRowId = db.insert("flowers", null, values2);

                        // Close the database
                        dbHelper.close();

                        if (newRowId != -1) {
                            Toast.makeText(AddFlowerActivity.this, "New flower added successfully!", Toast.LENGTH_SHORT).show();
                            finish(); // Finish the activity and return to the previous screen
                        } else {
                            Log.e("AddFlowerActivity", "Error inserting data into the database. newRowId: " + newRowId);
                            textViewError.setText("Error! Check logs for details.");
                        }
                    } catch (Exception e) {
                        Log.e("AddFlowerActivity", "Exception during database operation", e);
                        textViewError.setText("Error! Check logs for details.");
                    }
                }
            }
        });



    }

}