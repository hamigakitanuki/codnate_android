package com.example.codnate3.fragment;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

public class Fragment_closet extends Fragment {
    View rootView;
    private String path;
    private float dress = 0;
    private float casual = 0;
    private float simple = 0;
    private Path_List mpathlist;
    private int userNo;
    final public static int DETAIL_RESULT_CODE = 55;
    private  int j;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_closet, container, false);
        SharedPreferences data = this.getActivity().getSharedPreferences("DATA",MODE_PRIVATE);
        userNo = data.getInt("userNo",0);

        Get_closet_image task = new Get_closet_image();
        task.setListener(createListner());
        task.execute(String.valueOf(userNo));

        SwipeRefreshLayout swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LinearLayout closet_left = rootView.findViewById(R.id.closet_vertical_left);
                LinearLayout closet_right = rootView.findViewById(R.id.closet_vertical_right);
                LinearLayout tag_add_list = rootView.findViewById(R.id.tag_add_list_layout);
                closet_left.removeAllViews();
                closet_right.removeAllViews();
                tag_add_list.removeAllViews();

                Get_closet_image task = new Get_closet_image();
                task.setListener(createListner());
                task.execute(String.valueOf(userNo));


            }
        });


        return rootView;

    }

    private Get_closet_image.Listener createListner() {
        return  new Get_closet_image.Listener() {
            @Override
            public void onSuccess(Path_List pathlist) {
                SwipeRefreshLayout swipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout);

                if(swipeRefreshLayout.isRefreshing()){
                    swipeRefreshLayout.setRefreshing(false);
                }
                mpathlist = pathlist;
                Get_image_list task = new Get_image_list();
                task.setListener(get_task());
                task.execute(pathlist.path_list);
                dress = pathlist.dress / (pathlist.casual + pathlist.simple + pathlist.dress) * 100;
                casual = pathlist.casual / (pathlist.casual + pathlist.simple + pathlist.dress) * 100;
                simple = pathlist.simple / (pathlist.casual + pathlist.simple + pathlist.dress) * 100;
                setupPieChart();
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
                        Card_closet_image cardcloset_image_ = new Card_closet_image(bmp[j],mpathlist.sub_list[j],mpathlist.cate_list[j],mpathlist.color_list[j],mpathlist.path_list[j]);
                        fragmentTransaction.add(R.id.closet_vertical_right, cardcloset_image_);
                    }else {
                        Card_closet_image cardcloset_image_ = new Card_closet_image(bmp[j], mpathlist.sub_list[j], mpathlist.cate_list[j], mpathlist.color_list[j],mpathlist.path_list[j]);
                        fragmentTransaction.add(R.id.closet_vertical_left, cardcloset_image_);
                    }
                }
                int i = 0;
                for(i = 0;i<bmp.length;i++){
                    if(bmp[i] == null){
                        break;
                    }
                    Card_tag_add fragment = new Card_tag_add(bmp[i]);
                    fragmentTransaction.add(R.id.tag_add_list_layout,fragment);
                }
                if(i > 1 && isResumed()){
                    fragmentTransaction.commit();



                }

            }
        };
    }


    private void setupPieChart(){
        List<PieEntry> pieEntries = new ArrayList<PieEntry>();
        if(dress > 0){
            pieEntries.add(new PieEntry(dress,"ドレス"));
        }
        if(casual > 0){
            pieEntries.add(new PieEntry(casual,"カジュアル"));
        }
        if(simple > 0){
            pieEntries.add(new PieEntry(simple,"シンプル"));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries,"あなたの属性");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        PieChart pieChart = rootView.findViewById(R.id.pie_chart);
        pieChart.setData(data);

        pieChart.invalidate();
    }

}

