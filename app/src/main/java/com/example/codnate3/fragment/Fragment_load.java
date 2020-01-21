package com.example.codnate3.fragment;

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

import com.example.codnate3.R;

public class Fragment_load extends Fragment {

    public Fragment_load() {
        // Required empty public constructor
    }


    public static Fragment_load newInstance() {
        Fragment_load fragment = new Fragment_load();

        return fragment;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("ロード中");
        ImageView imageView = view.findViewById(R.id.load_tanuki);
        Animation animation = AnimationUtils.loadAnimation(this.getActivity(), R.anim.tanuki_load_animato);
        imageView.startAnimation(animation);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_load__flagment, container, false);

    }



    @Override
    public void onDetach() {


        super.onDetach();
    }


}

