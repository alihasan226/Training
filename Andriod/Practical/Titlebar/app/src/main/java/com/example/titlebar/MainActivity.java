package com.example.titlebar;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//bundle is used to pass the data between the activities
        super.onCreate(savedInstanceState);

        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();*/

        /*
        Android system initiate its program with in an activity statrting with a call on onCreate() method when you
        initiate activity here you usually call setContentView() method with layout resources
        findViewbyId(int) is used ot receive the UI that you need to interact with programming.
         */
        setContentView(R.layout.activity_main);
        actionbar=getSupportActionBar();//using this we can reterive the Actionbar instance
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#C5B115")));
        actionbar.setTitle("Ali Hasan");//by using this we can set the text in Actionbar

        Toast.makeText(MainActivity.this,"Ali",Toast.LENGTH_LONG).show();
    }
}
