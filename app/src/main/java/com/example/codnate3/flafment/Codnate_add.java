package com.example.codnate3.flafment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.codnate3.R;
import com.example.codnate3.net.Get_image;

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

        for(int i=0;i<3;i++){
            codnate_idx = i;
            Get_image task = new Get_image();
            task.setListener(get_image_task());
            task.execute(path[i]);
        }

        return view;
    }

    private Get_image.Listener get_image_task(){
        return new Get_image.Listener() {
            @Override
            public void onSuccess(Bitmap bmp) {
                codnate[codnate_idx].setImageBitmap(bmp);
            }
        };

    }
}
