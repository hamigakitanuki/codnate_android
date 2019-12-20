package com.example.codnate3.flafment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codnate3.Huku_Chager;
import com.example.codnate3.R;
import com.example.codnate3.intent.Image_detail;
import com.example.codnate3.net.Get_image;
import com.example.codnate3.net.ImageDelete;
import com.example.codnate3.object.Bitmap_set;
import com.example.codnate3.object.Path_set;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;


public class Closet_image_button extends Fragment {
    Bitmap bmp;
    String sub;
    String color;
    String cate;
    String path;

    View view;
    public Closet_image_button(Bitmap bmp,String sub,String cate,String color,String path){
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


                /*
                Image_detail image_detail = new Image_detail(path);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.detail_frame,image_detail);
                fragmentTransaction.commit();


                 */
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
