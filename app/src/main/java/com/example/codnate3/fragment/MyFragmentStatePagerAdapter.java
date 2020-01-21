package com.example.codnate3.fragment;

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
                return new Fragment_codnate();
            case 1:
                return new Fragment_closet();
            case 2:
                return new Fragment_Web_recomend();
            case 3:
                return new Fragment_local_recomend();
            default:
                return new Fragment_codnate();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int postion){
        switch (postion){
            case 0:
                return "今日のコーデ";
            case 1:
                return "クローゼット";
            case 2:
                return "近くのお店のおすすめ商品";
            case 3:
                return "オンラインストアのおすすめ商品";
            default:
                return "ああああ";
        }

    }
}
