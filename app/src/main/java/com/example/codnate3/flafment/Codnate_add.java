package com.example.codnate3.flafment;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.codnate3.R;
import com.example.codnate3.net.Get_image;
import com.example.codnate3.object.Bitmap_set;
import com.example.codnate3.object.Path_set;

public class Codnate_add extends Fragment {


    private final String path[];
    private ImageButton goodButton;
    private ImageButton badButton;
    private int codnate_idx;
    private ImageView codnate[] = new ImageView[3];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public Codnate_add(String path[]){
        this.path = path;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_codnate_add, container, false);
        goodButton = view.findViewById(R.id.good_button);
        badButton = view.findViewById(R.id.bad_button);

        //いいねボタン
        goodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodButton.setImageResource(R.drawable.gooded);
            }
        });
        //わるいねボタン
        badButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                badButton.setImageResource(R.drawable.baded);
            }
        });

        codnate[0] = view.findViewById(R.id.main_tops_1);
        codnate[1] = view.findViewById(R.id.main_botoms1);
        codnate[2] = view.findViewById(R.id.main_shoese1);
        Path_set path_set = null;
        for(int i=0;i<3;i++){
            path_set = new Path_set(path[i],i);
            Get_image task = new Get_image();
            task.setListener(get_image_task());
            task.execute(path_set);
        }

        TextView textView = view.findViewById(R.id.tops_color1);
        textView.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(),R.color.primaryColor)));
        return view;
    }

    private Get_image.Listener get_image_task(){
        return new Get_image.Listener() {
            @Override
            public void onSuccess(Bitmap_set bmp_set) {
                codnate[bmp_set.idx].setImageBitmap(bmp_set.bmp);
            }
        };

    }
}
