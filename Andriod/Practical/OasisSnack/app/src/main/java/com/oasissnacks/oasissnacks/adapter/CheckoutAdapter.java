package com.oasissnacks.oasissnacks.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;
import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.acitivity.CartActivity;
import com.oasissnacks.oasissnacks.acitivity.ProductListActivity;
import com.oasissnacks.oasissnacks.interfce.OnCartItemClickListner;


import java.util.ArrayList;

import static com.oasissnacks.oasissnacks.R.drawable.*;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {



    public Integer[] item_quantity={1,2,3,4,5,6,7,8,9,10};
    public ArrayAdapter<Integer> adapter;
    private LinearLayout btn_remove,btn_edit;
    @NonNull
    ArrayList data,price,list_image;
    Context context;
    String cartEntity;
    OnCartItemClickListner listner;
    public CheckoutAdapter(Context context,ArrayList list_image, ArrayList list_data, ArrayList list_price,String cartEntity, OnCartItemClickListner listner) {
        this.context=context;
        this.data=list_data;
        this.price=list_price;
        this.list_image=list_image;
        this.cartEntity=cartEntity;
        this.listner=listner;
    }

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        if (cartEntity.equalsIgnoreCase("Normal")){

            View view=inflater.inflate(R.layout.layout_cartdetails,parent,false);
            btn_remove=view.findViewById(R.id.llremove);
            btn_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    remove();
                }
            });


            btn_edit=view.findViewById(R.id.llEdit);
            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edit();
                }
            });
            return new ViewHolder(view);
        }else {
            View view=inflater.inflate(R.layout.row_landing_page,parent,false);

            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageList.setImageResource((Integer) list_image.get(position));
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        if(cartEntity.equalsIgnoreCase("Normal")){
            holder.textView.setText((CharSequence)data.get(position));
            holder.textPrice.setText((CharSequence)price.get(position));
        }else {

        }
        //holder.imageList.setBackgroundResource((Integer)list_image.get(position));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textPrice;
        ImageView imageList;
        public LinearLayout llRoot;
        public ViewHolder(View view)
        {
            super(view);

        }

    }

    public void edit()
    {
        adapter=new ArrayAdapter<Integer>(context,android.R.layout.simple_spinner_item, item_quantity);
        final Spinner sp = new Spinner(context);
        sp.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        sp.setPadding(20,0,0,0);
        sp.setAdapter(adapter);
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage("Quantity");
        alertDialog.setView(sp);


        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Toast.makeText(context,"OK",Toast.LENGTH_SHORT).show();
            } });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }});

        alertDialog.show();

        final Button positivebutton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positivebutton.setAllCaps(false);
        positivebutton.setPadding(0, 0, 130, 0);
        positivebutton.setTextColor(Color.BLUE);
        positivebutton.setTextSize(12);

        final Button negativebutton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativebutton.setAllCaps(false);
        negativebutton.setPadding(0, 0, 250, 0);
        negativebutton.setTextColor(Color.BLACK);
        negativebutton.setTextSize(12);

    }


    public void remove()
    {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage("Remove Item");
        TextView message = new TextView(context);
        message.setText("Are you sure want to remove this item?");
        message.setPadding(50, 0, 0, 0);
        message.setGravity(Gravity.LEFT);
        message.setTextSize(12);
        message.setTextColor(Color.BLACK);
        alertDialog.setView(message);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Remove", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(context,"Remove Item Successfully",Toast.LENGTH_SHORT).show();
            } });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }});

        alertDialog.show();

        final Button positivebutton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positivebutton.setAllCaps(false);
        positivebutton.setPadding(0, 0, 100, 0);
        positivebutton.setTextColor(Color.BLUE);
        positivebutton.setTextSize(12);

        final Button negativebutton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativebutton.setAllCaps(false);
        negativebutton.setPadding(0, 0, 230, 0);
        negativebutton.setTextColor(Color.BLACK);
        negativebutton.setTextSize(12);
    }

     /*
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        holder.textView.setText((CharSequence) data.get(position));
        holder.textPrice.setText((CharSequence) price.get(position));
        holder.imageList.setImageResource((Integer) list_image.get(position));



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(context, ProductDetailsActivity.class);
                context.startActivity(intent);

                Toast.makeText(context,"asd",Toast.LENGTH_LONG).show();
            }
        });
    }
    */

}
