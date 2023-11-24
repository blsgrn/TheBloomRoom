package com.example.thebloomroom_105_53;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnItemRemovedListener {
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;

    private TextView textViewTotal;

    Button buttonProcessPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        textViewTotal = findViewById(R.id.textViewTotal);

        buttonProcessPay = findViewById(R.id.buttonProcessPay);

        // Initialize adapter and set it to the RecyclerView
        List<CartItem> cartItems = getCartItemsFromDatabase();
        double initialTotal = calculateUpdatedTotal();
        textViewTotal.setText("Total (Rs): " + String.valueOf(initialTotal));

        cartAdapter = new CartAdapter(this, cartItems, this);
        recyclerView.setAdapter(cartAdapter);

        calculateUpdatedTotal();

        buttonProcessPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double totalAmount = calculateUpdatedTotal();
                Intent intent = new Intent(v.getContext(), PaymentActivity.class);
                intent.putExtra("TOTAL_AMOUNT", totalAmount);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }
    @Override
    public void onItemRemoved(double removedItemPrice) {
        // Update the total in activity when an item is removed
        double updatedTotal = calculateUpdatedTotal();
        textViewTotal.setText("Total (Rs): " + String.valueOf(updatedTotal));
    }


    // Retrieve cart items from the database
    private List<CartItem> getCartItemsFromDatabase() {
        DBHelper dbHelper = new DBHelper(this);
        return dbHelper.getAllCartItems();
    }

    // Method to calculate updated total
    private double calculateUpdatedTotal() {
        List<CartItem> updatedCartItems = getCartItemsFromDatabase();
        double updatedTotal = 0;
        for (CartItem item : updatedCartItems) {
            updatedTotal += item.getPrice();
        }
        return updatedTotal;
    }


}
