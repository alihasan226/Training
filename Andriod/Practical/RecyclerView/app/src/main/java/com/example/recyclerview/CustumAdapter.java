package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustumAdapter extends RecyclerView.Adapter<CustumAdapter.CustomeAdaterViewHolder>//it is a generic type passing the type of view holder
{
    private String[] data;
    public CustumAdapter(String[] data)//data the are going to pass the adapter to show in the recycler view
    {
        this.data=data;
    }
    @NonNull
    @Override//create view and store view in it and put that it viewHolder
    public CustomeAdaterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.rowlayout,parent,false);
        return new CustomeAdaterViewHolder(view);
    }

    @Override//bind the data and view after created by onCreateViewHolder
    public void onBindViewHolder(@NonNull CustomeAdaterViewHolder holder, int position) {
        String title=data[position];
        holder.textView.setText(title);
    }

    @Override//item count of data which we are going to show recycler view
    public int getItemCount() {
        return data.length;
    }

    public class CustomeAdaterViewHolder extends RecyclerView.ViewHolder//it retain the view for which we can recycle
    {
        ImageView imageView;
        TextView textView;

        public CustomeAdaterViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.image1);
            textView=(TextView)itemView.findViewById(R.id.text);
        }
    }
}
