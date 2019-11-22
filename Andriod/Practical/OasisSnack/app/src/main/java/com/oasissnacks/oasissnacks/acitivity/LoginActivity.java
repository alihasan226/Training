package com.oasissnacks.oasissnacks.acitivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;
import androidx.preference.Preference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

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
import com.oasissnacks.oasissnacks.utils.EventLogin;
import com.oasissnacks.oasissnacks.utils.Helper;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.MyProgressDialog;
import com.oasissnacks.oasissnacks.utils.Preferences;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends RegisterAbstractActivity implements View.OnClickListener {

    @BindView(R.id.et_mail)
    EditText et_email;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.tv_forgtpass)
    TextView tv_forgtpass;
    AppUser appUser;
    @BindView(R.id.cv_remember)
    CheckBox cv_remember;
    MyProgressDialog progressDialog;
    private ActionBar actionBar;
    final String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";//mail matcher for email validation
    private RequestContext requestContext;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initActionbar();
        appUser = LocalRepositories.getAppUser(this);
        appUser.login.clear();
        LocalRepositories.saveAppUser(this,appUser);
        progressDialog = new MyProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(true);
        requestContext = RequestContext.create(this);


        //Center Alignment of ActionBar Text


        //Removing Underline of Hyperlink
        String link = "<a href='https://www.google.com//'>Forgot Your Password?</a>";
        Spannable link1 = (Spannable) Html.fromHtml(link);
        for (URLSpan u : link1.getSpans(0, link1.length(), URLSpan.class))
            link1.setSpan(new UnderlineSpan() {
                public void updateDrawState(TextPaint tp) {
                    tp.setUnderlineText(false);
                }
            }, link1.getSpanStart(u), link1.getSpanEnd(u), 0);

        tv_forgtpass.setMovementMethod(LinkMovementMethod.getInstance());
        tv_forgtpass.setText(link1);
        tv_forgtpass.setOnClickListener(this);


        View loginButton = findViewById(R.id.login_with_amazon);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("LoginActivity", "Login button");
                AuthorizationManager.authorize(new AuthorizeRequest
                        .Builder(requestContext)
                        .addScopes(ProfileScope.profile(), ProfileScope.postalCode())
                        .build());
            }
        });


        //Login with Amazon
         requestContext.registerListener(new AuthorizeListener() {

         /* Authorization was completed successfully. */
            @Override
            public void onSuccess(AuthorizeResult result) {
                /* Your app is now authorized for the requested scopes */
                Log.w("LoginActivity","onSuccess");
                fetchUserProfile();
            }

            /* There was an error during the attempt to authorize the
            application. */
            @Override
            public void onError(AuthError ae) {
                /* Inform the user of the error */
                Log.w("LoginActivity","onError");
            }

            /* Authorization was cancelled before it could be completed. */
            @Override
            public void onCancel(AuthCancellation cancellation) {
                /* Reset the UI to a ready-to-login state */
                Log.w("LoginActivity","onCancel");
            }
        });


         if(Preferences.getInstance(getApplicationContext()).getChecked()){
            et_email.setText(Preferences.getInstance(LoginActivity.this).getEmail());
            et_password.setText(Preferences.getInstance(LoginActivity.this).getPassword());
            cv_remember.setChecked(true);
         }
         else{
             et_email.setText("");
             et_password.setText("");
             cv_remember.setChecked(false);
         }

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

        actionbarTitle.setText("Login");
        actionbarTitle.setTextSize(18);
        //actionbarTitle.setTypeface(TypefaceCache.get(getAssets(), 0));
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setHomeButtonEnabled(false);
        ImageView back = (ImageView) viewActionBar.findViewById(R.id.back);
        back .setVisibility(View.GONE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        requestContext.onResume();
    }

