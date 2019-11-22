package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.acitivity.AllCategoryActivity;
import com.oasissnacks.oasissnacks.acitivity.CategoryActivity;
import com.oasissnacks.oasissnacks.acitivity.LandingPageActivity;
import com.oasissnacks.oasissnacks.acitivity.ProductListActivity;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.Preferences;

import java.util.ArrayList;

public class LandingpageRecyclerAdapter extends RecyclerView.Adapter<LandingpageRecyclerAdapter.ViewHolder>{

    @NonNull
    ArrayList list_image;
    ArrayList<String> list_text;
    Context context;
    AppUser appUser;


    public LandingpageRecyclerAdapter(@NonNull Context context, ArrayList list_image, ArrayList<String> list_text) {
        this.list_image=list_image;
        this.list_text=list_text;
        this.context=context;
        appUser= LocalRepositories.getAppUser(context);

    }

    @NonNull
    @Override
    public LandingpageRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.layout_landing_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LandingpageRecyclerAdapter.ViewHolder holder, int position) {
        holder.imageList.setImageResource((Integer)list_image.get(position));



        String arr[]=list_text.get(position).split(";");
        holder.textList.setText(arr[0]);
        //Perform onclick event on recycler view element
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position==0){
                   context. startActivity(new Intent(context, AllCategoryActivity.class));
                }else {


                /*
                Intent intent=new Intent(context, CategoryActivity.class);
                context.startActivity(intent);
                */
                Bundle bundle=new Bundle();
                bundle.putString("actionbar_name",arr[0]);
                appUser.cat_id=arr[1];
                Preferences.getInstance(context).setCatID(arr[1]);

                LocalRepositories.saveAppUser(context.getApplicationContext(),appUser);
                Intent intent =new Intent(context.getApplicationContext(), ProductListActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return list_text.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textList;
        ImageView imageList;
        public ViewHolder(View view)
        {
            super(view);
            imageList=(ImageView) view.findViewById(R.id.recycler_image);
            textList=(TextView)view.findViewById(R.id.recycler_text);
        }

    }

}
