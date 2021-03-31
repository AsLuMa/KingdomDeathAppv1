package com.example.kingdomdeath;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    //FragmentManager is deprecated - figure out how to fix this
    public MyFragmentPagerAdapter(FragmentManager fm){
        super(fm);

    }

    @Override
    public int getCount() {
        return 3;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new SurvivalFragment();
            case 1:
                return new ArmorFragment();
            case 2:
                return new AttributeFragment();
            default:
                return null;
        }
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //return "Tab Pagetitle";
        return super.getPageTitle(position);
    }
}
