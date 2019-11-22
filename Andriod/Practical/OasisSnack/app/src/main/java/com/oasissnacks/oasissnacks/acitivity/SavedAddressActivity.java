package com.oasissnacks.oasissnacks.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.adapter.SavedAddressAdapter;
import com.oasissnacks.oasissnacks.app.ConnectivityReceiver;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.network.ApiCallService;
import com.oasissnacks.oasissnacks.network.Response.checkoutresponse.BillingAddressResponse;
import com.oasissnacks.oasissnacks.network.Response.checkoutresponse.CheckoutResponse;
import com.oasissnacks.oasissnacks.network.Response.user.UserResponse;
import com.oasissnacks.oasissnacks.network.userresponse.GetUserResponse;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.Cv;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.MyProgressDialog;
import com.oasissnacks.oasissnacks.utils.Preferences;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedAddressActivity extends RegisterAbstractActivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    View toolbar;
    @BindView(R.id.tvAddAddress)
            TextView tvAddAddress;
    TextView tvHeading;
    @BindView(R.id.rvAddNewAddress)
    RecyclerView rvAddNewAddress;
    @BindView(R.id.llDELEIVERHERE)
    LinearLayout llDeliveeHere;
    RelativeLayout rlCart;
    private LinearLayoutManager linearLayoutManager;
    public MyProgressDialog progressDialog;
    AppUser appUser = new AppUser();
    CheckoutResponse response = new CheckoutResponse();
    BillingAddressResponse billingAddressResponse = new BillingAddressResponse();

    private ArrayList<String> listName,listAddress,listNumber;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savedaddress);
        ButterKnife.bind(this);
        tvHeading=toolbar.findViewById(R.id.tvHeading);
        rlCart=toolbar.findViewById(R.id.rlCart);
        rlCart.setVisibility(View.GONE);
        tvHeading.setText("Select Address");
        progressDialog = new MyProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);
        tvAddAddress.setOnClickListener(this);
        llDeliveeHere.setOnClickListener(this);

        listName=new ArrayList<String>();
        listAddress=new ArrayList<String>();
        listNumber=new ArrayList<String>();

        listName.add("Ali Hasan");
        listName.add("Deepak Sharma");
        listAddress.add("D-60 New Ashok Nagar, New Delhi - 110096 D-60 New Ashok Nagar, New Delhi - 110096 D-60 New Ashok Nagar, New Delhi - 110096");
        listAddress.add("D-60 New Ashok Nagar, New Delhi - 110097");
        listNumber.add("9760656467");
        listNumber.add("9760656469");
        linearLayoutManager=new LinearLayoutManager(SavedAddressActivity.this,LinearLayoutManager.VERTICAL,false);
        rvAddNewAddress.setLayoutManager(linearLayoutManager);
        rvAddNewAddress.setAdapter(new SavedAddressAdapter(this,listName,listAddress,listNumber));

    }


    @Override
    protected void onResume() {
        super.onResume();
        apiGetUserAddress();
    }

    private void apiGetUserAddress() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.GETUSERADDRESSLIST);
        } else {
            Toast.makeText(this, "Please check your internet.", Toast.LENGTH_SHORT).show();
        }
    }
    @Subscribe
    public void getUserDetails(GetUserResponse response){
        progressDialog.dismiss();
        if (response.getStatus()==200&& response.getUser().get(0).getAddresses().size()!=0){
            response.getUser().get(0).getAddresses();
            linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            rvAddNewAddress.setLayoutManager(linearLayoutManager);
            rvAddNewAddress.setAdapter(new SavedAddressAdapter(this,listName,listAddress,listNumber));
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_savedaddress;
    }

    public void back(View view){
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvAddAddress:
                Intent intent=new Intent(SavedAddressActivity.this,ChangeAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.llDELEIVERHERE:
                if (Preferences.getInstance(this).getAddress().equalsIgnoreCase("billing")) {
                   /* billingAddressResponse.setFirstname(etFirstName.getText().toString());
                    billingAddressResponse.setLastname(etLastNAme.getText().toString());
                    billingAddressResponse.setCity(etCity.getText().toString());
                    billingAddressResponse.setCountryId(spinnerCoutry.getSelectedItem().toString());
                    billingAddressResponse.setTelephone(etPhoneNumber.getText().toString());
                    billingAddressResponse.setRegion(spinnerState.getSelectedItem().toString());
                    billingAddressResponse.setPostcode(etZipCode.getText().toString());
                    billingAddressResponse.setStreet(spinnerState.getSelectedItem().toString());
                    response.setBillingAddress(billingAddressResponse);
                    response.setEmail(etEmail.getText().toString());
                    response.setCurrencyId("USD");
                    response.setSaveInAddressBook(1);
                    if (Preferences.getInstance(this).getCartId().equalsIgnoreCase("")) {
                    } else {
                        response.setQuoteId(Integer.parseInt(Preferences.getInstance(this).getCartId()));
                    }
                    appUser.checkoutResponse = response;

                } else {
                    billingAddressResponse.setFirstname(etFirstName.getText().toString());
                    billingAddressResponse.setLastname(etLastNAme.getText().toString());
                    billingAddressResponse.setCity(etCity.getText().toString());
                    billingAddressResponse.setCountryId(spinnerCoutry.getSelectedItem().toString());
                    billingAddressResponse.setTelephone(etPhoneNumber.getText().toString());
                    billingAddressResponse.setRegion(spinnerState.getSelectedItem().toString());
                    billingAddressResponse.setPostcode(etZipCode.getText().toString());
                    billingAddressResponse.setStreet(spinnerState.getSelectedItem().toString());
                    response.setShippingAddress(billingAddressResponse);
                    response.setEmail(etEmail.getText().toString());
                    if (appUser != null) {
                        appUser.checkoutResponse = response;
                    }
                    response.setEmail(etEmail.getText().toString());*/
                    response.setCurrencyId("USD");
                    response.setSaveInAddressBook(1);
                    if (Preferences.getInstance(this).getCartId().equalsIgnoreCase("")) {
                    } else {
                        response.setQuoteId(Integer.parseInt(Preferences.getInstance(this).getCartId()));
                    }
                }
                LocalRepositories.saveAppUser(getApplicationContext(), appUser);
                apiShippingAdddress();
                break;
                default:break;
        }
    }
    private void apiShippingAdddress() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.ACTIONADDCHECKOUTADDRESS);
        } else {
            Toast.makeText(this, "Please check your internet.", Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe
    public void saveAddress(UserResponse userResponse) {
        progressDialog.dismiss();
        if (userResponse.getStatus() == 200) {
            Toast.makeText(this, userResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
