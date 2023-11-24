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

public class FlowerListAdapter extends RecyclerView.Adapter<FlowerListAdapter.FlowerViewHolder> {

    private List<Flower> flowerList;
    private Context context;

    public FlowerListAdapter(List<Flower> flowerList, Context context) {
        this.flowerList = flowerList;
        this.context = context;
    }

    @NonNull
    @Override
    public FlowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flower, parent, false);
        return new FlowerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull FlowerViewHolder holder, int position) {
        Flower flower = flowerList.get(position);

        holder.textViewFlowerId.setText("Flower ID: " + String.valueOf(flower.getId()));
        holder.textViewFlowerName.setText("Flower Name: " + flower.getName());
        holder.textViewFlowerPrice.setText(String.format("Price: Rs %.2f", flower.getPrice()));
        holder.textViewFlowerCategory.setText("Category: " + flower.getCategory());
        holder.textViewFlowerImageFilename.setText("Image Filename: " + flower.getImageFilename());

        final int currentPosition = position;
        holder.btnDeleteFlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(view.getContext());
                dbHelper.deleteFlower(flower.getId());
                // Handle delete button click
                flowerList.remove(currentPosition);
                notifyItemRemoved(currentPosition);
            }
        });


    }




    @Override
    public int getItemCount() {
        return flowerList.size();
    }

    public class FlowerViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFlowerId, textViewFlowerName, textViewFlowerDescription, textViewFlowerPrice, textViewFlowerCategory, textViewFlowerImageFilename;
        ImageButton btnDeleteFlower;
        public FlowerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewFlowerId = itemView.findViewById(R.id.textViewFlowerId);
            textViewFlowerName = itemView.findViewById(R.id.textViewFlowerName);
//            textViewFlowerDescription = itemView.findViewById(R.id.textViewFlowerDescription);
            textViewFlowerPrice = itemView.findViewById(R.id.textViewFlowerPrice);
            textViewFlowerCategory = itemView.findViewById(R.id.textViewFlowerCategory);
            textViewFlowerImageFilename = itemView.findViewById(R.id.textViewImageFilename);
            btnDeleteFlower = itemView.findViewById(R.id.btnDeleteFlower);

        }
    }

}

