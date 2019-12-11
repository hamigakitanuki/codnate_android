package com.example.codnate3.flafment;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.codnate3.R;
import com.example.codnate3.net.GetCodenate;
import com.example.codnate3.net.getimage2;
import com.example.codnate3.object.Bitmap_set;
import com.example.codnate3.object.Codenate_path_list;
import com.example.codnate3.object.Path_set;

import static android.content.Context.MODE_PRIVATE;


public class Fragment0 extends Fragment {
    LinearLayout linearLayout;
    View view;
    String path;
    ImageView images[];
    boolean sw = true;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle saveInstanceState){


        view = inflater.inflate(R.layout.activity_main,container,false);
        if(sw){
            SharedPreferences data = this.getActivity().getSharedPreferences("DATA", MODE_PRIVATE);
            int userNo = data.getInt("userNo", 0);
            TextView tops_color = view.findViewById(R.id.tops_color1);
            tops_color.setTextColor(Color.rgb(100,100,100));
            ImageView tops = view.findViewById(R.id.main_tops_1);
            ImageView botoms = view.findViewById(R.id.main_botoms1);
            ImageView shoese = view.findViewById(R.id.main_shoese1);
            ImageView outer = view.findViewById(R.id.main_outer1);
            ImageView[] images2 = {tops, botoms, outer, shoese};
            images = images2;

            GetCodenate task = new GetCodenate();
            task.setListener(createListner());
            task.execute(String.valueOf(userNo));

            linearLayout = getActivity().findViewById(R.id.fragment_codnate_liner);
            linearLayout.setVisibility(View.INVISIBLE);
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.load_tanuki, Load_Flagment.newInstance()).commit();
            sw = false;

            LinearLayout linearLayout = view.findViewById(R.id.tag_add_lisit_layout);

            int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
            for(int i = 0;i<3;i++){
                System.out.println(linearLayout);
                View view = getLayoutInflater().inflate(R.layout.add_frame_layout,null);
                linearLayout.addView(view);
            }
        }

        return view;

    }


    @Override
    public void onViewCreated(@NonNull View view,Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

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
                linearLayout.setVisibility(View.VISIBLE);
                FrameLayout frameLayout = getActivity().findViewById(R.id.load_tanuki);
                frameLayout.setVisibility(View.INVISIBLE);
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

