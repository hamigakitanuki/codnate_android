package com.example.codnate3.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codnate3.Huku_Chager;
import com.example.codnate3.R;
import com.example.codnate3.net.ImageDelete;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;


public class Card_closet_image extends Fragment {
    Bitmap bmp;
    String sub;
    String color;
    String cate;
    String path;

    View view;
    public Card_closet_image(Bitmap bmp, String sub, String cate, String color, String path){
        this.sub = sub;
        this.color = color;
        this.bmp = bmp;
        this.cate = cate;
        this.path = path;
        System.out.println("Closet_button 33->"+color);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_closet_image_button, container, false);
        PorterShapeImageView porterShapeImageView = view.findViewById(R.id.closet_image);
        porterShapeImageView.setImageBitmap(bmp);

        Huku_Chager huku_chager = new Huku_Chager();
        TextView textView = view.findViewById(R.id.closet_image_button_text);
        textView.setText(huku_chager.color_to_text(color)+"の"+huku_chager.sub_to_text(cate,sub));

        ImageButton imageButton = view.findViewById(R.id.closet_image_button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageDelete imageDelete = new ImageDelete();
                imageDelete.setListener(create_ImageDelete_task());
                imageDelete.execute(path);



            }
        });
        return view;
    }


    public ImageDelete.Listener create_ImageDelete_task(){
        return new ImageDelete.Listener() {
            @Override
            public void onSuccess(String result_text) {
                ToastMake("削除しました",0,-200);

            }
        };
    }
    public void ToastMake(String message,int x,int y){
        //トーストの宣言
        Context context = getActivity().getApplicationContext();
        Toast toast = Toast.makeText(getActivity(),message,Toast.LENGTH_LONG);
        //位置調整
        toast.setGravity(Gravity.CENTER,x,y);
        toast.show();

    }

}
