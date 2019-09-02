package com.example.fragment_single;

import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;

public class PM_Fragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState)
    {
        return inflater.inflate(R.layout.pm_fragment,container,false);
    }
}
