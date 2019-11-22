package com.oasissnacks.oasissnacks.acitivity;

import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.device.api.Listener;
import com.amazon.identity.auth.device.api.authorization.AuthCancellation;
import com.amazon.identity.auth.device.api.authorization.AuthorizationManager;
import com.amazon.identity.auth.device.api.authorization.AuthorizeListener;
import com.amazon.identity.auth.device.api.authorization.AuthorizeRequest;
import com.amazon.identity.auth.device.api.authorization.AuthorizeResult;
import com.amazon.identity.auth.device.api.authorization.ProfileScope;
import com.amazon.identity.auth.device.api.authorization.Scope;
import com.amazon.identity.auth.device.api.authorization.User;
import com.amazon.identity.auth.device.api.workflow.RequestContext;
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
import com.oasissnacks.oasissnacks.utils.Preferences;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends RegisterAbstractActivity {

    private ActionBar actionBar;
    @BindView(R.id.et_firstname)
    EditText et_firstname; //firstname
    @BindView(R.id.et_lastname)
    EditText et_lastname; //lastname
    @BindView(R.id.et_email)
    EditText et_email; //email
    @BindView(R.id.et_password)
    EditText et_password; //password
    @BindView(R.id.et_confirmpassword)
    EditText et_confirmpassword; //confirm password
    AppUser appUser;
    MyProgressDialog progressDialog;
    private RequestContext requestContext;


    final String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initActionbar();
        appUser = LocalRepositories.getAppUser(this);
        appUser.signup.clear();
        LocalRepositories.saveAppUser(this,appUser);
        progressDialog = new MyProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);



        //SignUp with Amazon
        View loginButton = findViewById(R.id.login_with_amazon);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthorizationManager.authorize(new AuthorizeRequest
                        .Builder(requestContext)
                        .addScopes(ProfileScope.profile(), ProfileScope.postalCode())
                        .build());
            }
        });


        //Login with Amazon
        requestContext = RequestContext.create(this);

        requestContext.registerListener(new AuthorizeListener() {

            /* Authorization was completed successfully. */
            @Override
            public void onSuccess(AuthorizeResult result) {
                fetchUserProfile();
                /* Your app is now authorized for the requested scopes */
            }

            /* There was an error during the attempt to authorize the
            application. */
            @Override
            public void onError(AuthError ae) {
                /* Inform the user of the error */
            }

            /* Authorization was cancelled before it could be completed. */
            @Override
            public void onCancel(AuthCancellation cancellation) {
                /* Reset the UI to a ready-to-login state */
            }
        });


    }
    private void initActionbar() {
        ActionBar actionBar = getSupportActionBar();
        View viewActionBar = getLayoutInflater().inflate(R.layout.action_bar_tittle_text_layout, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER);

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(viewActionBar, params);
        TextView actionbarTitle = (TextView) viewActionBar.findViewById(R.id.actionbar_textview);

        actionbarTitle.setText("Sign Up");
        actionbarTitle.setTextSize(18);
        //actionbarTitle.setTypeface(TypefaceCache.get(getAssets(), 0));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        ImageView back = (ImageView) viewActionBar.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //SignUp with Amazon
    @Override
    protected void onResume() {
        super.onResume();
        requestContext.onResume();
    }


    @Override
    protected void onStart() {
        super.onStart();
        Scope[] scopes = {
                ProfileScope.profile(),
                ProfileScope.postalCode()
        };
        AuthorizationManager.getToken(this, scopes, new Listener< AuthorizeResult, AuthError >() {

            @Override
            public void onSuccess(AuthorizeResult result) {
                if (result.getAccessToken() != null) {
                    //Toast.makeText(getApplicationContext(),"Successfully Login",Toast.LENGTH_LONG).show();//can't Toast here because it is call by working thread
                } else {
                     ///The user is not signed in
                }
            }
            @Override
            public void onError(AuthError ae) {
                // The user is not signed in
            }
        });
    }





    //Amazon fetching user Details
    private void fetchUserProfile() {
        User.fetch(this, new Listener<User, AuthError>() {

            /* fetch completed successfully. */
            @Override
            public void onSuccess(User user) {
                Log.w("LoginActivity","Fetch Details");
                final String name =
                        user.getUserName();
                final String email = user.getUserEmail();
                final String account = user.getUserId();
                final String zipcode = user.getUserPostalCode();
                updateProfileData(name, email, account, zipcode);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.w("LoginActivity","Fetch Details in run");
                    }
                });
            }


            /* There was an error during the attempt to get the profile. */
            @Override
            public void onError(AuthError ae) {
                /* Retry or inform the user of the error */
            }
        });
    }

    private void updateProfileData(String name, String email, String account, String zipcode) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Map user=new HashMap();
                if (name.contains("")){
                    et_firstname.setText(name.split(" ")[0]);
                    et_lastname.setText(name.split(" ")[1]);
                    user.put("first_name",name.split(" ")[0]);
                    user.put("last_name",name.split(" ")[1]);
                }else {
                    et_email.setText(email);
                    et_firstname.setText(name);
                    user.put("first_name",name);
                    user.put("last_name","");
                }

                user.put("email",email);


                //user.put("cart_id",Preferences.getInstance(getApplicationContext()).getCartId());
                appUser.AMAZON.put("user",user);

                LocalRepositories.saveAppUser(getApplicationContext(),appUser);
                Boolean isConnected = ConnectivityReceiver.isConnected();
                if (isConnected) {
                    progressDialog.show();
                    ApiCallService.action(getApplicationContext(), Cv.AMAZONLOGIN);
                } else {
                    Helper.alert(SignUpActivity.this, "No Internet Connection", "Oasis Snacks");
                }


            }
        });



    }




    //Actionbar backbutton clickon Method
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    
    
    
    @Override
    protected int layoutId() {
        return R.layout.activity_signup;
    }


    @OnClick(R.id.btn_signin)
    public void buttonclick(View view)
    {
        if(firstname() && lastname() && email(et_email.getText().toString()) && password(et_password.getText().toString()) && confirmpass(et_password.getText().toString(),et_confirmpassword.getText().toString()))
        {
            Map user=new HashMap();
            user.put("email",et_email.getText().toString());
            user.put("first_name",et_firstname.getText().toString());
            user.put("last_name",et_lastname.getText().toString());
            user.put("password",et_password.getText().toString());
            user.put("cart_id",Preferences.getInstance(this).getCartId());
            appUser.signup.put("user",user);

            LocalRepositories.saveAppUser(this,appUser);
            Boolean isConnected = ConnectivityReceiver.isConnected();
            if (isConnected) {
                progressDialog.show();
                ApiCallService.action(getApplicationContext(), Cv.ACTION_SIGNUP);
            } else {
                Helper.alert(SignUpActivity.this, "No Internet Connection", "Oasis Snacks");
            }
        }
    }

    public boolean firstname()
    {
        boolean temp=true;
        if(TextUtils.isEmpty(et_firstname.getText()))
        {
            temp=false;
            et_firstname.setError("Required Field");
            et_firstname.requestFocus();
        }
        if(TextUtils.isDigitsOnly(et_firstname.getText()))
        {
            temp=false;
            et_firstname.setError("Only Digit not allowed");
            et_firstname.requestFocus();
        }
        return temp;
    }

    public boolean lastname()
    {
        boolean temp=true;
        if(TextUtils.isEmpty(et_lastname.getText()))
        {
            temp=false;
            et_lastname.setError("Required Field");
            et_lastname.requestFocus();
        }
        if(TextUtils.isDigitsOnly(et_lastname.getText()))
        {
            temp=false;
            et_lastname.setError("Only Digit not allowed");
            et_lastname.requestFocus();
        }
        return temp;
    }

    public boolean email(String text)
    {
        boolean temp=true;
        if(TextUtils.isEmpty(et_email.getText()))
        {
            temp=false;
            et_email.setError("Required Field");
            et_email.requestFocus();
        }
        else if(!text.matches(emailpattern))
        {
            temp=false;
            et_email.setError("Invalid Email");
            et_email.requestFocus();
        }

        return temp;
    }

    public boolean password(String pass)
    {
        int length=pass.length();
        boolean temp=true;
        if(TextUtils.isEmpty(et_password.getText()))
        {
            temp=false;
            et_password.setError("Required Field");
            et_password.requestFocus();
        }
        else if(length<6)
        {
            temp=false;
            et_password.setError("Weak Password");
            et_password.requestFocus();
        }
        return temp;
    }

    public boolean confirmpass(String pass,String confirm)
    {
        int length=confirm.length();
        boolean temp=true;
        if(TextUtils.isEmpty(et_confirmpassword.getText()))
        {
            temp=false;
            et_confirmpassword.setError("Required Field");
            et_confirmpassword.requestFocus();
        }
        else if(length<6)
        {
            temp=false;
            et_confirmpassword.setError("Weak Password");
            et_confirmpassword.requestFocus();
        }
        else if(!pass.equals(confirm))
        {
            temp=false;
            et_confirmpassword.setError("Confirm Password not matches to Password");
            et_confirmpassword.requestFocus();
        }
        return temp;
    }


    @Subscribe
    public void signup(UserResponse response) {
        progressDialog.dismiss();
        if (response.getStatus() == 200) {
            appUser.id = response.getUser().getId() + "";
            appUser.first_name = response.getUser().getAttributes().getFirst_name();
            appUser.last_name = response.getUser().getAttributes().getLast_name();
            appUser.email = response.getUser().getAttributes().getEmail();
            appUser.auth_token = response.getUser().getAuth_token();
            Preferences.getInstance(this).setUserid(response.getUser().getId());
            Preferences.getInstance(this).setAuthToken(response.getUser().getAuth_token());
            LocalRepositories.saveAppUser(this,appUser);
            Preferences.getInstance(this).setCartID(response.getCart().getCartId());
            Preferences.getInstance(this).setCounter(response.getCart().getItemsCount());
            Preferences.getInstance(getApplicationContext()).setLogin(true);
            startActivity(new Intent(getApplicationContext(), LandingPageActivity.class));
            finish();
        } else {
            Helper.alert(SignUpActivity.this, response.getMessage(), "Error");
        }
    }



    @Subscribe
    public void timeout(String msg) {
        progressDialog.dismiss();
        Helper.alert(this, msg,"Error");
    }

}
