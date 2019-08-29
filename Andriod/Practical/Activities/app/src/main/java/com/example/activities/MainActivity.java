package com.example.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    String msg="Android : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(msg,"The onCreate() event");//this method is used to log debug message
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d(msg,"The onStart() event");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d(msg,"The onResume() event");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d(msg,"The OnPause() event");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d(msg,"The onStop() event");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(msg,"The onDestroy() event");
    }
}
