package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.interfce.OnFilterClickListner;
import com.oasissnacks.oasissnacks.network.Response.product.RelatedProducts;

import java.util.ArrayList;

public class RelatedProductAdapter extends RecyclerView.Adapter<RelatedProductAdapter.ViewHolder> {


    Context context;
    ArrayList<RelatedProducts> data;
    public OnFilterClickListner listner;


    public RelatedProductAdapter(Context context, ArrayList<RelatedProducts> data, OnFilterClickListner listner) {
        this.context = context;
        this.data = data;
        this.listner = listner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.layout_productdetails_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelatedProductAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(data.get(position).getImage())
                .into(holder.imageList);
        holder.textList1.setText(data.get(position).getName());
        holder.textList2.setText("$"+data.get(position).getPrice());
        holder.llRoot.setOnClickListener(view -> {
            listner.onFilterProductClick(view,position);
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textList1,textList2;
        ImageView imageList;
        LinearLayout llRoot;
        public ViewHolder(View view)
        {
            super(view);
            llRoot=view.findViewById(R.id.llRoot);
            imageList=(ImageView) view.findViewById(R.id.productdetails_recyclerimage);
            textList1=(TextView)view.findViewById(R.id.productdetails_recyclername);
            textList2=(TextView)view.findViewById(R.id.productdetails_recyclerprice);
        }

    }
}
