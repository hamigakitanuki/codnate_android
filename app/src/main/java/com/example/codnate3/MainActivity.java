package com.example.codnate3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codnate3.intent.Start1;
import com.example.codnate3.intent.Swaip;
import com.example.codnate3.intent.camera;
import com.example.codnate3.intent.closet;
import com.example.codnate3.net.GetCodenate;
import com.example.codnate3.net.getimage2;
import com.example.codnate3.object.Bitmap_set;
import com.example.codnate3.object.Codenate_path_list;
import com.example.codnate3.object.Path_set;

public class MainActivity extends AppCompatActivity {
    final int StartResultCode = 1;
    String path;
    ImageView images[];
    int j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        SharedPreferences data = getSharedPreferences("DATA", MODE_PRIVATE);
        int userNo = data.getInt("userNo", 0);
        if (userNo == 0) {
            Intent intent = new Intent(getApplication(), Start1.class);
            startActivity(intent);
        }


        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.addButton);
        ImageButton swaip_move_button = findViewById(R.id.calendar);
        ImageView tops = findViewById(R.id.main_tops_1);
        ImageView botoms = findViewById(R.id.main_botoms1);
        ImageView shoese = findViewById(R.id.main_shoese1);
        ImageView outer = findViewById(R.id.main_outer1);
        ImageView[] images2 = {tops, botoms, outer, shoese};
        images = images2;

        swaip_move_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),Swaip.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, camera.class);
                startActivity(intent);
            }
        });
        ImageButton button1 = findViewById(R.id.closetbutton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, closet.class);
                startActivity(intent);
            }
        });
        GetCodenate task = new GetCodenate();
        task.setListener(createListner());
        task.execute(String.valueOf(userNo));

    }
    private GetCodenate.Listener createListner(){
        return new GetCodenate.Listener() {
            @Override
            public void onSuccess(Codenate_path_list pathlist) {
                if(pathlist == null){

                }else {
                    for (int i = 0; i <= 3; i++) {
                        path = pathlist.get_path(i, 0);
                        if (path == "") continue;
                        Path_set path_set = new Path_set(path, i);
                        getimage2 task2 = new getimage2();
                        task2.setListener(createListner2());
                        task2.execute(path_set);
                    }
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
