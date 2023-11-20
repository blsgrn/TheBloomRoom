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

public class UserAccountAdapter extends RecyclerView.Adapter<UserAccountAdapter.ViewHolder> {

    private List<UserAccount> userAccounts;
    private LayoutInflater inflater;




    public UserAccountAdapter(Context context, List<UserAccount> userAccounts) {
        this.inflater = LayoutInflater.from(context);
        this.userAccounts = userAccounts;
    }

    @NonNull
    @Override
    public UserAccountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_account, parent, false);
        return new UserAccountAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAccountAdapter.ViewHolder holder, int position) {
        // Bind data to views in your item layout
        UserAccount currentItem = userAccounts.get(position);
        holder.accountUsernameTextView.setText("Username: " + currentItem.getUsername());
        holder.accountUserId.setText("Account ID: " + currentItem.getUserId());
        holder.accountEmail.setText(String.valueOf("Email: : "+ currentItem.getEmail()));

        final int currentPosition = position;



    }




    @Override
    public int getItemCount() {
        return userAccounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView accountUsernameTextView;
        TextView accountUserId;
        TextView accountEmail;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            accountUsernameTextView = itemView.findViewById(R.id.textViewUsername);
            accountUserId = itemView.findViewById(R.id.textViewUserId);
            accountEmail = itemView.findViewById(R.id.textViewEmail);

        }
    }


}
