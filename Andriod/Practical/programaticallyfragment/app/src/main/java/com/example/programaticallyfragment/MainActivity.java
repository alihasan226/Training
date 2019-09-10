package com.example.programaticallyfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment();
    }

    public void addFragment()
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        SampleFragment sample1=new SampleFragment();
        SampleFragment1 sample2=new SampleFragment1();
        fragmentTransaction.add(R.id.framelayout,sample1);
        fragmentTransaction.add(R.id.framelayout1,sample2);
        fragmentTransaction.commit();
    }

}
