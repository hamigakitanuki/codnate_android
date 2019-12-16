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

import com.example.codnate3.Huku_Chager;
import com.example.codnate3.R;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;


public class Closet_image_button extends Fragment {
    Bitmap bmp;
    String sub;
    String color;
    String cate;
    public Closet_image_button(Bitmap bmp,String sub,String cate,String color){
        this.sub = sub;
        this.color = color;
        this.bmp = bmp;
        this.cate = cate;
        System.out.println("Closet_button 33->"+color);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_closet_image_button, container, false);
        PorterShapeImageView porterShapeImageView = view.findViewById(R.id.closet_image_button);
        porterShapeImageView.setImageBitmap(bmp);

        Huku_Chager huku_chager = new Huku_Chager();
        TextView textView = view.findViewById(R.id.closet_image_button_text);
        textView.setText(huku_chager.color_to_text(color)+"の"+huku_chager.sub_to_text(cate,sub));
        return view;
    }
}
