package com.example.drawable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Example of Nine patch Image");

    }
}
