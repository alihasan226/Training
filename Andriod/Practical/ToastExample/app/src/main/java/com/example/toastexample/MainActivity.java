package com.example.toastexample;

import android.os.Bundle;
import android.view.View;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*other methods of toast
        Toast new_toast=Toast.makeText(getApplicationContext(),"Ali Hasan",Toast.LENGTH_LONG);
        new_toast.setMargin(50,50);
        new_toast.show();
        */
        LayoutInflater li=getLayoutInflater();//layout inflater takes XML file as an input and build view object
        View layout=li.inflate(R.layout.custometoast,(ViewGroup)findViewById(R.id.custome_layout));//Inflate a new view hierarchy from the specified xml node
        /*
        it is not currently possible to use LayoutInflater with an XmlPullParser over a plain XML file at runtime.
         */
        Toast.makeText(getApplicationContext(),"BerylSystems.com",Toast.LENGTH_SHORT).show();
        //here application context is attached to the application's life cycle and willalways be same though the life of application so if you are using application context or even acticity context because toast can be raised any where in the application.

        //creating the toast object
        Toast toast=new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
        toast.setView(layout);
        toast.show();
    }
}
