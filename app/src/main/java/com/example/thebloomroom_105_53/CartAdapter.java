package com.example.thebloomroom_105_53;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<CartItem> cartItems;
    private LayoutInflater inflater;

    public CartAdapter(Context context, List<CartItem> cartItems) {
        this.inflater = LayoutInflater.from(context);
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Bind data to views in your item layout
        CartItem currentItem = cartItems.get(position);
        holder.itemNameTextView.setText(currentItem.getName());
//        holder.itemDescriptionTextView.setText(currentItem.getDescription());
        holder.itemPriceTextView.setText(String.valueOf(currentItem.getPrice()));
        holder.itemCategoryTextView.setText(currentItem.getCategory());

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemNameTextView;
        TextView itemDescriptionTextView;
        TextView itemPriceTextView;
        TextView itemCategoryTextView;
        // Add more views as needed

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            itemDescriptionTextView = itemView.findViewById(R.id.itemDescriptionTextView);
            itemPriceTextView = itemView.findViewById(R.id.itemPriceTextView);
            itemCategoryTextView = itemView.findViewById(R.id.itemCategoryTextView);
            // Initialize other views as needed
        }
    }
}
