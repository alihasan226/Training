package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.acitivity.LandingPageActivity;
import com.oasissnacks.oasissnacks.acitivity.ProductDetailsActivity;
import com.oasissnacks.oasissnacks.acitivity.ProductListActivity;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.Preferences;

import java.util.ArrayList;

public class CategoryRecyclerviewAdapter extends RecyclerView.Adapter<CategoryRecyclerviewAdapter.ViewHolder> {

    @NonNull
    ArrayList<String> list_text;
    Context context;


    public CategoryRecyclerviewAdapter(Context context,ArrayList<String> list_text) {
      this.context=context;
      this.list_text=list_text;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.layout_category_explist_child_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String arr[]=list_text.get(position).split(";");
        holder.textView.setText(arr[0]);

        AppUser appUser=LocalRepositories.getAppUser(context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                bundle.putString("actionbar_name",arr[0]);
                appUser.cat_id=arr[1];
                Preferences.getInstance(context).setCatID(arr[1]);
                LocalRepositories.saveAppUser(context,appUser);
                Intent intent =new Intent(context.getApplicationContext(),ProductListActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return list_text.size();
    }


    public class  ViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.textview);
        }
    }


}
