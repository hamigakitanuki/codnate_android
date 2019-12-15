package com.example.codnate3.flafment;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.codnate3.AWS_INTERFACE;
import com.example.codnate3.R;
import com.example.codnate3.net.Get_closet_image;
import com.example.codnate3.net.Get_image_list;
import com.example.codnate3.object.Path_List;
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
                Get_image_list task = new Get_image_list();
                task.setListener(get_task());
                task.execute(pathlist.path_list);
            }
        };
    }

    private Get_image_list.Listener get_task() {
        return  new Get_image_list.Listener() {
            @Override
            public void onSuccess(Bitmap[] bmp) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                System.out.println(bmp.length);
                for(int j = 0;j<bmp.length;j++){
                    if(bmp[j] == null){
                        break;
                    }
                    if((j+1)% 2 == 0){
                        Closet_image_button closet_image_button  = new Closet_image_button(bmp[j]);
                        fragmentTransaction.add(R.id.closet_vertical_right,closet_image_button);
                    }
                    Closet_image_button closet_image_button  = new Closet_image_button(bmp[j]);
                    fragmentTransaction.add(R.id.closet_vertical_left,closet_image_button);

                }

                for(int i=0;i<bmp.length;i++){
                    if(bmp[i] == null){
                        break;
                    }
                    add fragment = new add(bmp[i]);
                    fragmentTransaction.add(R.id.tag_add_list_layout,fragment);
                }
                fragmentTransaction.commit();

            }
        };
    }


    private void setupPieChart(Path_List path_list){
        List<PieEntry> pieEntries = new ArrayList<PieEntry>();
        if(path_list.dress > 0){
            pieEntries.add(new PieEntry(path_list.dress,"ドレス"));
        }
        if(path_list.casual > 0){
            pieEntries.add(new PieEntry(path_list.casual,"カジュアル"));
        }
        if(path_list.simple > 0){
            pieEntries.add(new PieEntry(path_list.simple,"シンプル"));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries,"あなたの属性");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        PieChart pieChart = rootView.findViewById(R.id.pie_chart);
        pieChart.setData(data);
        pieChart.invalidate();
    }

}

