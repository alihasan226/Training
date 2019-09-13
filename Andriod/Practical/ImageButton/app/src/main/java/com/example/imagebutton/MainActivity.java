package com.example.imagebutton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton=(ImageButton)findViewById(R.id.imagebutton);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"This is Image Button",Toast.LENGTH_LONG).show();
            }
        });

        public boolean onTouchEvent(MotionEvent motion)
        {

        }

    }
}
