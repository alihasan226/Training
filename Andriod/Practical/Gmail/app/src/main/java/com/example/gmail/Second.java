package com.example.gmail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Second  extends AppCompatActivity {

    private TextView tv_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);


        tv_email=findViewById(R.id.second_textview);
        Intent intent=getIntent();
        String new_user=intent.getSerializableExtra("userId").toString();
        tv_email.setText(new_user);
    }
}
