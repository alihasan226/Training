package com.example.listview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] web;
    private  final  Integer[] imageId;

    public CustomList(@NonNull Activity context, String[] web,Integer[] image) {
        super(context, R.layout.list_row,web);
        this.context=context;
        this.web=web;
        this.imageId=image;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_row, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.textview);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.image_view);
        txtTitle.setText(web[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}

