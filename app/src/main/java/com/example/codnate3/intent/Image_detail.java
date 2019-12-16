package com.example.codnate3.intent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.codnate3.R;
import com.example.codnate3.net.ImageDelete;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;


public class Image_detail extends Activity {


    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Image_detail 35->");
        setContentView(R.layout.fragment_image_detail);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        Bitmap bmp = (Bitmap)b.get("bitmap");
        path = b.getString("path");
        ImageView imageView = findViewById(R.id.detail_image);
        imageView.setImageBitmap(bmp);
        Button backbutton = findViewById(R.id.detail_back_button);
        Button delete = findViewById(R.id.detail_delete_button);
        Button backgroud_button = findViewById(R.id.backgroud_button);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageDelete imageDelete = new ImageDelete();
                imageDelete.setListener(create_ImageDelete_task());
                imageDelete.execute(path);
            }
        });
        backgroud_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public ImageDelete.Listener create_ImageDelete_task(){
        return new ImageDelete.Listener() {
            @Override
            public void onSuccess(String result_text) {
                ToastMake("削除しました",0,-200);
                finish();
            }
        };
    }
    public void ToastMake(String message,int x,int y){
        //トーストの宣言
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(this,message,Toast.LENGTH_LONG);
        //位置調整
        toast.setGravity(Gravity.CENTER,x,y);
        toast.show();

    }

}
