package com.example.relativelayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    protected ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar=getSupportActionBar();//to get the instance of the action bar
        actionBar.setTitle("Example of the RelativeLayout");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));
    }
}
