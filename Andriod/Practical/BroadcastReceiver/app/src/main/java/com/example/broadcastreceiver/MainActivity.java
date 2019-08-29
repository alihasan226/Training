package com.example.broadcastreceiver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //broadcast a custom intent
    public void broadcastIntent(View view)
    {
        Intent intent=new Intent();
        intent.setAction("com.broadcastreceiver.CUSTOM_INTENT");//register that intent in the xml file
        sendBroadcast(intent);
    }
}
