package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.databinding.RowEstimateShippingBinding;
import com.oasissnacks.oasissnacks.interfce.OnFilterClickListner;
import com.oasissnacks.oasissnacks.network.Response.shiipingresponse.RateResponse;

import java.util.List;

public class ShippingMethodAdapter extends RecyclerView.Adapter<ShippingMethodAdapter.ShippingViewHolder> {
    public List<RateResponse> list;
    public Context context;
    public OnFilterClickListner listner;
    int row_index=-1;

    public ShippingMethodAdapter(List<RateResponse> list, Context context, OnFilterClickListner listner) {
        this.list = list;
        this.context = context;
        this.listner = listner;
    }

    @NonNull
    @Override
    public ShippingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_estimate_shipping,parent,false);
        return new ShippingViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ShippingViewHolder holder, int position) {
        holder.binding.tvTitle.setText(list.get(position).getTitle());
        holder.binding.tvCarrierTitle.setText(list.get(position).getCarrierTitle());
        holder.binding.tvPrice.setText("$"+list.get(position).getPrice());
        if (position==row_index){
            holder.binding.rb.setChecked(true);
        }else {
            holder.binding.rb.setChecked(false);
        }
        holder.binding.llRoot.setOnClickListener(view -> {
            row_index=position;
            if (holder.binding.rb.isChecked()){
                holder.binding.rb.setChecked(false);
            }else {
                listner.onFilterProductClick(view,position);
                holder.binding.rb.setChecked(true);
            }
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ShippingViewHolder extends RecyclerView.ViewHolder{
        private RowEstimateShippingBinding binding;

        public ShippingViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }
    }
}
