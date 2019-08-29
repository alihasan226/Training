package com.example.radiobutton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private ActionBar actionBar;
    protected RadioButton gender;
    protected RadioGroup rg;
    protected Button but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar=getSupportActionBar();
        actionBar.setTitle("RadioButton");

        rg=(RadioGroup)findViewById(R.id.radioGroup);
    }

    public void onClickbuttonMethod(View v)
    {
        int selectedId=rg.getCheckedRadioButtonId();
        gender=(RadioButton)findViewById(selectedId);
        if(selectedId==-1)
        {
            Toast.makeText(getApplicationContext(),"Nothing is Selected",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),gender.getText(),Toast.LENGTH_LONG).show();
        }

    }
}
