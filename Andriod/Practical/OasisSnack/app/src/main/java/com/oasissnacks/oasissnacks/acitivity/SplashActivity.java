package com.oasissnacks.oasissnacks.acitivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.Cv;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;
import com.oasissnacks.oasissnacks.utils.Preferences;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by suraj on 2/11/2019.
 */

public class SplashActivity extends Activity {
    long Delay = 2000;
    AppUser appUser;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        appUser = LocalRepositories.getAppUser(this);

        if (appUser == null) {
            appUser = new AppUser();
            LocalRepositories.saveAppUser(this, appUser);
        }
        appUser = LocalRepositories.getAppUser(this);
        Timer RunSplash = new Timer();
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), LandingPageActivity.class);
                startActivity(intent);
                finish();
               /* if(!Preferences.getInstance(getApplicationContext()).getLogin()) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(getApplicationContext(), LandingPageActivity.class);
                    startActivity(intent);
                    finish();

                }*/
            }

        };

        // Start the timer
        RunSplash.schedule(ShowSplash, Delay);

       /* int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion <= 22) {

            Timer RunSplash = new Timer();
            TimerTask ShowSplash = new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                    finish();
                  *//*  if (!Preferences.getInstance(getApplicationContext()).getLogin()) {
                        Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), LandingPageActivity.class);
                        startActivity(intent);
                        finish();
                    }*//*

                }

            };

            // Start the timer
            RunSplash.schedule(ShowSplash, Delay);

        } else {
            checkPermissions();
        }
    }

    private void checkPermissions() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.INTERNET,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,


            }, Cv.PERMISSIONS_BUZZ_REQUEST);
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {

            case Cv.PERMISSIONS_BUZZ_REQUEST:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Timer RunSplash = new Timer();
                    TimerTask ShowSplash = new TimerTask() {
                        @Override
                        public void run() {
                            if(Preferences.getInstance(getApplicationContext()).getLogin()){
                                Intent intent = new Intent(getApplicationContext(), LandingPageActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                    } ;
                    // Start the timer
                    RunSplash.schedule(ShowSplash, Delay);


                }else{
                    AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(SplashActivity.this);
                    myAlertDialog.setTitle(getString(R.string.msg_perms_needed));
                    myAlertDialog.setPositiveButton(getString(R.string.btn_quit), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            finish();
                        }
                    });

                    myAlertDialog.setNegativeButton(getString(R.string.btn_accept),
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface arg0, int arg1) {
                                    checkPermissions();
                                }
                            });
                    myAlertDialog.show();

                }
                break;
        }*/

    }
}
