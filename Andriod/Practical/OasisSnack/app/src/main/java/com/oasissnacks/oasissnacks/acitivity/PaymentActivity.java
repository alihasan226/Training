package com.oasissnacks.oasissnacks.acitivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.utils.CustomEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
public class PaymentActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.toolbar)
    View toolbar;
    @BindView(R.id.llPlaceOrder)
    LinearLayout llPlaceOrder;
    @BindView(R.id.etCard)
    CustomEditText etCardNumber;
    @BindView(R.id.etmounth)
    CustomEditText etCardMonth;
    @BindView(R.id.etYear)
    CustomEditText etCardYear;
    @BindView(R.id.etCVV)
    CustomEditText etCVV;
    ImageView ivCart;
    TextView tvHeadding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);

        setToolBAr();

    }

    private void setToolBAr() {
        tvHeadding=toolbar.findViewById(R.id.tvHeading);
        tvHeadding.setText("Payments");
        llPlaceOrder.setOnClickListener(this);
        ivCart=toolbar.findViewById(R.id.ivCart);
        ivCart.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.llPlaceOrder:
                if(validation()) {
                    Toast.makeText(this, "Working", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    public boolean validation()
    {
        if(TextUtils.isEmpty(etCardNumber.getText())){
            etCardNumber.setError("Card Number Required");
            etCardNumber.requestFocus();
            return false;
        }
        else if(TextUtils.isEmpty(etCardMonth.getText())){
            etCardMonth.setError("Month Required");
            etCardMonth.requestFocus();
            return  false;
        }else if(TextUtils.isEmpty(etCardYear.getText())){
            etCardYear.setError("Year Required");
            etCardYear.requestFocus();
            return  false;
        }else if(TextUtils.isEmpty(etCVV.getText())){
            etCVV.setError("CVV Required");
            etCVV.requestFocus();
            return  false;
        }
        return true;
    }


    public void back(View view) {
        finish();
    }
}
