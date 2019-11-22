package com.oasissnacks.oasissnacks.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.databinding.FragmentDescriptionBinding;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;


public class DecriptionFragment extends Fragment {
    public FragmentDescriptionBinding binding;
    AppUser appUser=new AppUser();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_description,container,false);
        appUser= LocalRepositories.getAppUser(getActivity().getApplicationContext());
        if(appUser!=null&&!appUser.disclamir.equalsIgnoreCase(""))
        {
            binding.webViewMDescription.loadData(appUser.decription,"text/html","UTf-8");
        }
        return binding.getRoot();

    }
}
