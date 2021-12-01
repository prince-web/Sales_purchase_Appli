package com.example.salespurchaseappli;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScreenItemAdapter extends RecyclerView.Adapter<ScreenItemAdapter.ViewHolder> {

    ArrayList<Model> list;
    Context context;

    public ScreenItemAdapter(ArrayList<Model> list,Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_obj,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(list.get(position).getDate());
        holder.time.setText(list.get(position).getTime());
        holder.item.setText(list.get(position).getName());
        holder.quantity.setText(list.get(position).getQuantity());
        holder.purchaseAmount.setText(list.get(position).getTotalPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView date,time,item,quantity,purchaseAmount,sellAmount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date_tv);
            time = itemView.findViewById(R.id.time_tv);
            item = itemView.findViewById(R.id.item_tv);
            quantity = itemView.findViewById(R.id.quantity_tv);
            purchaseAmount = itemView.findViewById(R.id.purchase_tv);
            sellAmount = itemView.findViewById(R.id.sellItem_tv);
        }
    }
}
