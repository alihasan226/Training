package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.acitivity.ProductDetailsActivity;
import com.oasissnacks.oasissnacks.network.Response.product.Products;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;

import java.util.ArrayList;

public class ViewMoreAdapter extends RecyclerView.Adapter<ViewMoreAdapter.ViewHolder>  {

    @NonNull
    ArrayList<Products> data;
    Context context;

    public ViewMoreAdapter(Context context, ArrayList<Products> data) {
        this.data=data;
        this.context=context;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.layout_viewmore_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(data.get(position).getName());
        holder.textPrice.setText("$"+data.get(position).getPrice()+"");
        holder.textperprice.setText("$"+data.get(position).getPrice_per_packet()+"/count");
        Glide.with(context).load(data.get(position).getImage())
                .into(holder.imageList);



        AppUser appUser= LocalRepositories.getAppUser(context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appUser.product_id=data.get(position).getId();
                LocalRepositories.saveAppUser(context.getApplicationContext(),appUser);
                context.startActivity(new Intent(context, ProductDetailsActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textPrice,textperprice;
        ImageView imageList;

        public ViewHolder(View view)
        {

            super(view);
            textView=view.findViewById(R.id.textview);
            textPrice=view.findViewById(R.id.textprice);
            textperprice=view.findViewById(R.id.textperprice);
            imageList=(ImageView) view.findViewById(R.id.image_view);

        }

    }
}