/*
    //This Navigate to the LandingPage automatically if user is login on Amazon
   /* @Override
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
                    Log.w("LoginActivity","onSuccessin onStart");
                        fetchUserProfile();
                } else {

                }
            }

            @Override
            public void onError(AuthError ae) {

            }
        });
    }*/



    @Override
    protected int layoutId() {
        return R.layout.activity_login;
    }


    @OnClick(R.id.btn_signin)
    public void buttonclick(View view) {
        if (validemail(et_email.getText().toString()) && validpassword(et_password.getText().toString())) {

            if(cv_remember.isChecked())
            {

                Preferences.getInstance(getApplicationContext()).setChecked(true);
                Map user=new HashMap();
                user.put("email",et_email.getText().toString());
                user.put("password",et_password.getText().toString());
                appUser.login.put("user",user);
                LocalRepositories.saveAppUser(this,appUser);

            }else{
                Preferences.getInstance(getApplicationContext()).setChecked(false);
                Map user=new HashMap();
                user.put("email",et_email.getText().toString());
                user.put("password",et_password.getText().toString());
                user.put("cart_id",Preferences.getInstance(this).getCartId());
                appUser.login.put("user",user);
                LocalRepositories.saveAppUser(this,appUser);
            }
            Boolean isConnected = ConnectivityReceiver.isConnected();
            if (isConnected) {
                progressDialog.show();
                ApiCallService.action(getApplicationContext(), Cv.ACTION_LOGIN);
            } else {
                Helper.alert(LoginActivity.this, "No Internet Connection", "Oasis Snacks");
            }
        }
    }

    @OnClick(R.id.btn_login)
    public void signup(View view) {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }

    public boolean validemail(String text) {
        boolean temp = true;
        if (TextUtils.isEmpty(et_email.getText())) {
            temp = false;
            et_email.setError("Required Field");
            et_email.requestFocus();
        } else if (!text.matches(emailpattern)) {
            temp = false;
            et_email.setError("Invalid Email");
            et_email.requestFocus();
        }

        return temp;
    }

    public boolean validpassword(String pass) {
        int length = pass.length();
        boolean temp = true;
        if (TextUtils.isEmpty(et_password.getText())) {
            temp = false;
            et_password.setError("Required Field");
            et_password.requestFocus();
        } else if (length < 6) {
            temp = false;
            et_password.setError("Weak Password");
            et_password.requestFocus();
        }

        return temp;
    }

    @Subscribe
    public void login(UserResponse response) {
        progressDialog.dismiss();
        if(response.getStatus()==200) {
            appUser.id=response.getUser().getId()+"";
            Preferences.getInstance(this).setUserid(response.getUser().getId());
            appUser.first_name=response.getUser().getAttributes().getFirst_name();
            appUser.last_name=response.getUser().getAttributes().getLast_name();
            appUser.email=response.getUser().getAttributes().getEmail();
            appUser.auth_token=response.getUser().getAuth_token();
            Preferences.getInstance(this).setAuthToken(response.getUser().getAuth_token());
            Preferences.getInstance(this).setCartID(response.getCart().getCartId());
            Preferences.getInstance(this).setCounter(response.getCart().getItemsCount());
            LocalRepositories.saveAppUser(this,appUser);
            Preferences.getInstance(getApplicationContext()).setLogin(true);
            startActivity(new Intent(LoginActivity.this, LandingPageActivity.class));
            finish();
        }else{
            Helper.alert(LoginActivity.this,response.getMessage(),"Error");
        }
        //Toast.makeText(getApplicationContext(), response.getToken(), Toast.LENGTH_LONG).show();
    }

    @Subscribe
    public void timeout(String msg) {
        progressDialog.dismiss();
        Helper.alert(this, msg, "Error");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_forgtpass:
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
                break;
                default:break;
        }
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateProfileData(name, email, account, zipcode);
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
        Map user=new HashMap();
        if (name.contains("")){

            user.put("first_name",name.split(" ")[0]);
            user.put("last_name",name.split(" ")[1]);
        }else {

            user.put("first_name",name);
            user.put("last_name","");
        }

        user.put("email",email);


       // user.put("cart_id",Preferences.getInstance(getApplicationContext()).getCartId());
        appUser.AMAZON.put("user",user);

        LocalRepositories.saveAppUser(getApplicationContext(),appUser);
        Boolean isConnected = ConnectivityReceiver.isConnected();
        if (isConnected) {
            progressDialog.show();
            ApiCallService.action(getApplicationContext(), Cv.AMAZONLOGIN);
        } else {
            Helper.alert(this, "No Internet Connection", "Oasis Snacks");
        }

        //Toast.makeText(getApplicationContext(),name+"\n"+email+"\n"+account+"\n"+zipcode,Toast.LENGTH_SHORT).show();
    }


    //Actionbar backbutton clickon Method


}
