package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private LinearLayout linearLayout,linearLayout1,linearLayout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout=findViewById(R.id.linearlayout);
        linearLayout1=findViewById(R.id.linearlayout1);
        linearLayout3=findViewById(R.id.linearlayout3);
        button=findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TranslateAnimation Example
                Animation new_animation2=new TranslateAnimation(0, 767, 0, 0);
                new_animation2.setDuration(5000);//it is used for set the time for translate milli second
                new_animation2.setFillAfter(true);//if it sets false than the layout move to its original position after completing the translation
                linearLayout3.startAnimation(new_animation2);
                linearLayout3.setVisibility(View.VISIBLE);//it will visible but also take the space.*/

                //Scaling
                Animation new_animation=new ScaleAnimation(1f, 50f, 1f, 1f, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f);
                new_animation.setDuration(7000);
                new_animation.setFillAfter(true);
                linearLayout.startAnimation(new_animation);
                linearLayout.setVisibility(View.VISIBLE);

                //Scaling
                Animation new_animation1=new ScaleAnimation(1f, 50f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0f);
                new_animation1.setDuration(7000);
                new_animation1.setFillAfter(true);
                linearLayout1.startAnimation(new_animation1);
                linearLayout1.setVisibility(View.VISIBLE);

            }
        });
    }

}
