package com.example.notification;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    protected Button b1;
    protected EditText textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button)findViewById(R.id.button2);
        textview=(EditText) findViewById(R.id.editText);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sentOnChannel();
            }
        });
    }

    public void sentOnChannel()
    {

    }
}
