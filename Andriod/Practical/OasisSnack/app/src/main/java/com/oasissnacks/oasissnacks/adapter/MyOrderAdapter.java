package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.acitivity.MyOrdersActivity;
import com.oasissnacks.oasissnacks.databinding.RowMyOrdersBinding;

import java.util.ArrayList;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.MyOrdersViewholder> {

    Context context;
    ArrayList<String> listName,listPrice,listOrderID;
    ArrayList<Integer> listImage;

    public MyOrderAdapter(Context context, ArrayList<String> listOrderID, ArrayList<String> listName, ArrayList<String> listPrice, ArrayList<Integer> listImage) {
        this.context=context;
        this.listImage=listImage;
        this.listName=listName;
        this.listOrderID=listOrderID;
        this.listPrice=listPrice;

    }


    @NonNull
    @Override
    public MyOrdersViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_my_orders,parent,false);
        return new MyOrdersViewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyOrdersViewholder holder, int position) {
        holder.binding.tvOrderId.setText(listOrderID.get(position));
        holder.binding.productName.setText(listName.get(position));
        holder.binding.ivProductImage.setImageResource(listImage.get(position));
        holder.binding.tvPrice.setText(listPrice.get(position));

    }

    @Override
    public int getItemCount() {
        return listOrderID.size();
    }

    public class MyOrdersViewholder extends RecyclerView.ViewHolder{
        public RowMyOrdersBinding binding;

        public MyOrdersViewholder(@NonNull View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }
    }
}
