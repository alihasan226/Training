package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Second extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup continer, Bundle saveInstanceState)
    {
        return inflater.inflate(R.layout.second,continer,false);
    }
}
