package com.example.thebloomroom_105_53;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class PaymentRecordsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPaymentRecords;
    private PaymentRecordAdapter paymentRecordAdapter;
    private List<PaymentRecord> paymentRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_records);

        recyclerViewPaymentRecords = findViewById(R.id.recyclerViewPaymentRecords);
        paymentRecords = new ArrayList<>();

        fetchRecordsFromDatabase();

        // Set up RecyclerView
        paymentRecordAdapter = new PaymentRecordAdapter(paymentRecords, this);
        recyclerViewPaymentRecords.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPaymentRecords.setAdapter(paymentRecordAdapter);

    }
    private void fetchRecordsFromDatabase() {
        DBHelper dbHelper = new DBHelper(this);
        paymentRecords.addAll(dbHelper.getAllPaymentRecords());
    }

}