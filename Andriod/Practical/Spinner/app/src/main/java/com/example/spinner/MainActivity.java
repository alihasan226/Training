package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import java.util.ArrayList;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.view.View;
import android.os.Bundle;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=(Spinner)findViewById(R.id.spinner);

        spinner.setOnItemClickListener(this);
        List<String> list=new ArrayList<String>();
        list.add("India");
        list.add("Australia");
        list.add("London");
        list.add("Paris");
        list.add("New York");
        list.add("New Zealand");
        list.add("France");
        list.add("Africa");

        ArrayAdapter<String> arr=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arr);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long l)
    {
        String item=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),"Selected : "+item,Toast.LENGTH_LONG).show();
    }
}
