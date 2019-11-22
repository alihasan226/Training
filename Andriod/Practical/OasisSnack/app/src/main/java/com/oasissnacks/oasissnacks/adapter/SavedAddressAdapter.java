package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.acitivity.BillingActivity;
import com.oasissnacks.oasissnacks.acitivity.SavedAddressActivity;
import com.oasissnacks.oasissnacks.databinding.RowSavedaddressBinding;

import java.util.ArrayList;

public class SavedAddressAdapter extends RecyclerView.Adapter<SavedAddressAdapter.ViewHolder> {

    Context context;
    public ArrayList<String> listName, listAddress, listNumber;
    public int row_index ;


    public SavedAddressAdapter(Context context, ArrayList<String> listName, ArrayList<String> listAddress, ArrayList<String> listNumber) {
        this.context = context;
        this.listName = listName;
        this.listAddress = listAddress;
        this.listNumber = listNumber;
    }

    @NonNull
    @Override
    public SavedAddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_savedaddress, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedAddressAdapter.ViewHolder holder, int position) {
        holder.binding.tvUserName.setText(listName.get(position));
        holder.binding.tvUserAddress.setText(listAddress.get(position));
        holder.binding.tvUserNumber.setText(listNumber.get(position));


        holder.binding.llSavedAddress.setOnClickListener(view -> {

            if (holder.binding.rbSavedAddress.isChecked()) {
                holder.binding.rbSavedAddress.setChecked(false);
            } else {
                holder.binding.rbSavedAddress.setChecked(true);
            }
            row_index = position;
            notifyDataSetChanged();

        });

        if (position == row_index) {
            holder.binding.rbSavedAddress.setChecked(true);
            holder.binding.btnEdit.setVisibility(View.VISIBLE);
        } else {
            holder.binding.rbSavedAddress.setChecked(false);
            holder.binding.btnEdit.setVisibility(View.INVISIBLE);
        }
        holder.binding.btnEdit.setOnClickListener(view ->{
            Bundle bundle=new Bundle();
            bundle.putString("UserName",holder.binding.tvUserName.getText().toString());
            bundle.putString("UserAddress",holder.binding.tvUserAddress.getText().toString());
            bundle.putString("UserNumber",holder.binding.tvUserNumber.getText().toString());
            Intent intent=new Intent(context,BillingActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listName.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public RowSavedaddressBinding binding;

        public ViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }

    }


}
