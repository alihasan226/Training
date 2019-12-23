package com.example.usl.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usl.Apps.ConnectivityReceiver;
import com.example.usl.Network.ApiCallService;
import com.example.usl.Network.Response.UserResponse;
import com.example.usl.R;
import com.example.usl.Utils.AppUser;
import com.example.usl.Utils.Cv;
import com.example.usl.Utils.Helper;
import com.example.usl.Utils.LocalRepositories;
import com.example.usl.Utils.Preferences;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.toolbar)
    View toolbar;
    @BindView(R.id.etUserID)
    EditText etUserID;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btn_login;
    TextView tvHeading;
    ImageView ivBack;
    AppUser appUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btn_login.setOnClickListener(this);
        settoolbar();
        appUser = LocalRepositories.getAppUser(this);
        appUser.login.clear();
        LocalRepositories.saveAppUser(this,appUser);
    }

    private void settoolbar() {
        tvHeading=toolbar.findViewById(R.id.tvHeading);
        tvHeading.setText("Login");
        ivBack=toolbar.findViewById(R.id.ivBack);
        ivBack.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                if(validation())
                {
                    Map user=new HashMap();
                    user.put("email",etUserID.getText().toString());
                    user.put("password",etPassword.getText().toString());
                    appUser.login.put("user",user);
                    LocalRepositories.saveAppUser(this,appUser);
                    Boolean isConnected = ConnectivityReceiver.isConnected();
                    if (isConnected) {
                        ApiCallService.action(getApplicationContext(), Cv.ACTION_LOGIN);
                    } else {
                        Helper.alert(LoginActivity.this,"No Internet Connection","USL");
                    }
                }
                break;
            default:
                break;
        }

    }

    public Boolean validation(){
        if(TextUtils.isEmpty(etUserID.getText())){
            etUserID.setError("Required Field");
            etUserID.requestFocus();
            return false;
        }else if(TextUtils.isEmpty(etPassword.getText())){
            etPassword.setError("Required Field");
            etPassword.requestFocus();
            return  false;
        }
        return true;
    }

    @Subscribe
    public void login(UserResponse response){
        if (response.getStatus() == 200) {
            appUser.id = response.getData().getUser().getId() + "";
            appUser.name = response.getData().getUser().getName();
            appUser.created_by_user_id=response.getData().getUser().getCreatedByUserId();
            appUser.user_id=response.getData().getUser().getUserId();
            appUser.phone_number= (Integer) response.getData().getUser().getPhoneNumber();
            appUser.email=response.getData().getUser().getEmail().toString();
            appUser.limit=response.getData().getUser().getLimit();
            appUser.exposure= (Double) response.getData().getUser().getExposure();
            appUser.auth_token=response.getData().getUser().getAuthToken();
            Preferences.getInstance(this).setAuthToken(response.getData().getUser().getAuthToken());
            LocalRepositories.saveAppUser(this,appUser);
            startActivity(new Intent(LoginActivity.this, LandingPageActivity.class));
            finish();
        } else {
            Helper.alert(LoginActivity.this,response.getMessage(),"USL");
        }
    }

    @Subscribe
    public void timeout(String msg) {
        Helper.alert(this, msg, "USL");
    }

}
