package com.example.codnate3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;



public class add extends Fragment {


    private OnFragmentInteractionListener mListener;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add, container, false);
        ImageView imageView = view.findViewById(R.id.tanuki_kangaeru);
        Button tag1 = view.findViewById(R.id.tag1);
        Button tag2 = view.findViewById(R.id.tag2);
        Button tag3 = view.findViewById(R.id.tag3);
        tag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageView)v.findViewById(R.id.tanuki_kangaeru)).setImageResource(R.drawable.tanuki_hirameki);
            }
        });
        tag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageView)v.findViewById(R.id.tanuki_kangaeru)).setImageResource(R.drawable.tanuki_hirameki);
            }
        });
        tag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageView)v.findViewById(R.id.tanuki_kangaeru)).setImageResource(R.drawable.tanuki_hirameki);
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
