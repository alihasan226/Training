package com.oasissnacks.oasissnacks.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.oasissnacks.oasissnacks.fragment.DecriptionFragment;
import com.oasissnacks.oasissnacks.fragment.DisclamierFragment;
import com.oasissnacks.oasissnacks.fragment.MoreInfoFragment;

public class VIewPagerAdapter extends FragmentPagerAdapter {
    public VIewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new DecriptionFragment();
                break;
            case 1:
                fragment = new DisclamierFragment();
                break;
            case 2:
                fragment = new MoreInfoFragment();
                break;
                default:break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "Description";
        } else if (position == 1) {
            title = "Disclaimer";
        } else if (position == 2) {
            title = "More Information";
        }
        return title;
    }
}
