package com.example.usl;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        btn_login.setOnClickListener(this);
        settoolbar();
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
                    startActivity(new Intent(LoginActivity.this,LandingPageActivity.class));
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
        }else if(TextUtils.isDigitsOnly(etUserID.getText())){
            etUserID.setError("Invalid UserID");
            etUserID.requestFocus();
            return  false;
        }else if(TextUtils.isEmpty(etPassword.getText())){
            etPassword.setError("Required Field");
            etPassword.requestFocus();
            return  false;
        }
        return true;
    }
}
