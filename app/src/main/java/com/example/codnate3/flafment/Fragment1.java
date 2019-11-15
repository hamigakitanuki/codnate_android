package com.example.codnate3.flafment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.codnate3.R;

public class Fragment1 extends Fragment {
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_closet, container, false);
        return rootView;
    }
    @Override
    public void onViewCreated(@NonNull View view,Bundle savedInstanceState) {

    }
}
