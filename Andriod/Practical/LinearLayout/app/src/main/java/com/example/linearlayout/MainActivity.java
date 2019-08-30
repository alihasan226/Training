package com.example.linearlayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    private ActionBar actionBar;
    protected Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar=getSupportActionBar();//get the instance of the ActionBar
        actionBar.setTitle("Example of Linear Layout");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#D81B60")));
    }
}
