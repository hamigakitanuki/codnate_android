package com.example.codnate3.flafment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.codnate3.R;
import com.example.codnate3.intent.detail;
import com.example.codnate3.net.getImage;
import com.example.codnate3.net.getimage2;
import com.example.codnate3.object.Bitmap_set;
import com.example.codnate3.object.Path_List;
import com.example.codnate3.object.Path_set;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Fragment1 extends Fragment {
    View rootView;
    getImage task;
    String url = "http://3.133.83.204/tanuki/getImage?UserNo=";
    ImageView images[];
    String[] path_array;
    String path;
    final public static int DETAIL_RESULT_CODE = 55;
    private  int j;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_closet, container, false);
        return rootView;

    }
    @Override
    public void onViewCreated(@NonNull View view,Bundle savedInstanceState) {
        SharedPreferences data = this.getActivity().getSharedPreferences("DATA",MODE_PRIVATE);
        int userNo = data.getInt("userNo",0);
        url = url + userNo;
        System.out.println(userNo);

        ImageButton huku1 = view.findViewById(R.id.huku1);
        ImageButton huku2 = view.findViewById(R.id.huku2);
        ImageButton huku3 = view.findViewById(R.id.huku3);
        ImageButton huku4 = view.findViewById(R.id.huku4);
        ImageButton huku5 = view.findViewById(R.id.huku5);
        ImageButton huku6 = view.findViewById(R.id.huku6);
        ImageButton huku7 = view.findViewById(R.id.huku7);
        ImageButton huku8 = view.findViewById(R.id.huku8);
        ImageButton huku9 = view.findViewById(R.id.huku9);
        ImageView[] images2 = {huku1,huku2,huku3,huku4,huku5,huku6,huku7,huku8,huku9};
        images = images2;




        task = new getImage();
        task.setListener(createListner());
        task.execute(url);
    }

    private getImage.Listener createListner(){

        return new getImage.Listener() {

            @Override
            public void onSuccess(Path_List pathlist) {
                path_array = pathlist.path_list;
                setupPieChart(pathlist);

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
                            Intent intent = new Intent(getActivity(),com.example.codnate3.intent.detail.class);
                            intent.putExtra("path",path_array[path_idx]);
                            startActivityForResult(intent,DETAIL_RESULT_CODE);
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
    private void setupPieChart(Path_List path_list){
        List<PieEntry> pieEntries = new ArrayList<PieEntry>();
        if(path_list.kawaii > 0){
            pieEntries.add(new PieEntry(path_list.kawaii,"カワイイ"));
        }
        if(path_list.cool > 0){
            pieEntries.add(new PieEntry(path_list.cool,"クール"));
        }
        if(path_list.simple > 0){
            pieEntries.add(new PieEntry(path_list.simple,"シンプル"));
        }
        if(path_list.adult > 0){
            pieEntries.add(new PieEntry(path_list.adult,"セクシー"));
        }
        PieDataSet dataSet = new PieDataSet(pieEntries,"あなたの属性");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        PieChart pieChart = rootView.findViewById(R.id.pie_chart);
        pieChart.setData(data);
        pieChart.invalidate();
    }

}

