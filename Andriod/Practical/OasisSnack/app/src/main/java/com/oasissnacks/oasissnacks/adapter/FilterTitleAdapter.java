package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.databinding.RowFilterTitleBinding;
import com.oasissnacks.oasissnacks.interfce.OnFilterClickListner;
import com.oasissnacks.oasissnacks.utils.AppUser;

import java.util.HashMap;
import java.util.List;

public class FilterTitleAdapter extends RecyclerView.Adapter<FilterTitleAdapter.FilterViewHolder> {

    public List<String> list;
    public Context context;
    public String ViewEntitiy;
    public OnFilterClickListner listner;
    public AppUser appUser = new AppUser();
    public int row_index = -1;
    int counter=0;

    public HashMap<String, String> range = new HashMap<>();
    public SparseBooleanArray array = new SparseBooleanArray();

    public FilterTitleAdapter(List<String> list, Context context, String viewEntitiy, OnFilterClickListner listner) {
        this.list = list;
        this.context = context;
        ViewEntitiy = viewEntitiy;
        this.listner = listner;
    }

    @NonNull
    @Override
    public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_filter_title, parent, false);
        return new FilterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterViewHolder holder, int position) {

       if(counter==0){
           holder.binding.rlRoot.setBackgroundResource(R.color.white);
       }else {
           if(position==row_index){
               holder.binding.rlRoot.setBackgroundResource(R.color.white);

           }else {
               holder.binding.rlRoot.setBackgroundResource(R.color.colorTemporary);
           }

       }
        holder.binding.tvRowFilter.setText(list.get(position));
        counter++;





        holder.binding.rlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.onFilterProductClick(view, position);
                row_index = position;
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FilterViewHolder extends RecyclerView.ViewHolder {
        public RowFilterTitleBinding binding;

        public FilterViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
