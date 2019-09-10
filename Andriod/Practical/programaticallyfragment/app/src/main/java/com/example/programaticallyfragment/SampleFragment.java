package com.example.programaticallyfragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

public class SampleFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState)
    {
        return inflater.inflate(R.layout.sample,container,false);
    }
}
