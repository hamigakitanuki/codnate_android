package com.example.codnate3.flafment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyFragmentStatePagerAdapter extends FragmentPagerAdapter {


    public MyFragmentStatePagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment0();
            case 1:
                return new Fragment1();
            case 2:
                return new Fragment2();
        }
        return  null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int postion){
        switch (postion){
            case 0:
                return "画面１";
            case 1:
                return "画面２";
            case 2:
                return "画面３";
        }
        return null;
    }
}
