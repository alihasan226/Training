package com.example.communicationbetweenfragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.widget.TextView;

public class fragmenttwo extends Fragment{
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable  ViewGroup container, Bundle saveInstanceState)
    {
        View view=inflater.inflate(R.layout.layouttwo,container,false);
        textView=(TextView)view.findViewById(R.id.textview1);

        return view;
    }

    public void updateedittext(String newtext)
    {
        textView.setText(newtext);
    }
}
