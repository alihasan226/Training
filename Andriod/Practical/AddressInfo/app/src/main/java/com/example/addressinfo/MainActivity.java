package com.example.addressinfo;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private ScrollView scrollView;
    private Spinner spinner;
    private TextView textView1,textView2,textView3;
    public EditText editText1,editText2,editText3,editText4,editText5,editText6,editText7,editText8,editText9,editText10,editText11,editText12;
    private Button button1;
    public static final String[] state={"Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhatisgarh","Goa","Gujrat","Haryana","Himacal Pradesh","Jammu & Kashmir","Jharkhand","karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal","Andaman and Nicobar Island","Chandigarh","Dadar and Nagar Haveli","Daman","Delhi","Lakshadweep","Puducherry"};
    public String emailPattern= "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    final Calendar calendar=Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText1=(EditText)findViewById(R.id.edittext1);
        editText2=(EditText)findViewById(R.id.edittext2);
        editText3=(EditText)findViewById(R.id.edittext3);//Date Picker Dialog
        editText4=(EditText)findViewById(R.id.edittext4);
        editText5=(EditText)findViewById(R.id.edittext5);
        editText6=(EditText)findViewById(R.id.edittext6);
        editText7=(EditText)findViewById(R.id.edittext7);
        editText8=(EditText)findViewById(R.id.edittext8);
        editText9=(EditText)findViewById(R.id.edittext9);
        editText10=(EditText)findViewById(R.id.edittext10);
        editText11=(EditText)findViewById(R.id.edittext11);
        editText12=(EditText)findViewById(R.id.edittext12);
        spinner=(Spinner)findViewById(R.id.spinner1);
        button1=(Button)findViewById(R.id.buttonl);

        scrollView=(ScrollView)findViewById(R.id.scrollview);

        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                //First Name
                if(TextUtils.isEmpty(editText1.getText()) || TextUtils.isDigitsOnly(editText1.getText()))
                {
                    scrollView.scrollTo(0,editText1.getBaseline());
                    editText1.setError("Invalid Input");
                    editText1.requestFocus();
                }
                //Last Name
                else if(TextUtils.isEmpty(editText2.getText())|| TextUtils.isDigitsOnly(editText2.getText()))
                {
                    scrollView.scrollTo(0,editText2.getBaseline());
                    editText2.setError("Invalid Input");
                    editText2.requestFocus();
                }
                //Date of Birth
                else if(TextUtils.isEmpty(editText3.getText()))
                {
                    scrollView.scrollTo(0,editText3.getBaseline());
                    editText3.setError("Required Field");
                    editText3.requestFocus();
                }
                //Email Validation
                else if(TextUtils.isEmpty(editText12.getText())) //email validation checcking
                {
                    scrollView.scrollTo(0,editText12.getBaseline());
                    editText12.setError("Enter Email");
                    editText12.requestFocus();
                }
                //final String email=editText12.getText().toString().trim();
                else if(!(editText12.getText().toString().trim()).matches(emailPattern))
                {
                    scrollView.scrollTo(0,editText12.getBaseline());
                    editText12.setError("Invalid Email");
                    editText12.requestFocus();
                }
                //University Name
                else if(TextUtils.isEmpty(editText4.getText()) || TextUtils.isDigitsOnly(editText4.getText()))
                {
                    scrollView.smoothScrollTo(0,editText4.getBaseline());
                    editText4.setError("Invalid Input");
                    editText4.requestFocus();
                }
                //College Name
                else if(TextUtils.isEmpty(editText5.getText()) || TextUtils.isDigitsOnly(editText5.getText()))
                {
                    scrollView.scrollTo(0,editText5.getBaseline());
                    editText5.setError("Required Eiled");
                    editText5.requestFocus();
                }
                //Stream
                else if(TextUtils.isEmpty(editText6.getText()) || TextUtils.isDigitsOnly(editText6.getText()))
                {
                    scrollView.scrollTo(0,editText6.getBaseline());
                    editText6.setError("Required Eiled");
                    editText6.requestFocus();
                }
                //Percentage Field
                else if(TextUtils.isEmpty(editText7.getText()))
                {
                    scrollView.scrollTo(0,editText7.getBaseline());
                    editText7.setError("Required Field");
                    editText7.requestFocus();
                }
                //House Number Validation
                else if(TextUtils.isEmpty(editText8.getText()))
                {
                    scrollView.scrollTo(0,editText8.getBaseline());
                    editText8.setError("Required Field");
                    editText8.requestFocus();
                }
                //Bloack Number Validation
                else if(TextUtils.isEmpty(editText9.getText()))
                {
                    scrollView.scrollTo(0,editText9.getBaseline());
                    editText9.setError("Required Field");
                    editText9.requestFocus();
                }
                //City Validation
                else if(TextUtils.isEmpty(editText10.getText()))
                {
                    scrollView.scrollTo(0,editText10.getBaseline());
                    editText10.setError("Required Field");
                    editText10.requestFocus();
                }
                else if(TextUtils.isDigitsOnly(editText10.getText()))
                {
                    scrollView.scrollTo(0,editText10.getBaseline());
                    editText10.setError("Should Only be Character");
                    editText10.requestFocus();
                }
                //Pin Code Validation
                else if(TextUtils.isEmpty(editText11.getText()))
                {
                    scrollView.scrollTo(0,editText11.getBaseline());
                    editText11.setError("Required Field");
                    editText11.requestFocus();
                }
                else if(pincodevalidation(editText11.getText().toString()))
                {
                    scrollView.scrollTo(0,editText11.getBaseline());
                    editText11.setError("Invalid Pin Code");
                    editText11.requestFocus();
                }
                else
                {
                    Intent intent=new Intent(getApplicationContext(),StudentRegistration.class);
                    intent.putExtra("firstname",editText1.getText().toString());
                    intent.putExtra("lastname",editText2.getText().toString());
                    intent.putExtra("dob",editText3.getText().toString());
                    intent.putExtra("email",editText12.getText().toString());
                    intent.putExtra("university",editText4.getText().toString());
                    intent.putExtra("college",editText5.getText().toString());
                    intent.putExtra("stream",editText6.getText().toString());
                    intent.putExtra("percentage",editText7.getText().toString());
                    intent.putExtra("housenumber",editText8.getText().toString());
                    intent.putExtra("blocknumber",editText9.getText().toString());
                    intent.putExtra("city",editText10.getText().toString());
                    intent.putExtra("pincode",editText11.getText().toString());
                    intent.putExtra("state",spinner.getSelectedItem().toString());
                    startActivity(intent);
                }
            }
        });

        //Spinner for selecting the state
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> arr=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,state);
        arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arr);


        //Date Picker object
        final DatePickerDialog.OnDateSetListener date=new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view,int year,int monthyear,int dayofmonth)
            {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,monthyear);
                calendar.set(Calendar.DAY_OF_MONTH,dayofmonth);
                updateLabel();
            }
        };
        editText3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this,date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();

                //new DatePickerDialog(MainActivity.this,date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

    }

    public boolean pincodevalidation(String text)
    {
        boolean temp=true;
        int length=text.length();
        if(length==6)
        {
            temp=false;
        }

        return temp;
    }

    public void updateLabel()
    {
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat sdf=new SimpleDateFormat(myFormat, Locale.US);

        editText3.setText(sdf.format(calendar.getTime()));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l)
    {
        String item=adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}

}
