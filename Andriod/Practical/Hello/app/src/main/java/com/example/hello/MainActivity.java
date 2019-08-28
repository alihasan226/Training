package com.example.hello;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText text1,text2;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListeneronButton();
    }

    private void addListeneronButton() {
        text1=(EditText)findViewById(R.id.editfirsttext);
        text2=(EditText)findViewById(R.id.editsecondtext);
        button1=(Button)findViewById(R.id.submitbutton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value1=text1.getText().toString();
                String value2=text2.getText().toString();

                int a=Integer.parseInt(value1);
                int b=Integer.parseInt(value2);

                int sum=a+b;

                Toast.makeText(getApplicationContext(),"Sum = "+String.valueOf(sum),Toast.LENGTH_LONG).show();
            }
        });
    }

}
