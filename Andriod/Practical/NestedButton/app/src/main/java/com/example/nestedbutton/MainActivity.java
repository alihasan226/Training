package com.example.nestedbutton;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickme(View view)
    {
        switch (view.getId())
        {
            case R.id.button1:
                b1=(Button)findViewById(R.id.button1);
                String button1=b1.getText().toString();
                Toast.makeText(getApplicationContext(),button1,Toast.LENGTH_LONG).show();
                break;
            case R.id.button2:
                b2=(Button)findViewById(R.id.button2);
                String button2=b2.getText().toString();
                Toast.makeText(getApplicationContext(),button2,Toast.LENGTH_LONG).show();
                break;
        }
    }
}
