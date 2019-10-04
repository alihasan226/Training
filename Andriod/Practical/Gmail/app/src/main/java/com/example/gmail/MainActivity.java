package com.example.gmail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.internal.ImageRequest;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import static android.provider.ContactsContract.Intents.Insert.EMAIL;

public class MainActivity extends AppCompatActivity {

    public GoogleApiClient mGoogleSignInClient;
    private Button button,loginButton,forgot_password;
    public final int RC_SIGN_IN=1;
    public final String TAG="MAIN_ACTIVITY";
    public FirebaseAuth mAuth;
    private EditText et_mail;
    private EditText et_pass;
    private Button btn_submit,btn_create;
    private CallbackManager mCallbackManager;


    FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_mail=findViewById(R.id.et_mail);
        et_pass=findViewById(R.id.et_pass);
        btn_submit=findViewById(R.id.btn_submit);
        btn_create=findViewById(R.id.btn_create);
        loginButton = findViewById(R.id.login_button);
        forgot_password=findViewById(R.id.btn_forgot);


        mAuth=FirebaseAuth.getInstance();//here in onCreate method the Firebase instance is shared


        button=findViewById(R.id.gmail_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GooglesignIn();
            }
        });




        /*FaceBook Integration
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mCallbackManager = CallbackManager.Factory.create();
                loginButton.setReadPermissions("email", "public_profile");
                loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d(TAG, "Facebook : onSuccess:" + loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Log.d(TAG, "facebook:onCancel");
                        // ...
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d(TAG, "facebook:onError", error);
                        //printKeyHash(MainActivity.this);  for generating the Hash Key
                        // ...
                    }
                });
            }
        });
        */
        mCallbackManager=CallbackManager.Factory.create();
        final Button loginButton=findViewById(R.id.login_button);
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "Facebook : onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
                //printKeyHash(MainActivity.this);  for generating the Hash Key
                // ...
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager
                        .getInstance()
                        .logInWithReadPermissions(
                                MainActivity.this, Collections.singletonList(EMAIL)
                        );
            }
        });


        /*for (int i = 0; i < button.getChildCount(); i++) {
            View v = button.getChildAt(i);

            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setPadding(0, 0, 20, 0);
                return;
            }//to set the text of buttom in the center
        }*/





        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                startActivity(new Intent(MainActivity.this,Second.class));
            }
        };



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();



        mGoogleSignInClient=new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(MainActivity.this,"Wrong",Toast.LENGTH_LONG).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

//The GoogleAPI client provides a common entry point to google Play services and manages the network connection between the user's dvice and each Google Service


        //Adding Notification Channel used for Sending Notification in Higher version of API Level 26
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel channel=new NotificationChannel("MyNotification","MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        FirebaseMessaging.getInstance().subscribeToTopic("General")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "";
                        if (!task.isSuccessful()) {
                            msg="Fail";
                            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        } else {
                            msg="Successfull";
                            //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    }
                });



        //Facebook LogIn Integration









    }//End of onCreate()


    /*
    //Generating HashKey
    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            //getting application package name, as defined in manifest
            String packageName = context.getApplicationContext().getPackageName();

            //Retriving package info
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);

            Log.e("Package Name=", context.getApplicationContext().getPackageName());

            for (Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));

                // String key = new String(Base64.encodeBytes(md.digest()));
                Log.d("Key Hash=", key);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("Name not found", e1.toString());
        }
        catch (NoSuchAlgorithmException e) {
            Log.e("No such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("Exception", e.toString());
        }

        return key;
    }

     */


