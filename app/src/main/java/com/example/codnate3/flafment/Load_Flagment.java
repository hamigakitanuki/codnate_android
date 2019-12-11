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

import com.example.codnate3.R;

public class Load_Flagment extends Fragment {

    public Load_Flagment() {
        // Required empty public constructor
    }


    public static Load_Flagment newInstance() {
        Load_Flagment fragment = new Load_Flagment();
        Bundle args = new Bundle();

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

