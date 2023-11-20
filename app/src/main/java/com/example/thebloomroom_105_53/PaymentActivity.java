package com.example.thebloomroom_105_53;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {

    TextView textViewCalculatedTotal;

    EditText editTextCustomerName;

    RadioGroup radioGroup;
    RadioButton selectedMethod;

    Button buttonProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        textViewCalculatedTotal = findViewById(R.id.textViewCalculatedTotal);

        editTextCustomerName = findViewById(R.id.editTextCustomerName);
        radioGroup = findViewById(R.id.radioGroup);
        buttonProcess = findViewById(R.id.buttonProcess);

        SharedPreferences preferences = getSharedPreferences("user_credentials", MODE_PRIVATE);
        String username = preferences.getString("username", "Please Login!");

        editTextCustomerName.setText(username);



        // Retrieve total amount from intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("TOTAL_AMOUNT")) {
            double totalAmount = intent.getDoubleExtra("TOTAL_AMOUNT", 0.0); // Use the same key used in CartActivity
            // Now you have the totalAmount, you can use it as needed in PaymentActivity
            textViewCalculatedTotal.setText(String.valueOf(totalAmount));
        }

        buttonProcess.setOnClickListener(view -> {
            // Get the selected radio button's ID
            int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            if (selectedRadioButtonId != -1) {
                // Find the selected radio button
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                // Get the values
                String customerName = editTextCustomerName.getText().toString();
                double totalAmount = Double.parseDouble(textViewCalculatedTotal.getText().toString());
                String paymentMethod = selectedRadioButton.getText().toString();

                // Create a DBHelper instance and insert the payment record
                DBHelper dbHelper = new DBHelper(PaymentActivity.this);
                dbHelper.insertPaymentRecord(customerName, totalAmount, paymentMethod);
                Toast.makeText(PaymentActivity.this, "Process success!", Toast.LENGTH_SHORT).show();

//                 Optionally, you can redirect the user to another activity or perform other actions
                 Intent newIntent = new Intent(PaymentActivity.this, PaymentMessageActivity.class);
                 startActivity(newIntent);
                overridePendingTransition(0, 0);


            }
        });


    }
}