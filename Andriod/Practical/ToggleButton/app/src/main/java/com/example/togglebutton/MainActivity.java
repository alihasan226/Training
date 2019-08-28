package com.example.togglebutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    private ToggleButton tog1,tog2;
    private Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButtonClick(but);
    }

    private void addListenerOnButtonClick(Button but) {

        tog1=(ToggleButton)findViewById(R.id.toggle1);
        tog2=(ToggleButton)findViewById(R.id.toggle2);
        but.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }
}
