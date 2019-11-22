package com.oasissnacks.oasissnacks.acitivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.adapter.WishListAdaptet;
import com.oasissnacks.oasissnacks.app.ConnectivityReceiver;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.interfce.OnFilterClickListner;
import com.oasissnacks.oasissnacks.network.ApiCallService;
import com.oasissnacks.oasissnacks.network.Response.product.Products;
import com.oasissnacks.oasissnacks.network.Response.user.UserResponse;
import com.oasissnacks.oasissnacks.network.Response.wishlistresponse.WishListResponse;
import com.oasissnacks.oasissnacks.utils.CustomTextView;
import com.oasissnacks.oasissnacks.utils.Cv;
import com.oasissnacks.oasissnacks.utils.Helper;
import com.oasissnacks.oasissnacks.utils.MyProgressDialog;
import com.oasissnacks.oasissnacks.utils.Preferences;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWishListActivity extends RegisterAbstractActivity {


    @BindView(R.id.toolbar)
    View toolbar;
    public MyProgressDialog progressDialog;
    @BindView(R.id.rvWishList)
    RecyclerView rvWishList;
    @BindView(R.id.tvNoItems)
    CustomTextView tvNoItems;
    public int ClickedPosition;
    TextView tvCartValue;
    public List<Products> list = new ArrayList<>();
    public WishListAdaptet adaptet;
    private LinearLayoutManager linearLayoutManager;


    private TextView tvHeading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mywishlist);
        ButterKnife.bind(this);
        progressDialog = new MyProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);
        setUpToolbar();



    }

    private void setUpToolbar() {
        tvHeading = toolbar.findViewById(R.id.tvHeading);
        tvHeading.setText("My WishList");
        tvCartValue = toolbar.findViewById(R.id.tvCartValue);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Preferences.getInstance(this).getCounter() == 0) {

            tvCartValue.setVisibility(View.INVISIBLE);
        } else {
            tvCartValue.setText("" + Preferences.getInstance(this).getCounter());
            tvCartValue.setVisibility(View.VISIBLE);
        }
        apiGetWishList();
    }

    private void apiGetWishList() {

        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.GETWISHLIST);
        } else {
            Helper.alert(this, "No Internet Connection", "Oasis Snacks");
        }

    }

    @Subscribe
    public void getMyWishList(WishListResponse response) {
        progressDialog.dismiss();
        if (response.getProducts() != null && response.getProducts().size() != 0) {
            tvNoItems.setVisibility(View.GONE);
            linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rvWishList.setLayoutManager(linearLayoutManager);
            list = response.getProducts();
            adaptet = new WishListAdaptet(list, this, (view, position) -> {
                Preferences.getInstance(this).setProductID(response.getProducts().get(position).getId());
                ClickedPosition = position;
                apiRemoveWishList();

            });
            rvWishList.setAdapter(adaptet);
        }
        else {
            tvNoItems.setVisibility(View.VISIBLE);
        }

    }

    @Subscribe
    public void deleteWishListStatus(UserResponse response) {
        progressDialog.dismiss();
        if (response.getStatus() == 200) {
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
            list.remove(ClickedPosition);
            adaptet.notifyItemRemoved(ClickedPosition);

            if(adaptet.getItemCount()==0)
            {
                tvNoItems.setVisibility(View.VISIBLE);
            }
        }
    }

    private void apiRemoveWishList() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.REMOVEWISHLIST);
        } else {
            Toast.makeText(this, "Please check your internet.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected int layoutId() {
        return R.layout.activity_mywishlist;

    }

    public void back(View vIew) {
        finish();
    }

}
