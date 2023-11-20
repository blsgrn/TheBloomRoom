package com.example.thebloomroom_105_53;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class AccountListActivity extends AppCompatActivity {
    private RecyclerView recyclerViewAccounts;
    private UserAccountAdapter userAccountAdapter;
    private List<UserAccount> userAccountList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_list);

        recyclerViewAccounts = findViewById(R.id.recyclerViewAccounts);
        userAccountList = new ArrayList<>();

        fetchUserAccountsFromDatabase();

        // Set up RecyclerView
        userAccountAdapter = new UserAccountAdapter(this, userAccountList);
        recyclerViewAccounts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAccounts.setAdapter(userAccountAdapter);

    }
    private void fetchUserAccountsFromDatabase() {
        DBHelper dbHelper = new DBHelper(this);
        userAccountList = dbHelper.getAllUsers();
    }

}