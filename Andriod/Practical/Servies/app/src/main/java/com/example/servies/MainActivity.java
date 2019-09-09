package com.example.servies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar=getSupportActionBar();
        actionBar.setTitle("Example of Service");
    }

    public void startServices(View view)
    {
        startService(new Intent(this,Receiver.class));
    }

    public void stopServices(View view)
    {
        stopService(new Intent(this,Receiver.class));
    }

}
