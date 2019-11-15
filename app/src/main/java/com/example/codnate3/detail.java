package com.example.codnate3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class detail extends Activity {
    ImageView detail_image;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Button delete_button = findViewById(R.id.detail_delete_button);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageDelete delete_task = new ImageDelete();
                delete_task.setListener(createListner());
                delete_task.execute(path);
            }
        });
        detail_image = findViewById(R.id.detail_image);
        path = getIntent().getStringExtra("path");
        Path_set path_set = new Path_set(path, 0);
        getimage2 task2 = new getimage2();
        task2.setListener(createListner2());
        task2.execute(path_set);
    }

    private ImageDelete.Listener createListner(){
        return new ImageDelete.Listener() {
            @Override
            public void onSuccess(String result_text) {
                Toast.makeText(getApplication(),result_text,Toast.LENGTH_LONG);
                finish();
            }
        };
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
