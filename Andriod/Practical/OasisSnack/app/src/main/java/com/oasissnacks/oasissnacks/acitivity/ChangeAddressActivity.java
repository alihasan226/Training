package com.oasissnacks.oasissnacks.acitivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.app.ConnectivityReceiver;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.network.ApiCallService;
import com.oasissnacks.oasissnacks.network.Response.checkoutresponse.BillingAddressResponse;
import com.oasissnacks.oasissnacks.network.Response.checkoutresponse.CheckoutResponse;
import com.oasissnacks.oasissnacks.network.Response.countrylist.CountryListResponse;
import com.oasissnacks.oasissnacks.network.Response.countrylist.GetCountryResponse;
import com.oasissnacks.oasissnacks.network.Response.statelist.GetStateListResponse;
import com.oasissnacks.oasissnacks.network.Response.user.Address;
import com.oasissnacks.oasissnacks.network.Response.user.UserResponse;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.CustomEditText;
import com.oasissnacks.oasissnacks.utils.CustomTextView;
import com.oasissnacks.oasissnacks.utils.Cv;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.MyProgressDialog;
import com.oasissnacks.oasissnacks.utils.Preferences;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangeAddressActivity extends RegisterAbstractActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    @BindView(R.id.toolbar)
    View ToolBar;
    @BindView(R.id.et_firstname)
    CustomEditText etFirstName;
    @BindView(R.id.et_lastname)
    CustomEditText etLastNAme;
    @BindView(R.id.et_street_address)
    CustomEditText etStreetAddress;
    @BindView(R.id.et_city)
    CustomEditText etCity;
    @BindView(R.id.spinnerState)
    Spinner spinnerState;
    @BindView(R.id.et_zip_code)
    CustomEditText etZipCode;

    @BindView(R.id.et_phone)
    CustomEditText etPhoneNumber;
    @BindView(R.id.et_Email)
    CustomEditText etEmail;
    @BindView(R.id.btnSaveAddress)
    CustomTextView btnSaveAddress;
    @BindView(R.id.spinnerCountry)
    Spinner SpinnerCountry;
    RelativeLayout rlCart;
    TextView tvHeading;
    ImageView ivBack;
    public ArrayList<String>list=new ArrayList<>();
    public ArrayList<String>stateList=new ArrayList<>();
    String Title;
    AppUser appUser = new AppUser();
    CheckoutResponse response = new CheckoutResponse();
    public CountryListResponse countryListResponse;
    public MyProgressDialog progressDialog;
    BillingAddressResponse billingAddressResponse = new BillingAddressResponse();

    List<Address> addressList1 = new ArrayList<>();
    final String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_addess);
        progressDialog = new MyProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);
        ButterKnife.bind(this);
        getBundle();
        setUpToolBar();
        getCountryListAPi();
        SpinnerCountry.setOnItemSelectedListener(this);

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_change_addess;
    }

    private void getBundle() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            Title = bundle.getString("billing", "");
        }

    }

    private void setUpToolBar() {
        rlCart = ToolBar.findViewById(R.id.rlCart);
        ivBack = ToolBar.findViewById(R.id.ivBackToolBar);
        tvHeading = ToolBar.findViewById(R.id.tvHeading);
        ivBack.setOnClickListener(this);
        btnSaveAddress.setOnClickListener(this);

        rlCart.setVisibility(View.INVISIBLE);
        tvHeading.setText(Title);


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSaveAddress:
                if (validation()) {
                    if (Preferences.getInstance(this).getAddress().equalsIgnoreCase("billing")) {
                        billingAddressResponse.setFirstname(etFirstName.getText().toString());
                        billingAddressResponse.setLastname(etLastNAme.getText().toString());
                        billingAddressResponse.setCity(etCity.getText().toString());
                        billingAddressResponse.setCountryId(SpinnerCountry.getSelectedItem().toString());
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
                        billingAddressResponse.setCountryId(SpinnerCountry.getSelectedItem().toString());
                        billingAddressResponse.setTelephone(etPhoneNumber.getText().toString());
                        billingAddressResponse.setRegion(spinnerState.getSelectedItem().toString());
                        billingAddressResponse.setPostcode(etZipCode.getText().toString());
                        billingAddressResponse.setStreet(spinnerState.getSelectedItem().toString());
                        response.setShippingAddress(billingAddressResponse);
                        response.setEmail(etEmail.getText().toString());
                        if (appUser != null) {
                            appUser.checkoutResponse = response;
                        }
                        response.setEmail(etEmail.getText().toString());
                        response.setCurrencyId("USD");
                        response.setSaveInAddressBook(1);
                        if (Preferences.getInstance(this).getCartId().equalsIgnoreCase("")) {
                        } else {
                            response.setQuoteId(Integer.parseInt(Preferences.getInstance(this).getCartId()));
                        }
                    }
                    LocalRepositories.saveAppUser(getApplicationContext(), appUser);
                    apiShippingAdddress();

                }
                break;
            case R.id.ivBackToolBar:
                super.onBackPressed();
                break;
            default:
                break;


        }
    }
    private void getStateListApi() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.GETSTATELIST);
        } else {
            Toast.makeText(this, "Please check your internet.", Toast.LENGTH_SHORT).show();
        }
    }
    private void getCountryListAPi() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.GETCOUNTRYLIST);
        } else {
            Toast.makeText(this, "Please check your internet.", Toast.LENGTH_SHORT).show();
        }
    }
    @Subscribe
    public void CountryListResponse(GetCountryResponse response){
        progressDialog.dismiss();
        countryListResponse=new CountryListResponse();
        countryListResponse=response.getData();
        if (response.getStatus()==200&& response.getData().getCountries().size()>0){
            for (int i=0;i<response.getData().getCountries().size();i++){
                list.add(response.getData().getCountries().get(i).getLabel());
            }
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_item,
                            list);
            SpinnerCountry.setAdapter(spinnerArrayAdapter);
        }


    }

    @Subscribe
    public void  getStateList(GetStateListResponse response){
        progressDialog.dismiss();
        if (response.getStatus()==200 && response.getData().getStates().size()>0){
            for (int i=0;i<response.getData().getStates().size();i++){
                stateList.add(response.getData().getStates().get(i).getLabel());
            }
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_item,
                            stateList);
            spinnerState.setAdapter(spinnerArrayAdapter);
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
            super.onBackPressed();
        }

    }

    public void back(View vIew) {
        finish();
    }


    public boolean validation() {

        if (TextUtils.isEmpty(etFirstName.getText())) {
            etFirstName.setError("Required Field");
            etFirstName.requestFocus();
            return false;
        } else if (TextUtils.isDigitsOnly(etFirstName.getText())) {
            etFirstName.setError("Only Digit Not Allowed");
            etFirstName.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etLastNAme.getText())) {
            etLastNAme.setError("Required Field");
            etLastNAme.requestFocus();
            return false;
        } else if (TextUtils.isDigitsOnly(etLastNAme.getText())) {
            etLastNAme.setError("Only Digit Not Allowed");
            etLastNAme.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etEmail.getText())) {
            etEmail.setError("Required Field");
            etEmail.requestFocus();
            return false;
        } else if (!etEmail.getText().toString().matches(emailpattern)) {
            etEmail.setError("Invalid Email");
            etEmail.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etStreetAddress.getText())) {
            etStreetAddress.setError("Required Field");
            etStreetAddress.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etCity.getText())) {
            etCity.setError("Required Field");
            etCity.requestFocus();
            return false;
        } else if (TextUtils.isDigitsOnly(etCity.getText())) {
            etCity.setError("Only Digit not Allowed");
            etCity.requestFocus();
            return false;
        } else if (SpinnerCountry.getSelectedItem().toString().equals("Please Select Country")) {
            Toast.makeText(this, "Please Select Country", Toast.LENGTH_SHORT).show();
            spinnerState.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etZipCode.getText())) {
            etZipCode.setError("Required Field");
            etZipCode.requestFocus();
            return false;
        } else if (etZipCode.getText().toString().length() < 6) {
            etZipCode.setError("Invalid Zip Code");
            etZipCode.requestFocus();
            return false;
        } else if (spinnerState.getSelectedItem().toString().equals("Please Select State")) {
            Toast.makeText(this, "Please Select State", Toast.LENGTH_SHORT).show();
            SpinnerCountry.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etPhoneNumber.getText())) {
            etPhoneNumber.setError("Required Field");
            etPhoneNumber.requestFocus();
            return false;
        } else if (etPhoneNumber.getText().toString().length() < 10) {
            etPhoneNumber.setError("Invalid Phone Number");
            etPhoneNumber.requestFocus();
            return false;
        }

        return true;

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (countryListResponse!=null&&i!=0){

            Preferences.getInstance(ChangeAddressActivity.this).setCounty(countryListResponse.getCountries().get(i).getValue());
            getStateListApi();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
