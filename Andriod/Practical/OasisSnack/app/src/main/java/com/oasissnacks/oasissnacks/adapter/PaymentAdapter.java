package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.acitivity.BillingActivity;
import com.oasissnacks.oasissnacks.acitivity.PaymentActivity;
import com.oasissnacks.oasissnacks.databinding.RowCarddetailsBinding;



import java.util.ArrayList;

public class PaymentAdapter extends RecyclerView.Adapter<PaymentAdapter.ViewHolder>{


    Context context;
    public ArrayList<String> listBank, listNumber;
    public ArrayList<Integer> listLogo;
    private int row_index;

    public PaymentAdapter(Context context, ArrayList<String> listBank, ArrayList<String> listNumber, ArrayList<Integer> listLogo) {
        this.context=context;
        this.listBank=listBank;
        this.listNumber=listNumber;
        this.listLogo=listLogo;
    }

    @NonNull
    @Override
    public PaymentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_carddetails, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdapter.ViewHolder holder, int position) {
        holder.binding.tvBankName.setText(listBank.get(position));
        holder.binding.tvCardNumber.setText(listNumber.get(position));
        holder.binding.bankLogo.setImageResource(listLogo.get(position));

        holder.binding.llBankDetails.setOnClickListener(view -> {

            if (holder.binding.rbBankDetails.isChecked()) {
                holder.binding.rbBankDetails.setChecked(false);
                holder.binding.etCVV.clearFocus();
            } else {
                holder.binding.rbBankDetails.setChecked(true);
            }
            row_index = position;
            notifyDataSetChanged();

        });

        if (position == row_index) {
            //holder.binding.btnEdit.setVisibility(View.VISIBLE);
        } else {
            holder.binding.rbBankDetails.setChecked(false);
            //holder.binding.btnEdit.setVisibility(View.INVISIBLE);
        }

        /*holder.binding.btnEdit.setOnClickListener(view ->{
            Bundle bundle=new Bundle();
            bundle.putString("UserName",holder.binding.tvUserName.getText().toString());
            bundle.putString("UserAddress",holder.binding.tvUserAddress.getText().toString());
            bundle.putString("UserNumber",holder.binding.tvUserNumber.getText().toString());
            Intent intent=new Intent(context, BillingActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });

         */

    }

    @Override
    public int getItemCount() {
        return listBank.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public RowCarddetailsBinding binding;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }

    }
}
