package com.example.thebloomroom_105_53;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<CartItem> cartItems;
    private LayoutInflater inflater;

    private OnItemRemovedListener onItemRemovedListener;

    private double removedItemPrice = 0;



    public CartAdapter(Context context, List<CartItem> cartItems, OnItemRemovedListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.cartItems = cartItems;
        this.onItemRemovedListener = listener;
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
        holder.itemNameTextView.setText("Flower Name: " + currentItem.getName());
        holder.itemIdTextView.setText("Flower ID: " + currentItem.getItemId());
        holder.itemPriceTextView.setText(String.valueOf("Price: "+ currentItem.getPrice()));
        holder.itemCategoryTextView.setText("Category: "+ currentItem.getCategory());

        final int currentPosition = position;
        holder.btnRemoveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(view.getContext());
                dbHelper.deleteCartItem(currentItem.getItemId());

                // Notify the listener that an item has been removed
                onItemRemovedListener.onItemRemoved(currentItem.getPrice());
                // Handle delete button click
                cartItems.remove(currentPosition);
                notifyItemRemoved(currentPosition);
            }
        });



    }
    public double getRemovedItemPrice() {
        return removedItemPrice;
    }
    public interface OnItemRemovedListener {
        void onItemRemoved(double removedItemPrice);
    }



    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemNameTextView;
        TextView itemIdTextView;
        TextView itemPriceTextView;
        TextView itemCategoryTextView;

        ImageButton btnRemoveItem;
        // Add more views as needed

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            itemIdTextView = itemView.findViewById(R.id.itemIdTextView);
            itemPriceTextView = itemView.findViewById(R.id.itemPriceTextView);
            itemCategoryTextView = itemView.findViewById(R.id.itemCategoryTextView);
            btnRemoveItem = itemView.findViewById(R.id.btnRemoveItem);
            // Initialize other views as needed
        }
    }



}
