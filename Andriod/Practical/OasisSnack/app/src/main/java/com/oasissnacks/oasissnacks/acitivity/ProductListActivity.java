package com.oasissnacks.oasissnacks.acitivity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.adapter.ProductsAdapter;
import com.oasissnacks.oasissnacks.app.ConnectivityReceiver;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.network.ApiCallService;
import com.oasissnacks.oasissnacks.network.Response.cartresponse.AddToCartResponse;
import com.oasissnacks.oasissnacks.network.Response.product.ProductList;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ProductListActivity extends RegisterAbstractActivity implements View.OnClickListener {





    @BindView(R.id.product_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    View toolbar;

    @BindView(R.id.inflated_expandablelist)
    ExpandableListView expandableListView;
    @BindView(R.id.filter_layout)
    LinearLayout mFilterLayout;

    @BindView(R.id.rvSort)
    RecyclerView rvSort;

    @BindView(R.id.llSortSlidingPanel)
    View llSortSlidingPanel;
    @BindView(R.id.llSort)
    View llSort;
    AppUser getAppUser;
    MyProgressDialog progressDialog;
    private boolean loading = true;
    public static int pageNo = 1;
    public AppUser appUser = new AppUser();
    public String[] SORTARRAY = {"position", "name", "price"};


    private ActionBar actionBar;
    private String[] item = {"Relevance", "Popularity", "Newest First", "Price --Low To High", "Price --High To Low"};
    private ArrayList<String> list = new ArrayList<>(Arrays.asList(item));
    public TextView tvHeading, tvCartValue;
    private ImageView ivCart;
    public Map AddToCartRequest = new HashMap();

    public Context context;




    ArrayList<Products> venueList = new ArrayList<Products>();
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    GridLayoutManager gridLayoutManager;
    ProductsAdapter productsAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("actionbar_name");
        ButterKnife.bind(this);
        context = this;
        appUser = LocalRepositories.getAppUser(this);
        progressDialog = new MyProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);

        appUser = LocalRepositories.getAppUser(getApplicationContext());


        appUser.FilerElements.clear();
        appUser.filter.clear();
        LocalRepositories.saveAppUser(getApplicationContext(), appUser);


        tvHeading = toolbar.findViewById(R.id.tvHeading);
        tvCartValue = toolbar.findViewById(R.id.tvCartValue);
        tvHeading.setText(message);
        ivCart = toolbar.findViewById(R.id.ivCart);
        ivCart.setOnClickListener(this);
        getAppUser = LocalRepositories.getAppUser(this);
        tvCartValue.setText("" + (getAppUser != null ? getAppUser.counter : 0));
        tvCartValue.setVisibility(View.VISIBLE);
        tvCartValue.setOnClickListener(this);
        llSort.setOnClickListener(this);

        if (Preferences.getInstance(this).getCounter() == 0) {

            tvCartValue.setVisibility(View.INVISIBLE);
        } else {
            tvCartValue.setText("" + Preferences.getInstance(this).getCounter());
            tvCartValue.setVisibility(View.VISIBLE);
        }








        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvSort.setLayoutManager(layoutManager);



        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);


    }

    @Override
    protected void onResume() {

        appUser = LocalRepositories.getAppUser(getApplicationContext());
        loading = true;
        pageNo = 1;
        appUser.page_no = "1";
        venueList = new ArrayList<Products>();


        if (Preferences.getInstance(this).getCounter() == 0) {

            tvCartValue.setVisibility(View.INVISIBLE);
        } else {
            tvCartValue.setText("" + Preferences.getInstance(this).getCounter());
            tvCartValue.setVisibility(View.VISIBLE);
        }

        LocalRepositories.saveAppUser(getApplicationContext(), appUser);
        Boolean isConnected = ConnectivityReceiver.isConnected();
        if (isConnected) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.ACTION_GET_CATEGORY_PRODUCTS);
        }


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = gridLayoutManager.getChildCount();
                    totalItemCount = gridLayoutManager.getItemCount();
                    pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            pageNo++;
                            appUser.page_no = String.valueOf(pageNo);
                            LocalRepositories.saveAppUser(getApplicationContext(), appUser);
                            Boolean isConnected = ConnectivityReceiver.isConnected();
                            if (isConnected) {
                                progressDialog.show();
                                ApiCallService.action(context, Cv.ACTION_GET_CATEGORY_PRODUCTS);
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
        return R.layout.activity_product_list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @OnClick(R.id.llFilters)
    public void buttonclick(View view) {

        /*if (!layout_add) {
            layout_add=true;
            mFilterLayout.setVisibility(View.VISIBLE);
        }else{
            layout_add=false;
            mFilterLayout.setVisibility(View.GONE);
        }*/
        startActivity(new Intent(ProductListActivity.this, FilterActivity.class));

    }

    public void back(View vIew) {
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.icon_shoppingcart:
                addtocart();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void addtocart() {
        AlertDialog alertDialog = new AlertDialog.Builder(ProductListActivity.this).create();
        TextView message = new TextView(this);
        message.setText("Cart is Empty");
        message.setPadding(0, 35, 0, 0);
        message.setGravity(Gravity.CENTER_HORIZONTAL);
        message.setTextSize(10);
        message.setTextColor(Color.BLACK);
        alertDialog.setView(message);
        alertDialog.show();

        startActivity(new Intent(ProductListActivity.this, CartActivity.class));

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivCart:
                if(Preferences.getInstance(this).getCounter()==0){
                    Toast.makeText(this, "Please add items to cart.", Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(ProductListActivity.this, CartActivity.class));
                }
                break;
            case R.id.llSort:
                dialogShow();
                break;
            case R.id.tvCartValue:
                startActivity(new Intent(ProductListActivity.this, CartActivity.class));
                break;
            default:
                break;

        }
    }

    private void dialogShow() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.dialog_bottamsheet);
        RadioGroup radioGroup = dialog.findViewById(R.id.rgRight);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                appUser.filter.put("sort_by", SORTARRAY[index]);
                LocalRepositories.saveAppUser(getApplicationContext(), appUser);
                dialog.dismiss();
                onPause();
                onResume();

            }
        });
        dialog.show();
    }

    @Override
    public void onBackPressed() {

        finish();


    }

    @Subscribe
    public void get_category_product(ProductResponse response) {
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
                    productsAdapter = new ProductsAdapter(this, venueList, (view, position) -> {
                        addToCartApi();
                        appUser.CARTELEMENTS.clear();
                        CartRequest request = new CartRequest();
                        request.setCartId(Preferences.getInstance(ProductListActivity.this).getCartId());
                        request.setProductId(venueList.get(position).getId());
                        request.setQty(1);
                        request.setUserId("guest");
                        appUser.request = request;
                        LocalRepositories.saveAppUser(getApplicationContext(), appUser);

                    });
                    recyclerView.setAdapter(productsAdapter);
                    progressDialog.dismiss();
                }

                loading = true;



            } else {


                loading = false;

            }
        }else {
         /*   venueList.clear();
            venueList.addAll(response.getProducts());
            productsAdapter.notifyDataSetChanged();*/


        }
    }

    @Subscribe
    public void getCartApiDeails(AddToCartResponse addToCartResponse) {
        progressDialog.dismiss();
        if (addToCartResponse.getStatus() == 200) {
            LocalRepositories.getAppUser(getApplicationContext());
            Preferences.getInstance(this).setCounter(addToCartResponse.getItemsCount());
            addToCartResponse.getItemsCount();
            tvCartValue.setVisibility(View.VISIBLE);
            tvCartValue.setText(addToCartResponse.getItemsCount() + "");


            Toast.makeText(this, addToCartResponse.getMessage(), Toast.LENGTH_SHORT).show();
            appUser.cartId = addToCartResponse.getCartId();
            Preferences.getInstance(this).setCartID(addToCartResponse.getCartId());
            LocalRepositories.saveAppUser(getApplicationContext(), appUser);
        } else {
            Toast.makeText(context, addToCartResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void addToCartApi() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();

            ApiCallService.action(getApplicationContext(), Cv.ACTION_ADDCART);
        } else {
            Helper.alert(this, "No Internet Connection", "Oasis Snacks");
        }
    }

    @Subscribe
    public void timeout(String msg) {
        progressDialog.dismiss();
        Helper.alert(this, msg, "Error");
    }


}
