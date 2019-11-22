package com.oasissnacks.oasissnacks.acitivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.adapter.VIewPagerAdapter;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.utils.Preferences;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllDetailsActivity extends AppCompatActivity {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    TextView tvHeading, tvEndText,tvCartValue;
    RelativeLayout relativeLayout;
    @BindView(R.id.toolbar)
    View ToolBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_details);
        ButterKnife.bind(this);
        setToolBar();
        VIewPagerAdapter viewPagerAdapter = new VIewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setToolBar() {
        tvHeading = ToolBar.findViewById(R.id.tvHeading);
        tvHeading.setText("All Details");
        tvCartValue = ToolBar.findViewById(R.id.tvCartValue);
        tvEndText = ToolBar.findViewById(R.id.tvEndText);
        relativeLayout=ToolBar.findViewById(R.id.rlCart);
        relativeLayout.setVisibility(View.GONE);
        tvEndText.setVisibility(View.GONE);
        if(Preferences.getInstance(this).getCounter()==0){
            tvCartValue.setVisibility(View.INVISIBLE);
        }else {
            tvCartValue.setText(""+Preferences.getInstance(this).getCounter());
            tvCartValue.setVisibility(View.VISIBLE);
        }

    }
    public void back(View view){
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Preferences.getInstance(this).getCounter()==0){

            tvCartValue.setVisibility(View.INVISIBLE);
        }else {
            tvCartValue.setText(""+Preferences.getInstance(this).getCounter());
            tvCartValue.setVisibility(View.VISIBLE);
        }
    }
}
