package com.example.codnate3.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.codnate3.R;
import com.example.codnate3.net.getImage;
import com.example.codnate3.net.getimage2;
import com.example.codnate3.object.Bitmap_set;
import com.example.codnate3.object.Path_List;
import com.example.codnate3.object.Path_set;

public class closet extends AppCompatActivity {
    getImage task;
    String url = "http://3.133.83.204/tanuki/getImage?UserNo=";
    ImageView images[];
    String[] path_array;
    String path;
    private  int j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences data = getSharedPreferences("DATA",MODE_PRIVATE);
        int userNo = data.getInt("userNo",0);
        url = url + userNo;
        System.out.println(userNo);

        setContentView(R.layout.activity_closet);
        Button addButton = findViewById(R.id.addButton);
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
        images = images2;

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),camera.class);
                startActivity(intent);
            }
        });


        task = new getImage();
        task.setListener(createListner());
        task.execute(url);
    }

    private getImage.Listener createListner(){
        return new getImage.Listener() {
            @Override
            public void onSuccess(Path_List pathlist) {
                path_array = pathlist.path_list;

                for(int i = 0;i<path_array.length && i < 9;i++){
                    Path_set path_set = new Path_set(path_array[i],i);
                    getimage2 task2 = new getimage2();
                    task2.setListener(createListner2());
                    task2.execute(path_set);
                    j = i;
                    images[i].setOnClickListener(new View.OnClickListener() {
                        int path_idx = j;
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(),detail.class);
                            intent.putExtra("path",path_array[path_idx]);
                            startActivity(intent);
                        }


                    });


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
