package com.oasissnacks.oasissnacks.acitivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.adapter.CartAdapter;
import com.oasissnacks.oasissnacks.adapter.CartItemData;
import com.oasissnacks.oasissnacks.adapter.CartItemDataAdapter;
import com.oasissnacks.oasissnacks.app.ConnectivityReceiver;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.interfce.OnCartItemClickListner;
import com.oasissnacks.oasissnacks.network.ApiCallService;
import com.oasissnacks.oasissnacks.network.Response.cartresponse.CartProductResponse;
import com.oasissnacks.oasissnacks.network.Response.cartresponse.CartResponse;
import com.oasissnacks.oasissnacks.network.Response.request.CartRequest;
import com.oasissnacks.oasissnacks.network.Response.user.UserResponse;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.CustomTextView;
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

public class CartActivity extends RegisterAbstractActivity {


    @BindView(R.id.tvTotalPrice)
    TextView tvTotalPrice;
    @BindView(R.id.cart_recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    View Toolbar;
    @BindView(R.id.tvNoItemsCart)
    CustomTextView tvNoItemsCart;
    @BindView(R.id.llcartButton)
    LinearLayout llCartButton;
    public AppUser appUser = new AppUser();
    AppUser user = new AppUser();
    public MyProgressDialog progressDialog;
    double TOTALPRICE = 0.0;
    TextView tvHeading;
    ImageView ivCart;
    public CartAdapter adapter;
    public int flag = 0;
    public int clickedpostion;
    int NumberOfItem;
    public List<CartProductResponse> list = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartpage);
        ButterKnife.bind(this);
        setToolBar();
        progressDialog = new MyProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);
    }

    private void setToolBar() {
        tvHeading = Toolbar.findViewById(R.id.tvHeading);
        tvHeading.setText("My Cart");
        ivCart = Toolbar.findViewById(R.id.ivCart);
        ivCart.setVisibility(View.INVISIBLE);
    }

    @Subscribe
    public void timeout(String msg) {
        progressDialog.dismiss();
        Helper.alert(this, msg, "Error");
    }

    private void removeApi() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.ACTIONDELETECART);
        } else {
            Helper.alert(getApplicationContext(), "No Internet Connection", "Oasis Snacks");
        }
    }

    private void editApi() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            flag = 1;
            ApiCallService.action(getApplicationContext(), Cv.ACTION_EDITCART);
        } else {
            Helper.alert(getApplicationContext(), "No Internet Connection", "Oasis Snacks");
        }
    }

    @Subscribe
    public void getDeleteCartApi(UserResponse response) {
        progressDialog.dismiss();
        if (response.getStatus() == 200&&!response.getItemsCount().toString().equalsIgnoreCase("0")) {
            tvTotalPrice.setText("$ " + String.format("%.2f", response.getSubtotal()));
            list.remove(clickedpostion);

           // adapter.notifyItemRemoved(clickedpostion);
            adapter.notifyDataSetChanged();

            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
            int i = Preferences.getInstance(this).getCounter();
            i--;
            Preferences.getInstance(this).setCounter(i);
        }else {
            tvNoItemsCart.setVisibility(View.VISIBLE);
            llCartButton.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        apiCartProducts();
    }

    private void apiCartProducts() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            flag = 2;
            ApiCallService.action(getApplicationContext(), Cv.ACTION_GETCART);
        } else {
            Helper.alert(getApplicationContext(), "No Internet Connection", "Oasis Snacks");
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_cartpage;
    }

    public void back(View vIew) {
        finish();
    }


    @OnClick(R.id.btn_checkout)
    public void buttonclick() {
        if (user.lists != null) {
            Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
            startActivity(intent);

        } else {
            Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
            startActivity(intent);

        }


    }

    @Subscribe
    public void getCartProduct(CartResponse response) {
        progressDialog.dismiss();
        if (flag == 1) {
            tvTotalPrice.setText("$" + String.format("%.2f", response.getSubtotal()));

            list.get(clickedpostion).setQuantity(String.valueOf(NumberOfItem));
            adapter.notifyItemChanged(clickedpostion);
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();

        }else if(response.getList().size()==0){
        tvNoItemsCart.setVisibility(View.VISIBLE);
        llCartButton.setVisibility(View.GONE);
        } else {
            TOTALPRICE = 0.0;
            Preferences.getInstance(this).setCounter(response.list.size());
            list = response.getList();
            adapter = new CartAdapter(CartActivity.this, list, "Normal", (view, position, ENTITITY) -> {
                if (ENTITITY.equalsIgnoreCase("Remove")) {
                    Preferences.getInstance(this).setProductID(String.valueOf(response.getList().get(position).getId()));
                    LocalRepositories.saveAppUser(getApplicationContext(), appUser);
                    clickedpostion = position;
                    removeApi();

                }
                else if (ENTITITY.equalsIgnoreCase("Product")){
                    appUser.product_id =String.valueOf( list.get(position).getId());
                    LocalRepositories.saveAppUser(getApplicationContext(), appUser);
                    startActivity(new Intent(this, ProductDetailsActivity.class));
                }  else{
                    clickedpostion = position;
                    NumberOfItem=Integer.parseInt(ENTITITY);

                    Preferences.getInstance(this).setProductID(String.valueOf(response.getList().get(position).getId()));
                    CartRequest request = new CartRequest();
                    request.setCartId(Preferences.getInstance(CartActivity.this).getCartId());
                    request.setProductId(String.valueOf(response.getList().get(position).getId()));
                    request.setQty(Integer.parseInt(ENTITITY));
                    appUser.request = request;
                    LocalRepositories.saveAppUser(getApplicationContext(), appUser);
                    editApi();
                }
            });

            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));


            for (int i = 0; i < response.getList().size(); i++) {
                TOTALPRICE = TOTALPRICE + Integer.parseInt(response.getList().get(i).getQuantity()) * Double.parseDouble(String.valueOf(response.getList().get(i).getPrice()));

            }

            tvTotalPrice.setText("$" + String.format("%.2f", TOTALPRICE));
        }

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
}
