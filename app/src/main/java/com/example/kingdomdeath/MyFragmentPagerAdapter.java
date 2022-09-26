package com.example.kingdomdeath;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    //FragmentManager is deprecated - figure out how to fix this
    public MyFragmentPagerAdapter(FragmentManager fm, Context context){
        super(fm);
        this.context = context;

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
                return new SurvivalFragment().newInstance(1);
            case 1:
                return new ArmorFragment().newInstance(2);
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
