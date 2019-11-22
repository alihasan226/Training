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
import com.oasissnacks.oasissnacks.interfce.OnFilterClickListner;
import com.oasissnacks.oasissnacks.network.Response.product.Products;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.CustomTextView;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    @NonNull
    ArrayList<Products> data;
    Context context;
    public OnFilterClickListner listner;



    public ProductsAdapter(Context context, ArrayList<Products> data,OnFilterClickListner listner) {
        this.data=data;
        this.context=context;
        this.listner=listner;


    }



    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.productdetails,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(data.get(position).getName());
        holder.textPrice.setText("$"+data.get(position).getPrice()+"");
        holder.textperprice.setText("($"+data.get(position).getPrice_per_packet()+"/count)");
        Glide.with(context).load(data.get(position).getImage())
                .into(holder.imageList);



        AppUser appUser= LocalRepositories.getAppUser(context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appUser.product_id=data.get(position).getId();
                LocalRepositories.saveAppUser(context.getApplicationContext(),appUser);
                context.startActivity(new Intent(context,ProductDetailsActivity.class));
            }
        });
        holder.btnAddtoCart.setOnClickListener(view -> {
            listner.onFilterProductClick(view,position);

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
            public TextView btnAddtoCart;
            public ViewHolder(View view)
            {
                super(view);
                textView=view.findViewById(R.id.textview);
                textPrice=view.findViewById(R.id.textprice);
                textperprice=view.findViewById(R.id.textperprice);
                imageList=(ImageView) view.findViewById(R.id.image_view);
                btnAddtoCart=view.findViewById(R.id.btn_addtocart);

            }

    }
}
