package com.oasissnacks.oasissnacks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.network.Response.homeresponse.MainBannerResponse;

import java.util.List;

public class SubscribeImageAdapter extends PagerAdapter {



    private List<MainBannerResponse> mainBanner;
    private LayoutInflater inflater;
    private Context context;
    public String text;

    public SubscribeImageAdapter(List<MainBannerResponse> mainBanner, Context context, String text)
    {
        this.mainBanner=mainBanner;
        this.context=context;
        this.text=text;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.subscribeviewpager,container,false);
        ImageView imageView;
        LinearLayout layout;
        imageView=view.findViewById(R.id.viewpager_image);

       /* if(text.equalsIgnoreCase("Normal")){
            layout.setVisibility(View.VISIBLE);
        }else {
            layout.setVisibility(View.GONE);

        }*/
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.no_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .dontAnimate()
                .dontTransform();
        Glide.with(context)
                .load((mainBanner.get(position).getImage()))
                .apply(options)
                .into(imageView);


        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return mainBanner.size();
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
