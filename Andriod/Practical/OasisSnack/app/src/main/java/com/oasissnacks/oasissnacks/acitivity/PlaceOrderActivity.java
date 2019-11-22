package com.oasissnacks.oasissnacks.acitivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.adapter.CardDetailsAdapter;
import com.oasissnacks.oasissnacks.adapter.PaymentAdapter;
import com.oasissnacks.oasissnacks.app.ConnectivityReceiver;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.interfce.OnCardClickListener;
import com.oasissnacks.oasissnacks.interfce.OnCartItemClickListner;
import com.oasissnacks.oasissnacks.interfce.OnPaymentClickListener;
import com.oasissnacks.oasissnacks.network.ApiCallService;
import com.oasissnacks.oasissnacks.network.Response.user.UserResponse;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.CustomEditText;
import com.oasissnacks.oasissnacks.utils.Cv;
import com.oasissnacks.oasissnacks.utils.Helper;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.MyProgressDialog;
import com.oasissnacks.oasissnacks.utils.Preferences;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceOrderActivity extends RegisterAbstractActivity implements  View.OnClickListener {

    @BindView(R.id.rbAddressCheck)
    CheckBox rbAddressChecked;
    @BindView(R.id.llChangeAdress)
    LinearLayout llChangeAddress;
    @BindView(R.id.toolbar)
    View toolbar;
    @BindView(R.id.btnContinue)
    Button btnContinue;
    @BindView(R.id.rlApplyCoupon)
    RelativeLayout rlApplyCoupon;
    ImageView ivCart;
    @BindView(R.id.rvCardDetails)
    RecyclerView rvCardDetails;
    private LinearLayoutManager linearLayoutManager,cardlinearLayoutManager;
    @BindView(R.id.rvPaymentMethod)
    RecyclerView rvPaymentMethod;


    ArrayList<String> listBank,listNumber,listCard;
    ArrayList<Integer> listLogo;
    //@BindView(R.id.rbCard)
    CheckBox rbCard;
    TextView dialogCancel,dialogApply;
    EditText etCouponCode;

    @BindView(R.id.llAddress)
    LinearLayout llAddress;
    public boolean istrue=true;
    TextView tvCArt;
    @BindView(R.id.ivApplyCoupon)
    ImageView ivApplyCoupon;
    public MyProgressDialog progressDialog;
    public AppUser appUser=new AppUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placeorder);
        ButterKnife.bind(this);
        llAddress.setOnClickListener(this);
        //btnContinue.setOnClickListener(this);
        llChangeAddress.setOnClickListener(this);
        progressDialog = new MyProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);
        rlApplyCoupon.setOnClickListener(this);
        setToolBAr();

        listBank=new ArrayList<String>();
        listNumber=new ArrayList<String>();
        listLogo=new ArrayList<Integer>();
        listBank.add("Punjab National Bank Debit Card");
        listBank.add("State Bank of India Debit Card");
        listNumber.add("123456789012");
        listNumber.add("098765432112");
        listLogo.add(R.drawable.pnb_logo);
        listLogo.add(R.drawable.sbi_logo);
        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvCardDetails.setLayoutManager(linearLayoutManager);
        rvCardDetails.setNestedScrollingEnabled(false);
        rvCardDetails.setFocusable(false);
        rvCardDetails.setAdapter(new PaymentAdapter(this,listBank,listNumber,listLogo));

        listCard=new ArrayList<String>();
        listCard.add("Credit / Debit / ATM Card");
        listCard.add("Net Banking");
        listCard.add("Cash on Delivery");
        listCard.add("EMI (Easy Installments)");
        cardlinearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvPaymentMethod.setLayoutManager(cardlinearLayoutManager);
        rvPaymentMethod.setNestedScrollingEnabled(false);
        rvPaymentMethod.setFocusable(false);
        rvPaymentMethod.setAdapter(new CardDetailsAdapter(this,listCard, new OnCardClickListener() {
            @Override
            public void onCardClick(View view, int position, String ENTITITY) {
               btnContinue.setOnClickListener(PlaceOrderActivity.this);
            }
        }));
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_placeorder;
    }

    private void setToolBAr() {
        ivCart=toolbar.findViewById(R.id.ivCart);
        tvCArt=toolbar.findViewById(R.id.tvHeading);
        tvCArt.setText("Payments");
        ivCart.setVisibility(View.INVISIBLE);
    }
    public void back(View view)
    {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rlApplyCoupon:
               showdialog();
                break;
            case R.id.llAddress:
                if (!rbAddressChecked.isChecked()){
                    rbAddressChecked.setChecked(true);
                    llChangeAddress.setVisibility(View.GONE);
                }else {
                    llChangeAddress.setVisibility(View.VISIBLE);
                    rbAddressChecked.setChecked(false);
                }
                break;
            case R.id.llChangeAdress:
                Preferences.getInstance(this).setAddress("shipping");
                Intent intent = new Intent(this, SavedAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.btnContinue:
                startActivity(new Intent(PlaceOrderActivity.this,PaymentActivity.class));
                default:break;

        }
    }



    private void showdialog() {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_apply_coupon);
        dialog.setCanceledOnTouchOutside(false);
        dialogApply=dialog.findViewById(R.id.btnApply);
        dialogCancel=dialog.findViewById(R.id.btnCancel);
        etCouponCode=dialog.findViewById(R.id.etApplyCoupon);
        dialogApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                applyCouponApi(etCouponCode.getText().toString());
                dialog.dismiss();
            }
        });
        dialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    private void applyCouponApi(String toString) {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            appUser= LocalRepositories.getAppUser(getApplicationContext());
            appUser.APPLYCOUPON.put("coupon_code",toString);
            appUser.APPLYCOUPON.put("cart_id", Preferences.getInstance(this).getCartId());
            LocalRepositories.saveAppUser(getApplicationContext(),appUser);
            ApiCallService.action(getApplicationContext(), Cv.APPLYCOUPON);
        } else {
            Helper.alert(getApplicationContext(), "No Internet Connection", "Oasis Snacks");
        }
    }
    @Subscribe
    public void getCouponStatus(UserResponse response){
        progressDialog.dismiss();
        if (response.getStatus()==200){
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}
