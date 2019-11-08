package com.example.codnate3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.ImageView;

public class detail extends Activity {
    ImageView detail_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detail_image = findViewById(R.id.detail_image);
        Intent intent  = getIntent();
        String path = intent.getStringExtra("path");
        System.out.println(path);
        Path_set path_set = new Path_set(path,0);
        getimage2 task2 = new getimage2();
        task2.setListener(createListner2());
        task2.execute(path_set);


    }
    private getimage2.Listener createListner2(){
        return  new getimage2.Listener() {
            @Override
            public void onSuccess(Bitmap_set bmp) {
                int imageWidth = bmp.bmp.getWidth();
                int imageHeight = bmp.bmp.getHeight();

                Matrix matrix = new Matrix();
                matrix.setRotate(90,imageWidth/2,imageHeight/2);

                Bitmap bitmap_rotate = Bitmap.createBitmap(bmp.bmp,0,0,imageWidth,imageHeight,matrix,true);
                detail_image.setImageBitmap(bitmap_rotate);
            }
        };
    }
}
