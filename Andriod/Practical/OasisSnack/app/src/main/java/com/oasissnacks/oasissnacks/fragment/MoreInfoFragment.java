package com.oasissnacks.oasissnacks.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.oasissnacks.oasissnacks.R;
import com.oasissnacks.oasissnacks.adapter.AdapterMoreInfo;
import com.oasissnacks.oasissnacks.databinding.FragmentMoreinfoBinding;
import com.oasissnacks.oasissnacks.utils.AppUser;
import com.oasissnacks.oasissnacks.utils.LocalRepositories;

import java.util.ArrayList;
import java.util.List;

public class MoreInfoFragment extends Fragment {
    public FragmentMoreinfoBinding binding;
    public AppUser appUser=new AppUser();
    public List<String> list = new ArrayList<>();
    public List<String> tempList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_moreinfo, container, false);
        appUser = LocalRepositories.getAppUser(getActivity().getApplicationContext());
      if(appUser.stringList!=null) {
        list=appUser.stringList;

          binding.rvMoreInfo.setAdapter(new AdapterMoreInfo(list,getActivity().getApplicationContext()));
          binding.rvMoreInfo.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false));
      }


        return binding.getRoot();

    }
}
