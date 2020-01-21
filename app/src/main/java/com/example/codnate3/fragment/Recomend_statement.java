package com.example.codnate3.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class Recomend_statement extends FragmentPagerAdapter {


    public Recomend_statement(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Card_fragment_codnate_item_recomend("tops");
            case 1:
                return new Card_fragment_codnate_item_recomend("botoms");
            case 2:
                return new Card_fragment_codnate_item_recomend("shoese");
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
                return "トップスおすすめ";
            case 1:
                return "ボトムスおすすめ";
            case 2:
                return "シューズおすすめ";
        }
        return null;
    }
}
