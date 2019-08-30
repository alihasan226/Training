package com.example.androidservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    String msg="Android : ";
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar=getSupportActionBar();//GETTING THE INSTANCE OF ACTIONBAR
        actionBar.setTitle("Service");//SETTING THE TITLE OF ACTIONBAR
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#DD0D5238")));//CHANGINF THE COLOR OF ACTIONBAR
        Log.d(msg,"The onCreate() event");
    }

    public void startService(View view)
    {
        startService(new Intent(getBaseContext(),MyService.class));
    }

    public void stopService(View view)
    {
        stopService(new Intent(getBaseContext(),MyService.class));
    }


}
