package com.example.thebloomroom_105_53;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnItemRemovedListener {
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;

    private TextView textViewTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        textViewTotal = findViewById(R.id.textViewTotal);

        // Initialize your adapter and set it to the RecyclerView
        List<CartItem> cartItems = getCartItemsFromDatabase();
        double initialTotal = calculateUpdatedTotal();
        textViewTotal.setText("Total (Rs): " + String.valueOf(initialTotal));

        cartAdapter = new CartAdapter(this, cartItems, this);
        recyclerView.setAdapter(cartAdapter);

        calculateUpdatedTotal();
    }
    @Override
    public void onItemRemoved(double removedItemPrice) {
        // Update the total in your activity when an item is removed
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
