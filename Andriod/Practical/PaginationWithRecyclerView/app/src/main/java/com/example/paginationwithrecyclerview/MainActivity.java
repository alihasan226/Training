package com.example.paginationwithrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    boolean scrolling=false;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    Adapter adapter;
    ProgressBar progressBar;
    ArrayList list;
    int currentItem,totalItem,scrollOutItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerview);
        Context context;
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        String[] data={"1","2","4","56","ali","hasan","beryl","toast","34","3","10","android","radius","circle","square","stroke","52.36","45.03"};
        list=new ArrayList(Arrays.asList(data));//converting string to ArrayList

        adapter=new Adapter(list,this);//here we just putting the data of arraylist in adapter

        recyclerView.setAdapter(adapter);//here we putting the data of adapter in receycler view

        progressBar=findViewById(R.id.progressbar);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {//here we calling the listener OnScrollListener
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {//when scrolling start
                super.onScrollStateChanged(recyclerView, newState);
                if(newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                {
                    scrolling=true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {//after scroling end
                super.onScrolled(recyclerView, dx, dy);
                currentItem=linearLayoutManager.getChildCount();
                totalItem=linearLayoutManager.getItemCount();
                scrollOutItem=linearLayoutManager.findFirstCompletelyVisibleItemPosition();

                if(scrollOutItem+currentItem == totalItem)
                {
                    scrolling=false;
                    fetchdata();
                }
            }
        });
    }



    public void fetchdata()
    {
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<5;i++)
                    {
                        list.add(Math.floor(Math.random()*100)+"");
                        adapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }, 5000
        );
    }




}
