package com.example.thebloomroom_105_53;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerViewCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize your adapter and set it to the RecyclerView
        List<CartItem> cartItems = getCartItemsFromDatabase();
        cartAdapter = new CartAdapter(this, cartItems);
        recyclerView.setAdapter(cartAdapter);
    }

    // Retrieve cart items from the database
    private List<CartItem> getCartItemsFromDatabase() {
        DBHelper dbHelper = new DBHelper(this);
        return dbHelper.getAllCartItems();
    }
}
