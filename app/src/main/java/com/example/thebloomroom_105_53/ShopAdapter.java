package com.example.thebloomroom_105_53;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.FlowerViewHolder> {

    private List<Flower> flowerList;
    private Context context;

    public ShopAdapter(List<Flower> flowerList, Context context) {
        this.flowerList = flowerList;
        this.context = context;
    }

    @NonNull
    @Override
    public FlowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);
        return new FlowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlowerViewHolder holder, int position) {
        Flower flower = flowerList.get(position);

        holder.textViewFlowerId.setText("Flower ID: " + String.valueOf(flower.getId()));
        holder.textViewFlowerName.setText("Flower Name: " + flower.getName());
        holder.textViewFlowerDescription.setText("Description: " + flower.getDescription());
        holder.textViewFlowerPrice.setText(String.format("Price: Rs %.2f", flower.getPrice()));
        holder.textViewFlowerCategory.setText("Category: " + flower.getCategory());
        holder.textViewFlowerImageFilename.setText("Image Filename: " + flower.getImageFilename());

        final int currentPosition = position;

        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(view.getContext());
                Flower selectedFlower = flowerList.get(currentPosition);

                // Call the method to add the selected flower to the cart
                long newRowId = dbHelper.addToCart(
                        selectedFlower.getId(),
                        selectedFlower.getName(),
                        selectedFlower.getPrice(),
                        selectedFlower.getCategory()
                );

                // Check if insertion was successful
                if (newRowId != -1) {
                    // Provide feedback to the user (optional)
                    Toast.makeText(context, "Flower added to cart", Toast.LENGTH_SHORT).show();
                } else {
                    // Provide feedback to the user (optional)
                    Toast.makeText(context, "Failed to add flower to cart", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return flowerList.size();
    }

    public class FlowerViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFlowerId, textViewFlowerName, textViewFlowerDescription, textViewFlowerPrice, textViewFlowerCategory, textViewFlowerImageFilename;
        ImageButton btnDeleteFlower, btnAddToCart;

        public FlowerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFlowerId = itemView.findViewById(R.id.textViewFlowerId);
            textViewFlowerName = itemView.findViewById(R.id.textViewFlowerName);
            textViewFlowerDescription = itemView.findViewById(R.id.textViewFlowerDescription);
            textViewFlowerPrice = itemView.findViewById(R.id.textViewFlowerPrice);
            textViewFlowerCategory = itemView.findViewById(R.id.textViewFlowerCategory);
            textViewFlowerImageFilename = itemView.findViewById(R.id.textViewImageFilename);

            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }
}
