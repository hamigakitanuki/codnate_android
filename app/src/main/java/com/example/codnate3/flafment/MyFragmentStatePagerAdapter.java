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
                return new Codnate_fragment();
            case 1:
                return new Closet_fragment();
            case 2:
                return new Local_recomend();
            case 3:
                return new Web_recomend();
        }
        return  null;
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
        }
        return null;
    }
}
