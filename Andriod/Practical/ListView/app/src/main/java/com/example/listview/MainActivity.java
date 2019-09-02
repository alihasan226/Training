package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] mobileArray={"Android","IPhone","WindowsPhone","BlackBerry","WebOS","Ubunto","Windows","MacOS","Linux"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter adaptor=new ArrayAdapter<String>(this,R.layout.activity_listview,mobileArray);

        ListView listview=(ListView)findViewById(R.id.mobile_list);
        listview.setAdapter(adaptor);
    }
}
