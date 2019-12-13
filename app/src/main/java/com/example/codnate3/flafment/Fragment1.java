package com.example.codnate3.flafment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.codnate3.AWS_INTERFACE;
import com.example.codnate3.R;
import com.example.codnate3.net.GetCodenate;
import com.example.codnate3.net.Get_closet_image;
import com.example.codnate3.net.Get_image_list;
import com.example.codnate3.net.getImage;
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
    String url = "http://"+ AWS_INTERFACE.IPADDRESS +"/tanuki/getImage?UserNo=";
    String path;
    final public static int DETAIL_RESULT_CODE = 55;
    private  int j;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_closet, container, false);
        SharedPreferences data = this.getActivity().getSharedPreferences("DATA",MODE_PRIVATE);
        int userNo = data.getInt("userNo",0);
        url = url + userNo;
        System.out.println(userNo);

        Get_closet_image task = new Get_closet_image();
        task.setListener(createListner());
        task.execute(url);
        return rootView;

    }

    private Get_closet_image.Listener createListner() {
        return  new Get_closet_image.Listener() {
            @Override
            public void onSuccess(Path_List pathlist) {
                Get_image_list tops_task = new Get_image_list();
                tops_task.setListener(get_task());
                tops_task.execute(pathlist.path_list);
            }
        };
    }

    private Get_image_list.Listener get_task() {
        return  new Get_image_list.Listener() {
            @Override
            public void onSuccess(Bitmap[] bmp) {
                LinearLayout closet_vertical = rootView.findViewById(R.id.closet_vertical);
                LinearLayout closet_horizontal = null;
                ImageView imageView = null;
                View view;
                view = View.inflate(getContext(),R.layout.closet_liner_h,null);

                for(int j = 0;j<bmp.length;j++){
                    if(j%4 == 0){
                        closet_horizontal = view.findViewById(R.id.closet_horizontal);
                    }imageView.setImageBitmap(bmp[j]);
                    closet_horizontal.addView(imageView);
                }
                closet_vertical.addView(closet_horizontal);
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

