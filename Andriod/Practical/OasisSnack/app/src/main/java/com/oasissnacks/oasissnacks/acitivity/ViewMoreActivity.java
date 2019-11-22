package com.oasissnacks.oasissnacks.acitivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.adapter.ProductsAdapter;
import com.oasissnacks.oasissnacks.adapter.ViewMoreAdapter;
import com.oasissnacks.oasissnacks.app.ConnectivityReceiver;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.network.ApiCallService;
import com.oasissnacks.oasissnacks.network.Response.product.ProductResponse;
import com.oasissnacks.oasissnacks.network.Response.product.Products;
import com.oasissnacks.oasissnacks.network.Response.request.CartRequest;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.Cv;
import com.oasissnacks.oasissnacks.utils.Helper;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.MyProgressDialog;
import com.oasissnacks.oasissnacks.utils.Preferences;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.oasissnacks.oasissnacks.acitivity.ProductListActivity.pageNo;

public class ViewMoreActivity extends RegisterAbstractActivity implements View.OnClickListener{

    @BindView(R.id.toolbar)
    View toolbar;
    private Context context;

    @BindView(R.id.viewmore_recycler_view)
    RecyclerView vmRecyclerView;

    LinearLayoutManager linearLayoutManager;
    ViewMoreAdapter productsAdapter;
    private TextView tv_Heading;
    private TextView tvCartValue;
    private ImageView ivCart;

    boolean layout_add = false;
    ArrayList<Products> venueList = new ArrayList<Products>();
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    AppUser getAppUser;
    MyProgressDialog progressDialog;
    private boolean loading = true;
    public static int pageNo = 1;
    public AppUser appUser = new AppUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmore);
        ButterKnife.bind(this);
        tv_Heading=toolbar.findViewById(R.id.tvHeading);
        tv_Heading.setText("View All");

        context=this;

        appUser = LocalRepositories.getAppUser(this);
        progressDialog = new MyProgressDialog(context);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);

        appUser = LocalRepositories.getAppUser(getApplicationContext());

        linearLayoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        vmRecyclerView.setLayoutManager(linearLayoutManager);



        tvCartValue = toolbar.findViewById(R.id.tvCartValue);
        ivCart = toolbar.findViewById(R.id.ivCart);
        ivCart.setOnClickListener(this);
        getAppUser = LocalRepositories.getAppUser(this);
        tvCartValue.setText("" + (getAppUser != null ? getAppUser.counter : 0));
        tvCartValue.setVisibility(View.VISIBLE);
        tvCartValue.setOnClickListener(this);

        if(Preferences.getInstance(this).getCounter()==0){

            tvCartValue.setVisibility(View.INVISIBLE);
        }else {
            tvCartValue.setText(""+Preferences.getInstance(this).getCounter());
            tvCartValue.setVisibility(View.VISIBLE);
        }

    }



    @Override
    protected void onResume() {

        appUser = LocalRepositories.getAppUser(getApplicationContext());
        loading = true;
        pageNo = 1;
        appUser.page_no = "1";


        if(Preferences.getInstance(this).getCounter()==0){

            tvCartValue.setVisibility(View.INVISIBLE);
        }else {
            tvCartValue.setText(""+Preferences.getInstance(this).getCounter());
            tvCartValue.setVisibility(View.VISIBLE);
        }

        LocalRepositories.saveAppUser(getApplicationContext(), appUser);
        Boolean isConnected = ConnectivityReceiver.isConnected();
        if (isConnected) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.ACTION_GET_All_RELATEDPRODUCT);
        }


        vmRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            pageNo++;
                            appUser.page_no = String.valueOf(pageNo);
                            LocalRepositories.saveAppUser(getApplicationContext(), appUser);
                            Boolean isConnected = ConnectivityReceiver.isConnected();
                            if (isConnected) {
                                progressDialog.show();
                                ApiCallService.action(context, Cv.ACTION_GET_All_RELATEDPRODUCT);
                            }
                        }

                    }


                }
            }
        });
        super.onResume();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_viewmore;
    }


    @Subscribe
    public void get_all_relatedproduct(ProductResponse response) {
        progressDialog.dismiss();
        if (response.getStatus() == 200) {
            if (response.getProducts().size() > 0) {
                if (pageNo > 1) {
                    if (response.getProducts().size() > 0) {
                        venueList.removeAll(response.getProducts());
                        venueList.addAll(response.getProducts());
                        Preferences.getInstance(this).setPRoducts(venueList.size());
                        productsAdapter.notifyDataSetChanged();
                        progressDialog.dismiss();
                    }
                } else if (pageNo == 1) {
                    venueList.clear();
                    venueList.removeAll(response.getProducts());
                    venueList = response.getProducts();
                    Preferences.getInstance(this).setPRoducts(venueList.size());
                    productsAdapter = new ViewMoreAdapter(this, venueList);
                    vmRecyclerView.setAdapter(productsAdapter);
                    progressDialog.dismiss();
                }

                loading = true;

            } else {
                loading = false;
            }
        }
    }


    public void back(View vIew){
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ivCart:
                startActivity(new Intent(ViewMoreActivity.this, CartActivity.class));
                break;
            default:
                break;
        }
    }

}
