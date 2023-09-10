package com.nilesh.stocksman.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nilesh.stocksman.models.Stocks;
import com.nilesh.stocksman.models.User;

import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder> {
    private List<Stocks> userList;

    public StockAdapter(List<Stocks> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your user item layout and create a ViewHolder
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Stocks user = userList.get(position);
        // Bind user data to the ViewHolder's views
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Declare your ViewHolder views here

        public ViewHolder(View itemView) {
            super(itemView);
            // Initialize your ViewHolder views here
        }
    }
}

