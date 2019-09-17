package com.example.addressinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private TextView textView1,textView2,textView3;
    private EditText editText1,editText2,editText3,editText4,editText5,editText6,editText7,editText8,editText9,editText10,editText11;
    private Button button1;
    public static final String[] state={"Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhatisgarh","Goa","Gujrat","Haryana","Himacal Pradesh","Jammu & Kashmir","Jharkhand","karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal","Andaman and Nicobar Island","Chandigarh","Dadar and Nagar Haveli","Daman","Delhi","Lakshadweep","Puducherry"};
    final Calendar calendar=Calendar.getInstance();

    public void updateLabel()
    {
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat sdf=new SimpleDateFormat(myFormat, Locale.US);

        editText3.setText(sdf.format(calendar.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner=(Spinner)findViewById(R.id.spinner1);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> arr=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,state);
        arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arr);


        editText3=(EditText)findViewById(R.id.edittext3);
        editText11=(EditText)findViewById(R.id.edittext11);
        final String number=editText11.getText().toString();
        button1=(Button)findViewById(R.id.buttonl);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),number+" gfh",Toast.LENGTH_LONG).show();
            }
        });





        final DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view,int year,int monthyear,int dayofmonth)
            {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,monthyear);
                calendar.set(Calendar.DAY_OF_MONTH,dayofmonth);
            }
        };

        editText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(MainActivity.this,date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
                updateLabel();
            }

        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String item=adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
