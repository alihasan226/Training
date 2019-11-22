package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.databinding.RowMoreInfoBinding;

import java.util.ArrayList;
import java.util.List;

public class AdapterMoreInfo extends RecyclerView.Adapter<AdapterMoreInfo.MoreInfoViewHolder> {
    public List<String> list;
    public Context context;

    public AdapterMoreInfo(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MoreInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_more_info,parent,false);
        return new MoreInfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoreInfoViewHolder holder, int position) {
        holder.binding.tvText.setText(list.get(position).split("=")[1]);
        holder.binding.tvTitie.setText(list.get(position).split("=")[0]);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MoreInfoViewHolder extends RecyclerView.ViewHolder{
        public RowMoreInfoBinding binding;

        public MoreInfoViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }
    }
}
