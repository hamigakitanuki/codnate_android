package com.example.codnate3.fragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.codnate3.R;


public class Card_tag_add extends Fragment {

    View mView;
    Bitmap bmp = null;
    private OnFragmentInteractionListener mListener;

    public Card_tag_add(Bitmap bmp){
        this.bmp = bmp;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_tag_add, container, false);
        ImageView imageView = mView.findViewById(R.id.tanuki_kangaeru);
        imageView.setImageResource(R.drawable.kousatu_tanuki_2);
        ImageView huku_img = mView.findViewById(R.id.kousatu);
        huku_img.setImageBitmap(bmp);
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
