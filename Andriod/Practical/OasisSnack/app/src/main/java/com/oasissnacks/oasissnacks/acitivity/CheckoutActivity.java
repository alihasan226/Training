package com.oasissnacks.oasissnacks.acitivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amazon.identity.auth.device.workflow.WorkflowConstants;
import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.adapter.CartAdapter;
import com.oasissnacks.oasissnacks.adapter.CheckoutAdapter;
import com.oasissnacks.oasissnacks.adapter.ShippingMethodAdapter;
import com.oasissnacks.oasissnacks.app.ConnectivityReceiver;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.interfce.OnCartItemClickListner;
import com.oasissnacks.oasissnacks.interfce.OnFilterClickListner;
import com.oasissnacks.oasissnacks.network.Api;
import com.oasissnacks.oasissnacks.network.ApiCallService;
import com.oasissnacks.oasissnacks.network.Response.cartresponse.CartProductResponse;
import com.oasissnacks.oasissnacks.network.Response.cartresponse.CartResponse;
import com.oasissnacks.oasissnacks.network.Response.checkoutresponse.CheckoutResponse;
import com.oasissnacks.oasissnacks.network.Response.request.CartRequest;
import com.oasissnacks.oasissnacks.network.Response.shiipingresponse.EstimateRateResponse;
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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheckoutActivity extends RegisterAbstractActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    @BindView(R.id.rvCheckOut)
    RecyclerView rvCheckout;
    @BindView(R.id.tvDontAddress)
    TextView tvDontAddress;
    @BindView(R.id.toolbar)
    View view;
    @BindView(R.id.tvAmount)
    CustomTextView tvAmount;
    @BindView(R.id.tvPayableAmount)
    CustomTextView tvPayableAmount;
    TextView textView;
    double TOTALPRICE = 0.0;
    @BindView(R.id.price)
    TextView tvPrice;

    AppUser user = new AppUser();
    @BindView(R.id.btnContinue)
    Button btnBilling;
    @BindView(R.id.tvUserName)
    CustomTextView tvUserName;
    @BindView(R.id.tvUserAddress)
    CustomTextView tvUserAddress;
    @BindView(R.id.tvRegionAndPostalCode)
    CustomTextView tvREgionAndCode;
    @BindView(R.id.tvMobileNumber)
    CustomTextView tvMobileNumber;
    @BindView(R.id.tvTotalPrice)
    CustomTextView tvTotalPrice;
    public CheckoutResponse checkoutResponse = new CheckoutResponse();
    public AppUser appUser = new AppUser();
    public MyProgressDialog progressDialog;
    public CartAdapter adapter;
    public int flag = 0;
    public int NumberOfItem;
    @BindView(R.id.tvDeliverPrice)
    TextView tvDeliveryPrice;
    public int clickedpostion;
    public ImageView ivCart;
    @BindView(R.id.rvShippingMethod)
    public RecyclerView rvShippingMethod;
    public Double DeliverPrice=0.0;
    public List<CartProductResponse> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);
        setUpToolBar();

        progressDialog = new MyProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);

        btnBilling.setOnClickListener(this);

    }

    private void setUpToolBar() {
        textView = view.findViewById(R.id.tvHeading);
        textView.setText("Order Summary");
        ivCart = view.findViewById(R.id.ivCart);
        ivCart.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAddressDetails();
        apiCartProducts();

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_checkout;
    }

    private void apiCartProducts() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();

            ApiCallService.action(getApplicationContext(), Cv.ACTION_GETCART);
        } else {
            Helper.alert(getApplicationContext(), "No Internet Connection", "Oasis Snacks");
        }
    }

    @Subscribe
    public void get(CartResponse response) {
        progressDialog.dismiss();

        if (flag == 1) {
            if (!(DeliverPrice==0.0)){
                tvTotalPrice.setText("$" + String.format("%.2f", response.getSubtotal()+DeliverPrice));
                tvPayableAmount.setText("$ " + String.format("%.2f", response.getSubtotal()+DeliverPrice));
                tvAmount.setText("$ " + String.format("%.2f", response.getSubtotal()));

            }else {
                tvTotalPrice.setText("$" + String.format("%.2f", response.getSubtotal()));
                tvAmount.setText("$ " + String.format("%.2f", response.getSubtotal()));
                tvPayableAmount.setText("$ " + String.format("%.2f", response.getSubtotal()));
            }

            for (int i = 0; i < response.getList().size(); i++) {
                TOTALPRICE = TOTALPRICE + Integer.parseInt(response.getList().get(i).getQuantity()) * Double.parseDouble(String.valueOf(response.getList().get(i).getPrice()));

            }
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();

            list.get(clickedpostion).setQuantity(String.valueOf(NumberOfItem));
            adapter.notifyDataSetChanged();

        } else {
            tvPrice.setText("Price("+response.getList().size()+" item)");
            apiGetShippingMethod();
            TOTALPRICE = 0.0;
            list = response.getList();
            Preferences.getInstance(this).setCounter(response.list.size());
            adapter = new CartAdapter(this, list, "Checkout", (view, position, ENTITITY) -> {
                if (ENTITITY.equalsIgnoreCase("Product")){
                    appUser.product_id =String.valueOf( list.get(position).getId());
                    LocalRepositories.saveAppUser(getApplicationContext(), appUser);
                    startActivity(new Intent(this, ProductDetailsActivity.class));
                } else {
                    clickedpostion = position;
                    NumberOfItem=Integer.parseInt(ENTITITY);

                    Preferences.getInstance(this).setProductID(String.valueOf(response.getList().get(position).getId()));
                    CartRequest request = new CartRequest();
                    request.setCartId(Preferences.getInstance(this).getCartId());
                    request.setProductId(String.valueOf(response.getList().get(position).getId()));
                    request.setQty(Integer.parseInt(ENTITITY));
                    appUser.request = request;
                    LocalRepositories.saveAppUser(getApplicationContext(), appUser);
                    editApi();
                }
            });

            rvCheckout.setAdapter(adapter);
            rvCheckout.setLayoutManager(new LinearLayoutManager(this));


            for (int i = 0; i < response.getList().size(); i++) {
                TOTALPRICE = TOTALPRICE + Integer.parseInt(response.getList().get(i).getQuantity()) * Double.parseDouble(String.valueOf(response.getList().get(i).getPrice()));

            }

            tvTotalPrice.setText("$" + String.format("%.2f", TOTALPRICE));
            tvAmount.setText("$" + String.format("%.2f", TOTALPRICE));
            tvPayableAmount.setText("$" + String.format("%.2f", TOTALPRICE));

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

    public void back(View VIew) {
        finish();
    }


    private void getAddressDetails() {
        appUser = LocalRepositories.getAppUser(getApplicationContext());
        if (appUser != null && appUser.checkoutResponse.getShippingAddress() != null) {
            tvDontAddress.setVisibility(View.GONE);
            checkoutResponse = appUser.checkoutResponse;
            tvUserName.setText(checkoutResponse.getShippingAddress().getFirstname() + " " + checkoutResponse.getShippingAddress().getLastname());
            tvMobileNumber.setText(checkoutResponse.getShippingAddress().getTelephone());
            tvREgionAndCode.setText(checkoutResponse.getShippingAddress().getCountryId() + " - " + checkoutResponse.getShippingAddress().getPostcode());
            tvUserAddress.setText(checkoutResponse.getShippingAddress().getStreet() + ", " + checkoutResponse.getShippingAddress().getCity() + "," + checkoutResponse.getShippingAddress().getRegion());
        } else {
            tvDontAddress.setVisibility(View.VISIBLE);
            tvUserName.setVisibility(View.GONE);
            tvMobileNumber.setVisibility(View.GONE);
            tvREgionAndCode.setVisibility(View.GONE);
            tvUserAddress.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.btn_checkout)
    public void btnCheckOutClick() {
        Preferences.getInstance(this).setAddress("billing");
        Intent intent = new Intent(CheckoutActivity.this, SavedAddressActivity.class);
        startActivity(intent);
    }




    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            appUser = LocalRepositories.getAppUser(getApplicationContext());
            if (appUser.checkoutResponse.getShippingAddress() != null) {

                checkoutResponse.setShippingAddress(appUser.checkoutResponse.getShippingAddress());
                checkoutResponse.setCurrencyId("USD");
                checkoutResponse.setBillingAddress(appUser.checkoutResponse.getShippingAddress());
                checkoutResponse.setSaveInAddressBook(1);
                if (Preferences.getInstance(this).getCartId().equalsIgnoreCase("")) {
                } else {
                    checkoutResponse.setQuoteId(Integer.parseInt(Preferences.getInstance(this).getCartId()));
                }
                appUser.checkoutResponse = checkoutResponse;
                LocalRepositories.saveAppUser(getApplicationContext(), appUser);
                //apiShippingAdddress();
            } else {
                if (appUser.checkoutResponse.getShippingAddress() != null && appUser.checkoutResponse.getBillingAddress() != null) {
                    checkoutResponse.setCurrencyId("USD");
                    checkoutResponse.setSaveInAddressBook(1);
                    if (Preferences.getInstance(this).getCartId().equalsIgnoreCase("")) {
                    } else {
                        checkoutResponse.setQuoteId(Integer.parseInt(Preferences.getInstance(this).getCartId()));
                    }
                    appUser.checkoutResponse = checkoutResponse;
                    LocalRepositories.saveAppUser(getApplicationContext(), appUser);

                }


            }

        } else {
        }
    }




    private void apiGetShippingMethod() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.ACTIONGETSHIPPINGMETHOD);
        } else {
            Toast.makeText(this, "Please check your internet.", Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe
    public void getShipingMethod(EstimateRateResponse response) {
        progressDialog.dismiss();
        if (response.getStatus() == 200 &&response.getRates().size()!=0) {
            rvShippingMethod.setAdapter(new ShippingMethodAdapter(response.getRates(), this, (view, position) -> {
                DeliverPrice=response.getRates().get(position).getPrice();
                tvTotalPrice.setText("$"+String.format("%.2f",TOTALPRICE+response.getRates().get(position).getPrice()));
                tvPayableAmount.setText("$"+String.format("%.2f",TOTALPRICE+response.getRates().get(position).getPrice()));
                tvDeliveryPrice.setText("$"+response.getRates().get(position).getPrice());

            }));
            rvShippingMethod.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        }else {
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void timeout(String msg) {
        progressDialog.dismiss();
        Helper.alert(this, msg, "Error");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnContinue:
                startActivity(new Intent(CheckoutActivity.this,PlaceOrderActivity.class));
                break;

                default:
                break;
        }
    }
}
