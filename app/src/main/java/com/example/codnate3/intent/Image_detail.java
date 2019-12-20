package com.example.codnate3.intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.codnate3.R;
import com.example.codnate3.net.Get_image;
import com.example.codnate3.net.ImageDelete;
import com.example.codnate3.object.Bitmap_set;
import com.example.codnate3.object.Path_set;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;


public class Image_detail extends Fragment {


    private String path;
    private View view;


    public Image_detail(String path){
        this.path = path;
    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle saveInstanceState){
        view = inflater.inflate(R.layout.fragment_image_detail,container,false);
        Button backbutton = view.findViewById(R.id.detail_back_button);
        Button delete = view.findViewById(R.id.detail_delete_button);
        Button backgroud_button = view.findViewById(R.id.backgroud_button);

        Get_image get_image = new Get_image();
        get_image.setListener(get_image_task());
        Path_set path_set = new Path_set(path,0);
        get_image.execute(path_set);




        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageDelete imageDelete = new ImageDelete();
                imageDelete.setListener(create_ImageDelete_task());
                imageDelete.execute(path);
                view.setVisibility(View.INVISIBLE);
            }
        });
        backgroud_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
            }
        });



        return view;
    }



    public ImageDelete.Listener create_ImageDelete_task(){
        return new ImageDelete.Listener() {
            @Override
            public void onSuccess(String result_text) {
                onDestroyView();
            }
        };
    }
    Get_image.Listener get_image_task(){
        return new Get_image.Listener() {
            @Override
            public void onSuccess(Bitmap_set bmp) {

                ImageView imageView = view.findViewById(R.id.closet_detail_image);
                imageView.setImageBitmap(bmp.bmp);


            }
        };
    }


}
