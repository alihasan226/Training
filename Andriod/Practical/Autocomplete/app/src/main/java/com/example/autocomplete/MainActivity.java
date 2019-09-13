package com.example.autocomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView autoCompleteTextView;
    private Button button;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String country[]={"India","Germany","England","London","Paris","China","Nepal","Australia","Indonesia","Iran","Israel","Africa","Argentina","Berlin","Afghanistan","Alaska","New Zealand","New York"};
        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.autocomplete);
        button=(Button)findViewById(R.id.buttonl);
        textView=(TextView)findViewById(R.id.textview2);

        ArrayAdapter<String> arr=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,country);

        arr.sort(new Comparator<String>() {//this is used for sort the list using comparision
            @Override
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });

        autoCompleteTextView.setThreshold(0);
        autoCompleteTextView.setAdapter(arr);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String output="Selected Country : ";
                output=output+autoCompleteTextView.getText().toString();
                textView.setText(output);
            }
        });
    }
}
