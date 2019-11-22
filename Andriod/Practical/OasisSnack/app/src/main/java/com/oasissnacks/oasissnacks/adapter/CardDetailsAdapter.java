package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.databinding.RowNewcardBinding;
import com.oasissnacks.oasissnacks.interfce.OnCardClickListener;
import com.oasissnacks.oasissnacks.interfce.OnPaymentClickListener;

import java.util.ArrayList;


public class CardDetailsAdapter extends RecyclerView.Adapter<CardDetailsAdapter.ViewHolder>{

    Context context;
    ArrayList<String> listCard;
    private int row_index;
    private OnCardClickListener listener;

    public CardDetailsAdapter(Context context, ArrayList<String> listCard, OnCardClickListener listener) {
        this.context=context;
        this.listCard=listCard;
        this.listener=listener;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view1=inflater.inflate(R.layout.row_newcard,parent,false);
        return new ViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.tvCardName.setText(listCard.get(position));

        holder.binding.rlCard.setOnClickListener(view -> {

            if (holder.binding.rbCard.isChecked()) {
                holder.binding.rbCard.setChecked(false);
            } else {
                    holder.binding.rbCard.setChecked(true);
                    listener.onCardClick(view,position,"CardClick");
            }
            row_index = position;
            notifyDataSetChanged();

        });

        if (position == row_index) {
        } else {
            holder.binding.rbCard.setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return listCard.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{

        public RowNewcardBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }

    }
}
