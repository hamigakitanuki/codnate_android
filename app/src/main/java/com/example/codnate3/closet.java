package com.example.codnate3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class closet extends AppCompatActivity {
    getImage task;
    String[] Pathlist;
    String url = "http://3.133.83.204/tanuki/getImage?UserNo=1";
    ImageView images[];
    private  int j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_closet);
        ImageButton huku1 = findViewById(R.id.huku1);
        ImageButton huku2 = findViewById(R.id.huku2);
        ImageButton huku3 = findViewById(R.id.huku3);
        ImageButton huku4 = findViewById(R.id.huku4);
        ImageButton huku5 = findViewById(R.id.huku5);
        ImageButton huku6 = findViewById(R.id.huku6);
        ImageButton huku7 = findViewById(R.id.huku7);
        ImageButton huku8 = findViewById(R.id.huku8);
        ImageButton huku9 = findViewById(R.id.huku9);
        ImageView[] images2 = {huku1,huku2,huku3,huku4,huku5,huku6,huku7,huku8,huku9};
        for(int i=0;i< images2.length;i++){
            j = i;
            images2[i].setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),detail.class);
                    intent.putExtra("path",Pathlist[j]);
                    System.out.println("Pathlist["+j+"]"+Pathlist[j]);
                    startActivity(intent);
                }
            });
        }
        images = images2;
        task = new getImage();
        task.setListener(createListner());
        task.execute(url);
    }

    private getImage.Listener createListner(){
        return new getImage.Listener() {
            @Override
            public void onSuccess(String[] pathlist) {
                Pathlist = pathlist;
                for(int i = 0;i<pathlist.length && i < 9;i++){
                    Path_set path_set = new Path_set(pathlist[i],i);
                    getimage2 task2 = new getimage2();
                    task2.setListener(createListner2());
                    task2.execute(path_set);
                }

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
                images[bmp.idx].setImageBitmap(bitmap_rotate);
            }
        };
    }
}
