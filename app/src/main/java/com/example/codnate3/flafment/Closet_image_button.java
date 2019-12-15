package com.example.codnate3.flafment;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.codnate3.R;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;


public class Closet_image_button extends Fragment {
    Bitmap bmp;
    public Closet_image_button(Bitmap bmp){
        this.bmp = bmp;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_closet_image_button, container, false);
        PorterShapeImageView porterShapeImageView = view.findViewById(R.id.closet_image_button);
        porterShapeImageView.setImageBitmap(bmp);
        TextView textView;
        return view;
    }
}
