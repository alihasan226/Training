package com.example.animationimage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout=findViewById(R.id.linearlayout);
        button=findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Scaling
                Animation new_animation=new ScaleAnimation(1f,4f,1.0f,1.0f,Animation.RELATIVE_TO_SELF,0.0f,Animation.RELATIVE_TO_SELF,0.0f);
                new_animation.setDuration(1000);
                new_animation.setFillAfter(true);
                linearLayout.startAnimation(new_animation);
                linearLayout.setVisibility(View.VISIBLE);

            }
        });
    }
}
