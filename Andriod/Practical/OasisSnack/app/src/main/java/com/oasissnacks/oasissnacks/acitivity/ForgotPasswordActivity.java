package com.oasissnacks.oasissnacks.acitivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.app.ConnectivityReceiver;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.network.ApiCallService;
import com.oasissnacks.oasissnacks.network.Response.user.UserResponse;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.Cv;
import com.oasissnacks.oasissnacks.utils.Helper;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.MyProgressDialog;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPasswordActivity extends RegisterAbstractActivity implements View.OnClickListener {
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.btnSubmit)
    TextView btnSubmit;
    @BindView(R.id.toolbar)
    View ToolBar;
    public AppUser appUser=new AppUser();
    final String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";//mail matcher for email validation
    RelativeLayout rlCart;
    TextView tvHEading;
    public MyProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setToolBar();
        progressDialog = new MyProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);

    }

    private void setToolBar() {
        tvHEading=ToolBar.findViewById(R.id.tvHeading);
        rlCart=ToolBar.findViewById(R.id.rlCart);
        rlCart.setVisibility(View.INVISIBLE);
        tvHEading.setVisibility(View.VISIBLE);
        tvHEading.setText("Forgot Password");
        btnSubmit.setOnClickListener(this);
    }
    @Subscribe
    public void timeout(String msg) {
        progressDialog.dismiss();
        Helper.alert(this, msg, "Error");
    }
    public boolean validEmail(String text) {
        boolean temp = true;
        if (TextUtils.isEmpty(etEmail.getText())) {
            temp = false;
            etEmail.setError("Required Field");
            etEmail.requestFocus();
        } else if (!text.matches(emailpattern)) {
            temp = false;
            etEmail.setError("Invalid Email");
            etEmail.requestFocus();
        }

        return temp;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_forgot_password;
    }

    @Override
    public void onClick(View view) {
        if(validEmail(etEmail.getText().toString())){
            Map user=new HashMap();
            user.put("email",etEmail.getText().toString());

            appUser.forgotPassword.put("user",user);
            LocalRepositories.saveAppUser(this,appUser);
            Boolean isConnected = ConnectivityReceiver.isConnected();
            if (isConnected) {
                progressDialog.show();
                ApiCallService.action(getApplicationContext(), Cv.ACTIONFORGOTPASSWORD);
            } else {
                Helper.alert(this, "No Internet Connection", "Oasis Snacks");
            }
        }
    }
    @Subscribe
    public void getForgotPasswordApi(UserResponse userResponse){
        progressDialog.dismiss();
        if(userResponse.getStatus()==200){
            Toast.makeText(this,userResponse.getMessage(),Toast.LENGTH_LONG).show();
            startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
        }else {
            Toast.makeText(this,"Something went wrong.",Toast.LENGTH_LONG).show();

        }
    }
}
