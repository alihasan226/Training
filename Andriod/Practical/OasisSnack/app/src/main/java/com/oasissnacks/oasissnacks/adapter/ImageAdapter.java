package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.interfce.onViewPagerItemClickListner;
import com.oasissnacks.oasissnacks.network.Response.homeresponse.ProductResponse;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends PagerAdapter {



    private List<ProductResponse>list;
    private LayoutInflater inflater;
    private Context context;
    TextView tvPRoductName,tvProductPrice;
    public onViewPagerItemClickListner listner;
    ImageView imageView;
    LinearLayout layout;
    TextView btnAddToCArt;


    public ImageAdapter(List<ProductResponse>list, Context context,onViewPagerItemClickListner listner)
    {
        this.list=list;
        this.context=context;
        this.listner=listner;

    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.viewpager,container,false);

        imageView=view.findViewById(R.id.viewpager_image);
        layout=view.findViewById(R.id.llRoot);
        btnAddToCArt=view.findViewById(R.id.btn_addtocart);
        tvPRoductName=view.findViewById(R.id.tvProductName);
        tvProductPrice=view.findViewById(R.id.tvProductPrice);
        tvProductPrice.setText("$"+list.get(position).getPrice().toString());
        tvPRoductName.setText(list.get(position).getName());
        btnAddToCArt.setOnClickListener(view1 -> {
            listner.onProductButtonClick(view1,position);
        });
        layout.setOnClickListener(view12 -> {
            listner.onProuctClick(view12,position);
        });

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.no_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .dontAnimate()
                .dontTransform();
        Glide.with(context)
                .load(list.get(position).getImage())
                .apply(options)
                .into(imageView);

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }


    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }


}
