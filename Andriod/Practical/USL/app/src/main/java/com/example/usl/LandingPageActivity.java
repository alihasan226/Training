package com.example.usl;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
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
import java.util.concurrent.Delayed;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.tablelayout)
    View table;
    @BindView(R.id.tvSubtotal1)
    TextView tvSubtotal1;
    @BindView(R.id.tvSubtotal2)
    TextView TvSubtotal2;

    public TextView tvRow1,tvRow2,tvRow3,tvRow4,tvRow5,tvRow6,tvRow7,tvRow8,tvRow9,tvRow10,tvRow11,tvRow12;

    public int c1r1,c1r2,c1r3,c1r4,c1r5,c1r6,c1r7,c1r8,c1r9,c1r10,c2r1,c2r2,c2r3,c2r4,c2r5,c2r6,c2r7,c2r8,c2r9,c2r10,c3r1,c3r2,c3r3,c3r4,c3r5,c3r6,c3r7,c3r8,c3r9,c3r10,c4r1,c4r2,c4r3,c4r4,c4r5,c4r6,c4r7,c4r8,c4r9,c4r10
    ,c5r1,c5r2,c5r3,c5r4,c5r5,c5r6,c5r7,c5r8,c5r9,c5r10,c6r1,c6r2,c6r3,c6r4,c6r5,c6r6,c6r7,c6r8,c6r9,c6r10,c7r1,c7r2,c7r3,c7r4,c7r5,c7r6,c7r7,c7r8,c7r9,c7r10,c8r1,c8r2,c8r3,c8r4,c8r5,c8r6,c8r7,c8r8,c8r9,c8r10
    ,c9r1,c9r2,c9r3,c9r4,c9r5,c9r6,c9r7,c9r8,c9r9,c9r10,c10r1,c10r2,c10r3,c10r4,c10r5,c10r6,c10r7,c10r8,c10r9,c10r10,c11r1,c11r2,c11r3,c11r4,c11r5,c11r6,c11r7,c11r8,c11r9,c11r10,c12r1,c12r2,c12r3,c12r4,c12r5,c12r6,c12r7,c12r8,c12r9,c12r10;

    public int TC1,TC2,TC3,TC4,TC5,TC6,TC7,TC8,TC9,TC10,TC11,TC12;

    public int col1,col2,col3,col4,col5,col6,col7,col8,col9,col10,col11,col12;

    public EditText tvOne,tvTwo,tvThree,tvFour,tvFive,tvSix,tvSeven,tvEight,tvNine,tvTen,tveleven,tvtwlve,tvthiteen,tvFourteen,tvFifteen,tvSixteen,tvSeventeen,tvEighteen,tvNinteen,tvTwenty
    ,tvtevone,tvtwentytwo,tvtwentythree,tvtwentyFour,tvtwentyFive,tvtwentySix,tvtwentySeven,tvtwentyEight,tvtwentyNine,tvThirty,tvthiryone,tvthirtytwo,tvthirtythree,tvthirtyFour,tvthirtyFive
    ,tvthirtySix,tvthirtySeven,tvthirtyEight,tvthirtyNine,tvFourty,tvFourtyone,tvFortytwo,tvFourtythree,tvFourtyFour,tvFourtyFive,tvFourtySix,tvFourtySeven,tvFortyEight,tvFourtyNine,tvFifty,tvFiftyone,tvFiftytwo,tvFiftythree,tvFiftyFour,tvFiftyFive,tvFiftySix,tvFiftySeven,tvFiftyEight,tvFiftyNine,tvSixty
    ,tvSixtyone,tvSixtyTwo,tvSixtyThree,tvSixtyFour,tvSixtyFive,tvSixtySix,tvSixtySeven,tvSixtyEight,tvSixtyNine,tvSeventy,tvSeventyone,tvSeventytwo,tvSeventythree,tvSeventyFour,tvSeventyFive,tvSeventySix,tvSeventySeven,tvSeventyEight,tvSeventyNine,tvEighty,tvEightyOne,tvEightyTwo,tvEightyThree,tvEightyFour,tvEightyFive,tvEightySix
    ,tvEightySeven,tvEightyEight,tvEightyNine,tvNinty,tvNintyone,tvNintytwo,tvNintythree,tvNintyFour,tvNintyFive,tvNintySix,tvNintySeven,tvNintyEight,tvNintyNine,tvHundrad,tvd1,tvd2,tvd3,tvd4,tvd5,tvd6,tvd7,tvd8,tvd9,tvd10,tvh1,tvh2,tvh3,tvh4,tvh5,tvh6,tvh7,tvh8,tvh9,tvh10;

    public final Handler handler=new Handler();
    public int Count[][]=new int[10][12];
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

        for(int i=0;i<10;i++){
            for(int j=0;j<12;j++){
                Count[i][j]=0;
            }
        }

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        ArrayAdapter<String> spinnerAdapter=new ArrayAdapter<String>(LandingPageActivity.this,android.R.layout.simple_spinner_dropdown_item,list);
        spinnerGame.setAdapter(spinnerAdapter);

        navigation.setOnClickListener(view -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        tvRow1=table.findViewById(R.id.tvRow1);
        tvRow2=table.findViewById(R.id.tvRow2);
        tvRow3=table.findViewById(R.id.tvRow3);
        tvRow4=table.findViewById(R.id.tvRow4);
        tvRow5=table.findViewById(R.id.tvRow5);
        tvRow6=table.findViewById(R.id.tvRow6);
        tvRow7=table.findViewById(R.id.tvRow7);
        tvRow8=table.findViewById(R.id.tvRow8);
        tvRow9=table.findViewById(R.id.tvRow9);
        tvRow10=table.findViewById(R.id.tvRow10);
        tvRow11=table.findViewById(R.id.tvRow11);
        tvRow12=table.findViewById(R.id.tvRow12);

    }

    @Override
    protected void onResume(){
        super.onResume();
        col1=1;
        col2=2;
        col3=3;
        col4=4;
        col5=5;
        col6=6;
        col7=7;
        col8=8;
        col9=9;
        col10=10;
        col11=11;
        col12=12;

        setItemView();
        setTextValue();
    }

    public void datepicker(){
        Calendar mcurrentDate=Calendar.getInstance();
        int year=mcurrentDate.get(Calendar.YEAR);
        int month=mcurrentDate.get(Calendar.MONTH);
        int day=mcurrentDate.get(Calendar.DAY_OF_MONTH);
        final DatePickerDialog   mDatePicker =new DatePickerDialog(LandingPageActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                tvDate.setText(new StringBuilder().append(selectedday).append("-").append(selectedmonth+1).append("-").append(selectedyear));
            }
        },year, month, day);
        mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
        mDatePicker.show();
    }

    @OnClick(R.id.btn_Submit)
    public void submit(){
        Toast.makeText(getApplicationContext(),"Need to Submit",Toast.LENGTH_SHORT).show();
    }



    public void setItemView() {
        tvOne = table.findViewById(R.id.tvOne);tvTwo = table.findViewById(R.id.tvTwo);tvThree = table.findViewById(R.id.tvThree);tvFour = table.findViewById(R.id.tvFour);
        tvFive = table.findViewById(R.id.tvFive);tvSix = table.findViewById(R.id.tvSix);tvSeven = table.findViewById(R.id.tvSeven);tvEight = table.findViewById(R.id.tvEight);
        tvNine = table.findViewById(R.id.tvNine);tvTen = table.findViewById(R.id.tvTen);

        tveleven = table.findViewById(R.id.tveleven);tvtwlve = table.findViewById(R.id.tvtwlve);tvthiteen = table.findViewById(R.id.tvthiteen);tvFourteen = table.findViewById(R.id.tvFourteen);
        tvFifteen = table.findViewById(R.id.tvFifteen);tvSixteen = table.findViewById(R.id.tvSixteen);tvSeventeen = table.findViewById(R.id.tvSeventeen);tvEighteen = table.findViewById(R.id.tvEighteen);
        tvNinteen = table.findViewById(R.id.tvNinteen);tvTwenty = table.findViewById(R.id.tvTwenty);

        tvtevone = table.findViewById(R.id.tvtevone);tvtwentytwo = table.findViewById(R.id.tvtwentytwo);tvtwentythree = table.findViewById(R.id.tvtwentythree);tvtwentyFour = table.findViewById(R.id.tvtwentyFour);
        tvtwentyFive = table.findViewById(R.id.tvtwentyFive);tvtwentySix = table.findViewById(R.id.tvtwentySix);tvtwentySeven = table.findViewById(R.id.tvtwentySeven);
        tvtwentyEight = table.findViewById(R.id.tvtwentyEight);tvtwentyNine = table.findViewById(R.id.tvtwentyNine);tvThirty = table.findViewById(R.id.tvThirty);

        tvthiryone = table.findViewById(R.id.tvthiryone);tvthirtytwo = table.findViewById(R.id.tvthirtytwo);tvthirtythree = table.findViewById(R.id.tvthirtythree);tvthirtyFour = table.findViewById(R.id.tvthirtyFour);
        tvthirtyFive = table.findViewById(R.id.tvthirtyFive);
        tvthirtySix = table.findViewById(R.id.tvthirtySix);
        tvthirtySeven = table.findViewById(R.id.tvthirtySeven);
        tvthirtyEight = table.findViewById(R.id.tvthirtyEight);
        tvthirtyNine = table.findViewById(R.id.tvthirtyNine);
        tvFourty = table.findViewById(R.id.tvFourty);

        tvFourtyone = table.findViewById(R.id.tvFourtyone);
        tvFortytwo = table.findViewById(R.id.tvFortytwo);
        tvFourtythree = table.findViewById(R.id.tvFourtythree);
        tvFourtyFour = table.findViewById(R.id.tvFourtyFour);
        tvFourtyFive = table.findViewById(R.id.tvFourtyFive);
        tvFourtySix = table.findViewById(R.id.tvFourtySix);
        tvFourtySeven = table.findViewById(R.id.tvFourtySeven);
        tvFortyEight = table.findViewById(R.id.tvFortyEight);
        tvFourtyNine = table.findViewById(R.id.tvFourtyNine);
        tvFifty = table.findViewById(R.id.tvFifty);

        tvFiftyone = table.findViewById(R.id.tvFiftyone);
        tvFiftytwo = table.findViewById(R.id.tvFiftytwo);
        tvFiftythree = table.findViewById(R.id.tvFiftythree);
        tvFiftyFour = table.findViewById(R.id.tvFiftyFour);
        tvFiftyFive = table.findViewById(R.id.tvFiftyFive);
        tvFiftySix = table.findViewById(R.id.tvFiftySix);
        tvFiftySeven = table.findViewById(R.id.tvFiftySeven);
        tvFiftyEight = table.findViewById(R.id.tvFiftyEight);
        tvFiftyNine = table.findViewById(R.id.tvFiftyNine);
        tvSixty = table.findViewById(R.id.tvSixty);

        tvSixtyone = table.findViewById(R.id.tvSixtyone);
        tvSixtyTwo = table.findViewById(R.id.tvSixtyTwo);
        tvSixtyThree = table.findViewById(R.id.tvSixtyThree);
        tvSixtyFour = table.findViewById(R.id.tvSixtyFour);
        tvSixtyFive = table.findViewById(R.id.tvSixtyFive);
        tvSixtySix = table.findViewById(R.id.tvSixtySix);
        tvSixtySeven = table.findViewById(R.id.tvSixtySeven);
        tvSixtyEight = table.findViewById(R.id.tvSixtyEight);
        tvSixtyNine = table.findViewById(R.id.tvSixtyNine);
        tvSeventy = table.findViewById(R.id.tvSeventy);

        tvSeventyone = table.findViewById(R.id.tvSeventyone);
        tvSeventytwo = table.findViewById(R.id.tvSeventytwo);
        tvSeventythree = table.findViewById(R.id.tvSeventythree);
        tvSeventyFour = table.findViewById(R.id.tvSeventyFour);
        tvSeventyFive = table.findViewById(R.id.tvSeventyFive);
        tvSeventySix = table.findViewById(R.id.tvSeventySix);
        tvSeventySeven = table.findViewById(R.id.tvSeventySeven);
        tvSeventyEight = table.findViewById(R.id.tvSeventyEight);
        tvSeventyNine = table.findViewById(R.id.tvSeventyNine);
        tvEighty = table.findViewById(R.id.tvEighty);

        tvEightyOne = table.findViewById(R.id.tvEightyOne);
        tvEightyTwo = table.findViewById(R.id.tvEightyTwo);
        tvEightyThree = table.findViewById(R.id.tvEightyThree);
        tvEightyFour = table.findViewById(R.id.tvEightyFour);
        tvEightyFive = table.findViewById(R.id.tvEightyFive);
        tvEightySix = table.findViewById(R.id.tvEightySix);
        tvEightySeven = table.findViewById(R.id.tvEightySeven);
        tvEightyEight = table.findViewById(R.id.tvEightyEight);
        tvEightyNine = table.findViewById(R.id.tvEightyNine);
        tvNinty = table.findViewById(R.id.tvNinty);

        tvNintyone = table.findViewById(R.id.tvNintyone);
        tvNintytwo = table.findViewById(R.id.tvNintytwo);
        tvNintythree = table.findViewById(R.id.tvNintythree);
        tvNintyFour = table.findViewById(R.id.tvNintyFour);
        tvNintyFive = table.findViewById(R.id.tvNintyFive);
        tvNintySix = table.findViewById(R.id.tvNintySix);
        tvNintySeven = table.findViewById(R.id.tvNintySeven);
        tvNintyEight = table.findViewById(R.id.tvNintyEight);
        tvNintyNine = table.findViewById(R.id.tvNintyNine);
        tvHundrad = table.findViewById(R.id.tvHundrad);

         tvd1 = table.findViewById(R.id.tvd1);
         tvd2 = table.findViewById(R.id.tvd2);
         tvd3 = table.findViewById(R.id.tvd3);
         tvd4 = table.findViewById(R.id.tvd4);  tvd5 = table.findViewById(R.id.tvd5);  tvd6 = table.findViewById(R.id.tvd6);  tvd7 = table.findViewById(R.id.tvd7);  tvd8 = table.findViewById(R.id.tvd8);  tvd9 = table.findViewById(R.id.tvd9);  tvd10 = table.findViewById(R.id.tvd10);
          tvh1 = table.findViewById(R.id.tvh1);  tvh2 = table.findViewById(R.id.tvh2);  tvh3 = table.findViewById(R.id.tvh3);  tvh4 = table.findViewById(R.id.tvh4);  tvh5 = table.findViewById(R.id.tvh5);  tvh6 = table.findViewById(R.id.tvh6);  tvh7 = table.findViewById(R.id.tvh7);  tvh8 = table.findViewById(R.id.tvh8);  tvh9 = table.findViewById(R.id.tvh9);  tvh10 = table.findViewById(R.id.tvh10);

    }

    public void setTextValue(){

        tvOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c1r1=0;
                }else{
                    c1r1=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c1r1,0,0,col1);
                }
            };
        });

        tvTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c1r2=0;
                }else{
                    c1r2=Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c1r2,1,0,col1);
                }
            };
        });

        tvThree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c1r3=0;
                }else{
                    c1r3=Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c1r3,2,0,col1);
                }
            };
        });

        tvFour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c1r4=0;
                }else{
                    c1r4=Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c1r4,3,0,col1);
                }
            };
        });

        tvFive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c1r5=0;
                }else{
                    c1r5=Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c1r5,4,0,col1);
                }
            };
        });

        tvSix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c1r6=0;
                }else{
                    c1r6=Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c1r6,5,0,col1);
                }
            };
        });

        tvSeven.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c1r7=0;
                }else{
                    c1r7=Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c1r7,6,0,col1);
                }
            };
        });

        tvEight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c1r8=0;
                }else{
                    c1r8=Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c1r8,7,0,col1);
                }
            };
        });

        tvNine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c1r9=0;
                }else{
                    c1r9=Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c1r9,8,0,col1);
                }
            };
        });

        tvTen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c1r10=0;
                }else{
                    c1r10=Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c1r10,9,0,col1);
                }
            };
        });

        //Column 2
        tveleven.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c2r1=0;
                }else{
                    c2r1=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c2r1,0,1,col2);
                }
            };
        });

        tvtwlve.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c2r2=0;
                }else{
                    c2r2=Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c2r2,1,1,col2);
                }
            };
        });

        tvthiteen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c2r3=0;
                }else{
                    c2r3=Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c2r3,2,1,col2);
                }
            };
        });

        tvFourteen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c2r4=0;
                }else{
                    c2r4=Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c2r4,3,1,col2);
                }
            };
        });

        tvFifteen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c2r5=0;
                }else{
                    c2r5=Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c2r5,4,1,col2);
                }
            };
        });

        tvSixteen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c2r6=0;
                }else{
                    c2r6=Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c2r6,5,1,col2);
                }
            };
        });

        tvSeventeen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c2r7=0;
                }else{
                    c2r7=Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c2r7,6,1,col2);
                }
            };
        });

        tvEighteen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c2r8=0;
                }else{
                    c2r8=Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c2r8,7,1,col2);
                }
            };
        });

        tvNinteen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c2r9=0;
                }else{
                    c2r9=Integer.parseInt(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c2r9,8,1,col2);
                }
            };
        });

        tvTwenty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c2r10=0;
                }else{
                    c2r10=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c2r10,9,1,col2);
                }
            };
        });

        //Column 3
        tvtwlve.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c3r1=0;
                }else{
                    c3r1=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c3r1,0,2,col3);
                }
            };
        });

        tvtwentytwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c3r2=0;
                }else{
                    c3r2=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c3r2,1,2,col3);
                }
            };
        });

        tvtwentythree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c3r3=0;
                }else{
                    c3r3=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c3r3,2,2,col3);
                }
            };
        });

        tvtwentyFour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c3r4=0;
                }else{
                    c3r4=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c3r4,3,2,col3);
                }
            };
        });

        tvtwentyFive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c3r5=0;
                }else{
                    c3r5=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c3r5,4,2,col3);
                }
            };
        });

        tvtwentySix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c3r6=0;
                }else{
                    c3r6=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c3r6,5,2,col3);
                }
            };
        });

        tvtwentySeven.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c3r7=0;
                }else{
                    c3r7=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c3r7,6,2,col3);
                }
            };
        });

        tvtwentyEight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c3r8=0;
                }else{
                    c3r8=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c3r8,7,2,col3);
                }
            };
        });

        tvtwentyNine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c3r9=0;
                }else{
                    c3r9=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c3r9,8,2,col3);
                }
            };
        });

        tvThirty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c3r10=0;
                }else{
                    c3r10=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c3r10,9,2,col3);
                }
            };
        });

        //Column 4
        tvthiryone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c4r1=0;
                }else{
                    c4r1=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c4r1,0,3,col4);
                }
            };
        });

        tvthirtytwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c4r2=0;
                }else{
                    c4r2=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c4r2,1,3,col4);
                }
            };
        });

        tvthirtythree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c4r3=0;
                }else{
                    c4r3=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c4r3,2,3,col4);
                }
            };
        });

        tvthirtyFour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c4r4=0;
                }else{
                    c4r4=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c4r4,3,3,col4);
                }
            };
        });

        tvthirtyFive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c4r5=0;
                }else{
                    c4r5=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c4r5,4,3,col4);
                }
            };
        });

        tvthirtySix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c4r6=0;
                }else{
                    c4r6=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c4r6,5,3,col4);
                }
            };
        });

        tvthirtySeven.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c4r7=0;
                }else{
                    c4r7=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c4r7,6,3,col4);
                }
            };
        });

        tvthirtyEight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c4r8=0;
                }else{
                    c4r8=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c4r8,7,3,col4);
                }
            };
        });

        tvthirtyNine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c4r9=0;
                }else{
                    c4r9=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c4r9,8,3,col4);
                }
            };
        });

        tvFourty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c4r10=0;
                }else{
                    c4r10=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c4r10,9,3,col4);
                }
            };
        });

        //Column 5

        tvFourtyone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c5r1=0;
                }else{
                    c5r1=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c5r1,0,4,col5);
                }
            };
        });

        tvFortytwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c5r2=0;
                }else{
                    c5r2=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c5r2,1,4,col5);
                }
            };
        });

        tvFourtythree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c5r3=0;
                }else{
                    c5r3=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c5r3,2,4,col5);
                }
            };
        });

        tvFourtyFour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c5r4=0;
                }else{
                    c5r4=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c5r4,3,4,col5);
                }
            };
        });

        tvFourtyFive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c5r5=0;
                }else{
                    c5r5=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c5r5,4,4,col5);
                }
            };
        });

        tvFourtySix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c5r6=0;
                }else{
                    c5r6=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c5r6,5,4,col5);
                }
            };
        });

        tvFourtySeven.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c5r7=0;
                }else{
                    c5r7=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c5r7,6,4,col5);
                }
            };
        });

        tvFortyEight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c5r8=0;
                }else{
                    c5r8=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c5r8,7,4,col5);
                }
            };
        });

        tvFourtyNine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c5r9=0;
                }else{
                    c5r9=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c5r9,8,4,col5);
                }
            };
        });

        tvFifty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c5r10=0;
                }else{
                    c5r10=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c5r10,9,4,col5);
                }
            };
        });

        //Column 6
        tvFiftyone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c6r1=0;
                }else{
                    c6r1=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c6r1,0,5,col6);
                }
            };
        });

        tvFiftytwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c6r2=0;
                }else{
                    c6r2=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c6r2,1,5,col6);
                }
            };
        });

        tvFiftythree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c6r3=0;
                }else{
                    c6r3=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c6r3,2,5,col6);
                }
            };
        });

        tvFiftyFour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c6r4=0;
                }else{
                    c6r4=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c6r4,3,5,col6);
                }
            };
        });

        tvFiftyFive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c6r5=0;
                }else{
                    c6r5=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c6r5,4,5,col6);
                }
            };
        });

        tvFiftySix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c6r6=0;
                }else{
                    c6r6=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c6r6,5,5,col6);
                }
            };
        });

        tvFiftySeven.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c6r7=0;
                }else{
                    c6r7=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c6r7,6,5,col6);
                }
            };
        });

        tvFiftyEight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c6r8=0;
                }else{
                    c6r8=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c6r8,7,5,col6);
                }
            };
        });

        tvFiftyNine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c6r9=0;
                }else{
                    c6r9=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c6r9,8,5,col6);
                }
            };
        });

        tvSixty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c6r10=0;
                }else{
                    c6r10=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c6r10,9,5,col6);
                }
            };
        });

        //Column 7
        tvSixtyone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c7r1=0;
                }else{
                    c7r1=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c7r1,0,6,col7);
                }
            };
        });

        tvSixtyTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c7r2=0;
                }else{
                    c7r2=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c7r2,1,6,col7);
                }
            };
        });

        tvSixtyThree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c7r3=0;
                }else{
                    c7r3=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c7r3,2,6,col7);
                }
            };
        });

        tvSixtyFour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c7r4=0;
                }else{
                    c7r4=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c7r4,3,6,col7);
                }
            };
        });

        tvSixtyFive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c7r5=0;
                }else{
                    c7r5=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c7r5,4,6,col7);
                }
            };
        });

        tvSixtySix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c7r6=0;
                }else{
                    c7r6=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c7r6,5,6,col7);
                }
            };
        });

        tvSixtySeven.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c7r7=0;
                }else{
                    c7r7=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c7r7,6,6,col7);
                }
            };
        });

        tvSixtyEight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c7r8=0;
                }else{
                    c7r8=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c7r8,7,6,col7);
                }
            };
        });

        tvSixtyNine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c7r9=0;
                }else{
                    c7r9=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c7r9,8,6,col7);
                }
            };
        });

        tvSeventy.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c7r10=0;
                }else{
                    c7r10=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c7r10,9,6,col7);
                }
            };
        });

        //Column 8

        tvSeventyone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c8r1=0;
                }else{
                    c8r1=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c8r1,0,7,col8);
                }
            };
        });

        tvSeventytwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c8r2=0;
                }else{
                    c8r2=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c8r2,1,7,col8);
                }
            };
        });

        tvSeventythree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c8r3=0;
                }else{
                    c8r3=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c8r3,2,7,col8);
                }
            };
        });

        tvSeventyFour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c8r4=0;
                }else{
                    c8r4=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c8r4,3,7,col8);
                }
            };
        });

        tvSeventyFive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c8r5=0;
                }else{
                    c8r5=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c8r5,4,7,col8);
                }
            };
        });

        tvSeventySix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c8r6=0;
                }else{
                    c8r6=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c8r6,5,7,col8);
                }
            };
        });

        tvSeventySeven.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c8r7=0;
                }else{
                    c8r7=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c8r7,6,7,col8);
                }
            };
        });

        tvSeventyEight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c8r8=0;
                }else{
                    c8r8=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c8r8,7,7,col8);
                }
            };
        });

        tvSeventyNine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c8r9=0;
                }else{
                    c8r9=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c8r9,8,7,col8);
                }
            };
        });

        tvEighty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c8r10=0;
                }else{
                    c8r10=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c8r10,9,7,col8);
                }
            };
        });

        //Column 9
        tvEightyOne.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c9r1=0;
                }else{
                    c9r1=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c9r1,0,8,col9);
                }
            };
        });

        tvEightyTwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c9r2=0;
                }else{
                    c9r2=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c9r2,1,8,col9);
                }
            };
        });

        tvEightyThree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c9r3=0;
                }else{
                    c9r3=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c9r3,2,8,col9);
                }
            };
        });

        tvEightyFour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c9r4=0;
                }else{
                    c9r4=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c9r4,3,8,col9);
                }
            };
        });

        tvEightyFive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c9r5=0;
                }else{
                    c9r5=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c9r5,4,8,col9);
                }
            };
        });

        tvEightySix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c9r6=0;
                }else{
                    c9r6=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c9r6,5,8,col9);
                }
            };
        });

        tvEightySeven.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c9r7=0;
                }else{
                    c9r7=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c9r7,6,8,col9);
                }
            };
        });

        tvEightyEight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c9r8=0;
                }else{
                    c9r8=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c9r8,7,8,col9);
                }
            };
        });

        tvEightyNine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c9r9=0;
                }else{
                    c9r9=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c9r9,8,8,col9);
                }
            };
        });

        tvNinty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c9r10=0;
                }else{
                    c9r10=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c9r10,9,8,col9);
                }
            };
        });

        //Column 10
        tvNintyone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c10r1=0;
                }else{
                    c10r1=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c10r1,0,9,col10);
                }
            };
        });

        tvNintytwo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c10r2=0;
                }else{
                    c10r2=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c10r2,1,9,col10);
                }
            };
        });

        tvNintythree.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c10r3=0;
                }else{
                    c10r3=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c10r3,2,9,col10);
                }
            };
        });

        tvNintyFour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c10r4=0;
                }else{
                    c10r4=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c10r4,3,9,col10);
                }
            };
        });

        tvNintyFive.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c10r5=0;
                }else{
                    c10r5=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c10r5,4,9,col10);
                }
            };
        });

        tvNintySix.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c10r6=0;
                }else{
                    c10r6=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c10r6,5,9,col10);
                }
            };
        });

        tvNintySeven.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c10r7=0;
                }else{
                    c10r7=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c10r7,6,9,col10);
                }
            };
        });

        tvNintyEight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c10r8=0;
                }else{
                    c10r8=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c10r8,7,9,col10);
                }
            };
        });

        tvNintyNine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c10r9=0;
                }else{
                    c10r9=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c10r9,8,9,col10);
                }
            };
        });

        tvHundrad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c10r10=0;
                }else{
                    c10r10=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c10r10,9,9,col10);
                }
            };
        });

        //Column 11
        tvd1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c11r1=0;
                }else{
                    c11r1=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c11r1,0,10,col11);
                }
            };
        });

        tvd2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c11r2=0;
                }else{
                    c11r2=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c11r2,1,10,col11);
                }
            };
        });

        tvd3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c11r3=0;
                }else{
                    c11r3=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c11r3,2,10,col11);
                }
            };
        });

        tvd4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c11r4=0;
                }else{
                    c11r4=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c11r4,3,10,col11);
                }
            };
        });

        tvd5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c11r5=0;
                }else{
                    c11r5=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c11r5,4,10,col11);
                }
            };
        });

        tvd6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c11r6=0;
                }else{
                    c11r6=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c11r6,5,10,col11);
                }
            };
        });

        tvd7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c11r7=0;
                }else{
                    c11r7=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c11r7,6,10,col11);
                }
            };
        });

        tvd8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c11r8=0;
                }else{
                    c11r8=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c11r8,7,10,col11);
                }
            };
        });

        tvd9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c11r9=0;
                }else{
                    c11r9=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c11r9,8,10,col11);
                }
            };
        });

        tvd10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c11r10=0;
                }else{
                    c11r10=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c11r10,9,10,col11);
                }
            };
        });
        //Column 12
        tvh1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c12r1=0;
                }else{
                    c12r1=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c12r1,0,11,col12);
                }
            };
        });

        tvh2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c12r2=0;
                }else{
                    c12r2=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c12r2,1,11,col12);
                }
            };
        });

        tvh3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c12r3=0;
                }else{
                    c12r3=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c12r3,2,11,col12);
                }
            };
        });

        tvh4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c12r4=0;
                }else{
                    c12r4=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c12r4,3,11,col12);
                }
            };
        });

        tvh5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c12r5=0;
                }else{
                    c12r5=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c12r5,4,11,col12);
                }
            };
        });

        tvh6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c12r6=0;
                }else{
                    c12r6=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c12r6,5,11,col12);
                }
            };
        });

        tvh7.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c12r7=0;
                }else{
                    c12r7=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c12r7,6,11,col12);
                }
            };
        });

        tvh8.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c12r8=0;
                }else{
                    c12r8=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c12r8,7,11,col12);
                }
            };
        });

        tvh9.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c12r9=0;
                }else{
                    c12r9=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c12r9,8,11,col12);
                }
            };
        });

        tvh10.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(String.valueOf(charSequence).equalsIgnoreCase("")){
                    c12r10=0;
                }else{
                    c12r10=Integer.parseInt(charSequence.toString());
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(userStopTyping,2000);
            }
            Runnable userStopTyping=new Runnable() {
                @Override
                public void run() {
                    Column(c12r10,9,11,col12);
                }
            };
        });
    }

    public void Column(int temp,int row,int column,int col){

        if(col==1){
            TC1=0;
            Count[row][column]=temp;
            for(int i=0;i<10;i++){
                TC1+=Count[i][col-1];
            }
            tvRow1.setText(String.valueOf(TC1));
        }else if(col==2){
            TC2=0;
            Count[row][column]=temp;
            for(int i=0;i<10;i++){
                TC2+=Count[i][col-1];
            }
            tvRow2.setText(String.valueOf(TC2));
        }else if(col==3){
            TC3=0;
            Count[row][column]=temp;
            for(int i=0;i<10;i++){
                TC3+=Count[i][col-1];
            }
            tvRow3.setText(String.valueOf(TC3));
        }else if(col==4){
            TC4=0;
            Count[row][column]=temp;
            for(int i=0;i<10;i++){
                TC4+=Count[i][col-1];
            }

            tvRow4.setText(String.valueOf(TC4));
        }else if(col==5){
            TC5=0;
            Count[row][column]=temp;
            for(int i=0;i<10;i++){
                TC5+=Count[i][col-1];
            }

            tvRow5.setText(String.valueOf(TC5));
        }else if(col==6){
            TC6=0;
            Count[row][column]=temp;
            for(int i=0;i<10;i++){
                TC6+=Count[i][col-1];
            }

            tvRow6.setText(String.valueOf(TC6));
        }else if(col==7){
            TC7=0;
            Count[row][column]=temp;
            for(int i=0;i<10;i++){
                TC7+=Count[i][col-1];
            }

            tvRow7.setText(String.valueOf(TC7));
        }else if(col==8){
            TC8=0;
            Count[row][column]=temp;
            for(int i=0;i<10;i++){
                TC8+=Count[i][col-1];
            }

            tvRow8.setText(String.valueOf(TC8));
        }else if(col==9){
            TC9=0;
            Count[row][column]=temp;
            for(int i=0;i<10;i++){
                TC9+=Count[i][col-1];
            }

            tvRow9.setText(String.valueOf(TC9));
        }else if(col==10){
            TC10=0;
            Count[row][column]=temp;
            for(int i=0;i<10;i++){
                TC10+=Count[i][col-1];
            }

            tvRow10.setText(String.valueOf(TC10));
        }else if(col==11){
            TC11=0;
            Count[row][column]=temp;
            for(int i=0;i<10;i++){
                TC11+=Count[i][col-1];
            }
            tvRow11.setText(String.valueOf(TC11));
        }else if(col==12){
            TC12=0;
            Count[row][column]=temp;
            for(int i=0;i<10;i++){
                TC12+=Count[i][col-1];
            }

            tvRow12.setText(String.valueOf(TC12));
        }

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
