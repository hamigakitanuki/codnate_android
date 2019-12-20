package com.example.codnate3.flafment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.codnate3.R;

public class Fragment2 extends Fragment {
    View rootView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_bunseki,container,false);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        Card_fragment_canpain canpain = new Card_fragment_canpain(0);
        fragmentTransaction.add(R.id.market_layout,canpain);
        Card_fragment_osusume osusume = new Card_fragment_osusume();
        fragmentTransaction.add(R.id.market_layout,osusume);
        Card_fragment_hokanimo hokanimo = new Card_fragment_hokanimo();
        fragmentTransaction.add(R.id.market_layout,hokanimo);
        fragmentTransaction.commit();
        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


    }

}
