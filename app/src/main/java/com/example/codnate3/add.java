package com.example.codnate3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;



public class add extends Fragment {

    View mView;
    private OnFragmentInteractionListener mListener;

    public static add newInstance() {
        System.out.println("addメソッドが呼び出された");
        add fragment = new add();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_add, container, false);
        ImageView imageView = mView.findViewById(R.id.tanuki_kangaeru);
        imageView.setImageResource(R.drawable.kousatu_tanuki_2);
        Button tag1 = mView.findViewById(R.id.tag1);
        Button tag2 = mView.findViewById(R.id.tag2);
        Button tag3 = mView.findViewById(R.id.tag3);
        tag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView1 = mView.findViewById(R.id.tanuki_kangaeru);
                imageView1.setImageResource(R.drawable.tanuki_hirameki);
            }
        });
        tag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView1 = mView.findViewById(R.id.tanuki_kangaeru);
                imageView1.setImageResource(R.drawable.tanuki_hirameki);
            }
        });
        tag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView1 = mView.findViewById(R.id.tanuki_kangaeru);
                imageView1.setImageResource(R.drawable.tanuki_hirameki);
            }
        });
        return mView;
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
