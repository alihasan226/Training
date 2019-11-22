package com.oasissnacks.oasissnacks.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.utils.CustomEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillingActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    View ToolBar;
    @BindView(R.id.et_firstname)
    CustomEditText etFirstName;
    @BindView(R.id.et_phone)
    CustomEditText etPhone;
    @BindView(R.id.et_street_address)
    CustomEditText etStreetAddress;
    /*
    @BindView(R.id.rbDebitCard)
    RadioButton rbWallet;
    */
    TextView tvHeading;
    RelativeLayout rlRoot;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_page);
        ButterKnife.bind(this);
        setUpToolBAr();
        getAddress();
    }

    public void placeOrderClick(View view) {
        startActivity(new Intent(BillingActivity.this, LandingPageActivity.class));
    }

    private void setUpToolBAr() {
        tvHeading = ToolBar.findViewById(R.id.tvHeading);
        rlRoot = ToolBar.findViewById(R.id.rlCart);
        rlRoot.setVisibility(View.INVISIBLE);
        tvHeading.setText("Billing ");
    }

    private void getAddress()
    {
        Bundle bundle=new Bundle();
        etFirstName.setText(bundle.getString("UserName"));
        etStreetAddress.setText(bundle.getString("UserAddress"));
        etPhone.setText(bundle.getString("UserNumber"));
    }

    public void back(View view) {
        finish();
    }
}