//Facebook Token => EAAFi1TauXZAUBAE7nGDh0zljMLiLPIh3fFF0ijuBRG4ZBmj3pfrVZBAMJYQnaSYfnZA2ORVIlGkkve6Q4tvGZAQMtAIdDPHQoE3LphN06gHE6ZATbuGvIoxYiTnZCHBzd6HQSiS0xL3JluxZC6H9EGqqQZCZCbmLZA8m7AsvYIoLNNN8qYSbEK2agcWXZC3jt64o9ZBfFKd2pyYMi0eyrkMpREfptwDt0FaZBuqq8ZD
    //FacaBook Integration Code
    private void handleFacebookAccessToken(final AccessToken token) {
        Log.w(TAG, "handleFacebookAccessToken:" + token.getToken());
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            /*Log.w(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String name=user.getEmail();
                            String name1=user.getDisplayName();
                            Toast.makeText(MainActivity.this,"Welcome\n"+name+"\n"+name1,Toast.LENGTH_SHORT).show();
                            updateUI(user);
                            */

                            GraphRequest request = GraphRequest.newMeRequest(
                                    token.getCurrentAccessToken(),
                                    new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(
                                                JSONObject object,
                                                GraphResponse response) {


                                            try {
                                                Log.w(TAG, response.toString());

                                                String id = response.getJSONObject().getString("id");
                                                String name = response.getJSONObject().getString("name");
                                                String email=response.getJSONObject().getString("email");

                                                Toast.makeText(MainActivity.this,id+"\n"+name+"\n"+email,Toast.LENGTH_LONG).show();
                                                /*et_mail.setText(name);
                                                et_pass.setText(email);

                                                 */
                                                //TODO put your code here
                                            } catch (JSONException e) {
                                                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                            }
                                            // Application code
                                        }
                                    });
                            Bundle parameters = new Bundle();
                            parameters.putString("fields", "id,name,email");
                            request.setParameters(parameters);
                            request.executeAsync();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication__Failed",Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }



    public void clickIt(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_submit:
                usermail();
                break;
            case R.id.btn_create:
                CreateNew();
                break;
            case R.id.btn_forgot:
                forgot_password();
                break;
        }
    }


    public void forgot_password()
    {
        startActivity(new Intent(MainActivity.this,Forgot_Password.class));
    }

    @Override
    protected void onStart() {
        super.onStart();


            // Check if user is signed in (non-null) and update UI accordingly.
           FirebaseUser currentUser = mAuth.getCurrentUser();
            if(currentUser!=null) {
                updateUI();
            }

    }


    public void updateUI()
    {
        //Toast.makeText(getApplicationContext(),"You Are Login",Toast.LENGTH_SHORT).show();
    }


    private void usermail()
    {
        String email=et_mail.getText().toString();
        String password=et_pass.getText().toString();

        if(TextUtils.isEmpty(et_mail.getText()))
        {
            et_mail.setError("Required Field");
            et_mail.requestFocus();
        }
        else if(TextUtils.isEmpty(et_pass.getText()))
        {
            et_pass.setError("Required Field");
            et_pass.requestFocus();
        }
        else
        {
            //Sign with Email and Password
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                Toast.makeText(MainActivity.this, "Successfully Authenticated.", Toast.LENGTH_SHORT).show();
                                //FirebaseUser user = mAuth.getCurrentUser();
                                //updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                            // ...
                        }
                    });

        }

    }



    public void CreateNew()
    {
        startActivity(new Intent(MainActivity.this,Third.class));
    }


    private void GooglesignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleSignInClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // ...
            }
        }

        mCallbackManager.onActivityResult(requestCode, resultCode, data);


    }



    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(),"Authentication Successfull",Toast.LENGTH_SHORT).show();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this,"Authentication Failed",Toast.LENGTH_SHORT).show();
                            //Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }

                });
    }


}




/*

keytool -exportcert -alias androiddebugkey -keystore C:\Users\Android\.android\debug.keystore | "PATH_TO_OPENSSL_LIBRARY\bin\openssl sha1 -binary | "PATH_TO_OPENSSL_LIBRARY\bin\openssl" base64



        keytool -exportcert -alias androiddebugkey -keystore C:\Users\Android\.android\debug.keystore | C:\openssl-0.9.8k_X64\bin\openssl sha1 -binary | C:\openssl-0.9.8k_X64\bin\openssl base64



keytool -exportcert -alias androiddebugkey -keystore C:\Users\Android\.android\debug.keystore | C:\openssl-0.9.8k_X64\bin\openssl sha1 -binary | "C:\openssl-0.9.8k_X64\bin\openssl base64



*/