package com.oasissnacks.oasissnacks.acitivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.app.ConnectivityReceiver;
import com.oasissnacks.oasissnacks.app.RegisterAbstractActivity;
import com.oasissnacks.oasissnacks.network.ApiCallService;
import com.oasissnacks.oasissnacks.network.Response.user.UserResponse;
import com.oasissnacks.oasissnacks.network.userresponse.GetUserResponse;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.Cv;
import com.oasissnacks.oasissnacks.utils.Helper;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.MyProgressDialog;

import org.greenrobot.eventbus.Subscribe;

import java.util.Date;
import java.util.HashMap;


import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfileActivity extends RegisterAbstractActivity implements View.OnClickListener {
    @BindView(R.id.toolbar)
    View ToolBar;
    RelativeLayout rlCart;
    TextView tvHEading;
    @BindView(R.id.btnUpdate)
    TextView btnUpdate;
    @BindView(R.id.ivEdit)
    ImageView ivEdit;
    @BindView(R.id.etNewPassword)
    EditText etNewPasswrod;
    @BindView(R.id.etConfirmPassword)
    EditText etConfirmPassword;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etFirstName)
    EditText etFirstName;
    @BindView(R.id.etLastName)
    EditText etLastName;
    @BindView(R.id.etEmail)
    EditText etEmail;

    @BindView(R.id.tv_NewPassword)
    TextView tvNewPassword;
    @BindView(R.id.tv_ConfirmPassword)
    TextView tvConfirmPassword;
    public AppUser appUser = new AppUser();

    boolean isVisible = true;
    public MyProgressDialog progressDialog;
    public boolean editProfile = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        progressDialog = new MyProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);
        setToolBar();
        getUSerAPi();
    }

    private void setToolBar() {
        tvHEading = ToolBar.findViewById(R.id.tvHeading);
        rlCart = ToolBar.findViewById(R.id.rlCart);
        rlCart.setVisibility(View.INVISIBLE);
        tvHEading.setVisibility(View.VISIBLE);
        tvHEading.setText("Edit Profile");
        btnUpdate.setOnClickListener(this);
        ivEdit.setOnClickListener(this);

    }


    public void getUSerAPi() {
        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(this, Cv.ACTIONGETDETAILS);
        } else {
            Helper.alert(getApplicationContext(), "No Internet Connection", "Oasis Snacks");

        }

    }

    public void back(View vIew) {
        finish();
    }


    @Subscribe
    public void getUserResponse(GetUserResponse response) {
        progressDialog.dismiss();
        if (response.getStatus() == 200) {
            if (editProfile) {
                Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
            } else {
                etEmail.setText(response.getUser().get(0).getEmail());
                etFirstName.setText(response.getUser().get(0).getFirstname());
                etLastName.setText(response.getUser().get(0).getLastname());
                appUser.userId = response.getUser().get(0).getId().toString();

            }

        } else {
            Toast.makeText(this, "Something went wrong.", Toast.LENGTH_SHORT).show();
        }

    }

    @Subscribe
    public void saveUSerProfile(UserResponse response) {
        progressDialog.dismiss();
        if (response.getStatus() == 200) {
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        } else
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void timeout(String msg) {
        progressDialog.dismiss();
        Helper.alert(this, msg, "Error");
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_profile;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnUpdate:
                if (isVisible) {
                    editProfile = true;
                    editProfile();
                } else {

                    if (checkValidation()) {
                        editProfile = true;
                        editProfile();
                    }
                }

                break;
            case R.id.ivEdit:
                if (isVisible) {
                    tvNewPassword.setVisibility(View.VISIBLE);
                    tvConfirmPassword.setVisibility(View.VISIBLE);

                    etNewPasswrod.setVisibility(View.VISIBLE);
                    etConfirmPassword.setVisibility(View.VISIBLE);
                    etPassword.setText("");
                    etPassword.setHint("Old Password");
                    isVisible = false;
                } else {
                    tvNewPassword.setVisibility(View.GONE);
                    tvConfirmPassword.setVisibility(View.GONE);

                    etNewPasswrod.setVisibility(View.GONE);
                    etConfirmPassword.setVisibility(View.GONE);
                    etPassword.setText("abc");
                    etPassword.setHint(" Password");
                    isVisible = true;
                }

                break;
            default:
                break;
        }


    }

    private boolean checkValidation() {

        if (etPassword.getText().toString().equalsIgnoreCase("")) {
            etPassword.setError("Please enter password.");
            return false;
        } else if (etNewPasswrod.getText().toString().equalsIgnoreCase("")) {
            etNewPasswrod.setError("Please enter new password.");
            return false;
        } else if (etConfirmPassword.getText().toString().equalsIgnoreCase("")) {
            etConfirmPassword.setError("Please enter confirm password.");
            return false;
        } else if (!etConfirmPassword.getText().toString().equalsIgnoreCase(etNewPasswrod.getText().toString())) {
            etConfirmPassword.setError("Confirm Password does not match to password.");
            return false;
        }
        return true;

    }

    public void editProfile() {
        HashMap<String, String> hashMap = new HashMap<>();
        LocalRepositories.getAppUser(getApplicationContext());
        hashMap.put("user_id", appUser.userId);
        hashMap.put("first_name", etFirstName.getText().toString());
        hashMap.put("last_name", etLastName.getText().toString());
        hashMap.put("email", etEmail.getText().toString());
        if (!isVisible){
            appUser.EDITPROFILE.put("current_password", etPassword.getText().toString());
            appUser.EDITPROFILE.put("new_password", etConfirmPassword.getText().toString());
        }

        appUser.EDITPROFILE.put("user", hashMap);

        LocalRepositories.saveAppUser(getApplicationContext(), appUser);

        if (ConnectivityReceiver.isConnected()) {
            progressDialog.show();
            ApiCallService.action(this, Cv.ACTIONSAVEPROFILE);
        } else {
            Helper.alert(getApplicationContext(), "No Internet Connection", "Oasis Snacks");

        }

    }

}
