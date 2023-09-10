package com.nilesh.stocksman.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nilesh.stocksman.R;
import com.nilesh.stocksman.models.GlobalData;

import java.util.List;

public class GlobalAdapter extends RecyclerView.Adapter<GlobalAdapter.ViewHolder> {

    private List<GlobalData> globalList;

    public GlobalAdapter(List<GlobalData> globalList) {
        this.globalList = globalList;
    }




    @NonNull
    @Override
    public GlobalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.global_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GlobalAdapter.ViewHolder holder, int position) {

//        TextView market_type, region, local_open, local_close, current_status;

        GlobalData globalitem = globalList.get(position);
        holder.market_type.setText(globalitem.getMarket_type());
        holder.region.setText(globalitem.getRegion());
        holder.local_open.setText(globalitem.getLocal_open());
        holder.local_close.setText(globalitem.getLocal_close());
        holder.current_status.setText(globalitem.getCurrent_status());


    }



    @Override
    public int getItemCount() {
        return globalList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView market_type, region, local_open, local_close, current_status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            market_type =itemView.findViewById(R.id.market_type);
            region =itemView.findViewById(R.id.region);
            local_open =itemView.findViewById(R.id.local_open);
            local_close =itemView.findViewById(R.id.local_close);
            current_status =itemView.findViewById(R.id.current_status);

        }
    }
}
