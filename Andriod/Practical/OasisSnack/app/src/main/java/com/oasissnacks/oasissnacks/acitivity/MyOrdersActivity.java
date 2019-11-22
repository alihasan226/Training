package com.oasissnacks.oasissnacks.acitivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.adapter.MyOrderAdapter;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.utils.Preferences;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrdersActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    View toolbar;
    @BindView(R.id.rvMyOrder)
    RecyclerView rvMyOrder;
    private LinearLayoutManager linearLayoutManager;
    TextView tvHeading,tvCartValue;
    ArrayList<String> listOrderId,listProductName,listPrice;
    ArrayList<Integer> listImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorders);
        ButterKnife.bind(this);
        setUpToolBar();

        listOrderId=new ArrayList<String>();
        listProductName=new ArrayList<String>();
        listPrice=new ArrayList<String>();
        listImage=new ArrayList<Integer>();

        listOrderId.add("SDH373BHSH6723STDSH");
        listOrderId.add("OPIRPWOEIR634VF782B");
        listProductName.add("Muscle Milk Genuine Non Dairy Protein Shake 5 Flavour");
        listProductName.add("Muscle Milk Genuine Non Dairy Protein Shake 5 Flavour");
        listPrice.add("$38.99");
        listPrice.add("$47.99");
        listImage.add(R.drawable.bubly_sparking_water_apple);
        listImage.add(R.drawable.bubly_sparking_water);


        linearLayoutManager=new LinearLayoutManager(MyOrdersActivity.this,LinearLayoutManager.VERTICAL,false);
        rvMyOrder.setLayoutManager(linearLayoutManager);
        rvMyOrder.setNestedScrollingEnabled(false);
        rvMyOrder.setFocusable(false);
        rvMyOrder.setAdapter(new MyOrderAdapter(MyOrdersActivity.this,listOrderId,listProductName,listPrice,listImage));

    }

    /*@Override
    protected int layoutId() {
        return R.layout.activity_myorders;
    }*/

    @Override
    protected void onResume() {
        super.onResume();
        if (Preferences.getInstance(this).getCounter() == 0) {

            tvCartValue.setVisibility(View.INVISIBLE);
        } else {
            tvCartValue.setText("" + Preferences.getInstance(this).getCounter());
            tvCartValue.setVisibility(View.VISIBLE);
        }
    }

   /* @Subscribe
    public void MyOrders(){

    }*/
    private void setUpToolBar() {
        tvHeading=toolbar.findViewById(R.id.tvHeading);
        tvHeading.setText("My Orders");
        tvCartValue = toolbar.findViewById(R.id.tvCartValue);
    }

    public void back(View vIew){
        finish();
    }

}
