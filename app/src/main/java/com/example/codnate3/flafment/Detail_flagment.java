package com.example.codnate3.flafment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.codnate3.R;

public class Detail_flagment extends Fragment {
    View rootView;
    public static Fragment newInstance(String path){
        Fragment fragment = new Detail_flagment();
        Bundle args = new Bundle();
        args.putString("path",path);
        fragment.setArguments(args);
        return  fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle saveInstanceState){
        rootView = inflater.inflate(R.layout.activity_detail, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
