package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.acitivity.ProductDetailsActivity;
import com.oasissnacks.oasissnacks.interfce.onProuctClickListner;
import com.oasissnacks.oasissnacks.utils.ButtonAnimation;
import com.oasissnacks.oasissnacks.utils.CustomTextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    @NonNull
    ArrayList data,price,list_image;
    onProuctClickListner listner;
    Context context;
    ArrayList<Integer> positionList=new ArrayList<>();
    int counter=0;


    public CustomAdapter(Context context,ArrayList data,ArrayList price, ArrayList list_image, onProuctClickListner listner)
    {
        this.data=data;
        this.price=price;
        this.context=context;

        this.list_image=list_image;
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
        holder.textView.setText((CharSequence) data.get(position));
        holder.textPrice.setText((CharSequence) price.get(position));
        holder.imageList.setImageResource((Integer) list_image.get(position));
        holder.btnAddtoCart.setOnClickListener(view -> {
            holder. btnAddtoCart.startAnimation(ButtonAnimation.createButtonAnimation());
            counter++;
            positionList.add(position);
            listner.onProductClick(view,positionList,counter);

        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,ProductDetailsActivity.class));
            }
        });


    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


            TextView textView;
            TextView textPrice;
            ImageView imageList;
            public CustomTextView btnAddtoCart;
            public ViewHolder(View view)
            {
                super(view);
                textView=view.findViewById(R.id.textview);
                textPrice=view.findViewById(R.id.textprice);
                imageList=(ImageView) view.findViewById(R.id.image_view);
                btnAddtoCart=view.findViewById(R.id.btn_addtocart);

            }

    }
}
