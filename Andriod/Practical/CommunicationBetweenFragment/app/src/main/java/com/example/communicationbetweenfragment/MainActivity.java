package com.example.communicationbetweenfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements fragmentone.buttonclick
{

    private fragmentone frag1;
    private fragmenttwo frag2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        frag1=new fragmentone();
        frag2=new fragmenttwo();
        fragmentTransaction.add(R.id.framelayout1,frag1);
        fragmentTransaction.add(R.id.framelayout2,frag2);
        fragmentTransaction.commit();
    }

    @Override
    public void sendValue(String text) {
        frag2.updateedittext(text);
    }
}
