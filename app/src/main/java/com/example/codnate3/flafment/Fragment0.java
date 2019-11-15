package com.example.codnate3.flafment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.codnate3.R;


public class Fragment0 extends Fragment {
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle saveInstanceState){
        rootView = inflater.inflate(R.layout.activity_camera,container,false);

        return rootView;
    }
}
