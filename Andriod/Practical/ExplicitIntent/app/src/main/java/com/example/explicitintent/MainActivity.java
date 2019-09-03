package com.example.explicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    protected EditText firstnumber,secondnumber;
    protected Button sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstnumber=(EditText)findViewById(R.id.editText);
        secondnumber=(EditText)findViewById(R.id.editText2);
        sum=(Button)findViewById(R.id.button2);

        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number1=Integer.parseInt(firstnumber.getText().toString());
                int number2=Integer.parseInt(secondnumber.getText().toString());
                int sum=number1+number2;
                Intent intent=new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtra("SUM",number1+" + "+number2+" = "+sum);
                startActivity(intent);
            }
        });
    }
}
