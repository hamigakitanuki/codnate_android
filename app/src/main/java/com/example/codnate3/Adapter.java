package com.example.codnate3;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Adapter extends FragmentPagerAdapter {
    public Adapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int i){
        switch(i){
            case 0:
            return new Fragment0();
            case 1:
            return new Fragment1();
            default:
            return new Fragment2();
        }
    }
    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "画面1";
            case 1:
                return "画面2";
            default:
                return "画面3";

        }
    }

}
