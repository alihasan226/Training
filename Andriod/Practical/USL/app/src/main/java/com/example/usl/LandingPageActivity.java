package com.example.usl;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.adapters.AdapterViewBindingAdapter;
import androidx.drawerlayout.widget.DrawerLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LandingPageActivity extends AppCompatActivity implements View.OnClickListener{


    ActionBarDrawerToggle toggle;
    public String game[]={"Choose Game","Game1","Game2","Game3"};
    @BindView(R.id.spinnerGame)
    Spinner spinnerGame;
    @BindView(R.id.tvDate)
    TextView tvDate;
    @BindView(R.id.rlSpinnerGame)
    RelativeLayout rlSpinnerGame;
    @BindView(R.id.rlDatePicker)
    RelativeLayout rlDatePicker;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav)
    ImageView navigation;
    private List<String> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        ButterKnife.bind(this);
        rlDatePicker.setOnClickListener(this);
        list=new ArrayList<String>();
        for(int i=0;i<game.length;i++){
            list.add(game[i]);
        }


        ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<String>(LandingPageActivity.this,android.R.layout.simple_spinner_dropdown_item,list);
        spinnerGame.setAdapter(spinnerAdapter);





        navigation.setOnClickListener(view -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



    }

    public void datepicker(){

        Calendar mcurrentDate=Calendar.getInstance();
        int year=mcurrentDate.get(Calendar.YEAR);
        int month=mcurrentDate.get(Calendar.MONTH);
        int day=mcurrentDate.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog   mDatePicker =new DatePickerDialog(LandingPageActivity.this, new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday)
            {
                tvDate.setText(new StringBuilder().append(selectedday).append("-").append(selectedmonth+1).append("-").append(selectedyear));
            }
        },year, month, day);
        mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        mDatePicker.show();

    }



    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.rlDatePicker:
                datepicker();
                break;
            default:
                break;
        }
    }


}
