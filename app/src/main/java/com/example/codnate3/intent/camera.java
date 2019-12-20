package com.example.codnate3.intent;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Region;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.codnate3.Huku_Chager;
import com.example.codnate3.R;
import com.example.codnate3.flafment.Open_layout_animation;
import com.example.codnate3.flafment.Open_layout_animation_horizon;
import com.example.codnate3.net.GetCate;
import com.example.codnate3.net.GetColor;
import com.example.codnate3.net.GetSub;
import com.example.codnate3.net.GetTag;
import com.example.codnate3.net.GetType;
import com.example.codnate3.net.GetVol;
import com.example.codnate3.object.Bitmap_cate_set;
import com.example.codnate3.object.Huku_info;
import com.example.codnate3.net.ImagePOST;
import com.example.codnate3.object.ArrayPulldown;
import com.example.codnate3.object.Param;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class camera extends Activity {

    final int REQUEST_CAPTURE_IMAGE = 150;
    private ImagePOST task;
    private int userNo;
    private Bitmap capImage;
    private Button cameraButton,addButton,yosou1,yosou2;
    private int cate_frame_height,sub_frame_height,color_frame_height,type_frame_height,tag_frame_height,vol_frame_height;
    private ImageButton cameraImage;
    private LinearLayout camera_info,cate_frame,sub_frame,color_frame,type_frame,tag_frame,vol_frame;
    private Open_layout_animation open_cameraINFO_vertical;
    private Open_layout_animation open_cate_horizon,open_sub_horizon,open_color_horizon,open_type_horizon,open_tag_horizon,open_vol_horizon;
    private RelativeLayout add_frame;
    private Spinner cate_sp ,sub_sp,color_sp;
    private Param param;
    private String filename, cate_text,sub_text,color_text,vol;
    private String tag1 = "";
    private String tag2 = "";
    private String tag3 = "";
    private String tag4 = "";
    private int dress_value,casual_value,simple_value;
    private Huku_Chager huku_chager;
    private boolean sw = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        huku_chager = new Huku_Chager();

        //ボタンの配置
        cameraButton = findViewById(R.id.cameraButton);
        addButton = findViewById(R.id.huku_add);
        //プルダウンの設置
        cate_sp = findViewById(R.id.cate_spiner);
        sub_sp = findViewById(R.id.sub_spiner);
        color_sp = findViewById(R.id.color_spiner);
        yosou1 = findViewById(R.id.sub_yosou);
        yosou2 = findViewById(R.id.sub_yosou2);

        //画像の情報画面のレイアウト

        camera_info = findViewById(R.id.camera_info);
        cate_frame = findViewById(R.id.cate_frame);
        sub_frame = findViewById(R.id.sub_cate_frame);
        color_frame = findViewById(R.id.color_frame);
        type_frame = findViewById(R.id.type_frame);
        tag_frame = findViewById(R.id.tag_frame);
        vol_frame = findViewById(R.id.vol_frame);
        //一度透明にする
        camera_info.setVisibility(View.INVISIBLE);

        //撮影した写真の表示する場所兼ボタン
        cameraImage = findViewById(R.id.cameraImage);
        add_frame = findViewById(R.id.camera_add_frame);

        //カテゴリのスピナー
        cate_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner spinner = (Spinner)adapterView;
                cate_text = (String)spinner.getSelectedItem();
                cate_text = huku_chager.cate_text_to_eng(cate_text);
                System.out.println("camera 124-> cate_text:"+cate_text);
                ArrayAdapter<String> subAdapter;
                switch (cate_text){
                    case "tops":
                        subAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_spinner_item, ArrayPulldown.tops_sub);
                        sub_sp.setAdapter(subAdapter);

                        break;
                    case "botoms":
                        subAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_spinner_item,ArrayPulldown.botoms_sub);
                        sub_sp.setAdapter(subAdapter);

                        break;
                    case "onepeace":
                        subAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_spinner_item,ArrayPulldown.onepeace_sub);
                        sub_sp.setAdapter(subAdapter);

                        break;
                    case "outer":
                        subAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_spinner_item,ArrayPulldown.outer_sub);
                        sub_sp.setAdapter(subAdapter);

                        break;
                    case "shoese":
                        subAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_spinner_item,ArrayPulldown.shoese_sub);
                        sub_sp.setAdapter(subAdapter);

                        break;
                    default:
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //サブカテゴリのスピナー
        sub_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner spiner = (Spinner)adapterView;
                sub_text = (String)spiner.getSelectedItem();
                System.out.println("camera167->"+cate_text+","+sub_text);
                sub_text = huku_chager.sub_text_to_eng(cate_text,sub_text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //カラーのスピナー
        color_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner spinner = (Spinner)adapterView;
                color_text = (String)spinner.getSelectedItem();
                color_text = huku_chager.color_text_to_eng(color_text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //保存しているデータからuserNoを持ってくる
        SharedPreferences data = getSharedPreferences("DATA",MODE_PRIVATE);
        userNo = data.getInt("userNo",0);
        //現在のカメラの権限を取得
        int permissionCheck = ContextCompat.checkSelfPermission(camera.this,Manifest.permission.CAMERA);
        //許可されているか確認
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            //許可されていない場合
            final int REQUEST_CODE = 1;
            ActivityCompat.requestPermissions(camera.this,new String[]{Manifest.permission.CAMERA},REQUEST_CODE);
        }
        //カメラ起動のリスナー
        cameraButton.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //カメラのインテントを作成
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //カメラのインテントを起動し結果を取得
                startActivityForResult(intent,REQUEST_CAPTURE_IMAGE);
            }
        });
        cameraImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //カメラのインテントを作成
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //カメラのインテントを起動し結果を取得
                startActivityForResult(intent,REQUEST_CAPTURE_IMAGE);
            }
        });
        //登録ボタンの処理部
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cate_text.equals("")){
                    Toast.makeText(getApplication(),"カテゴリを選択してください！",Toast.LENGTH_LONG).show();
                    return;
                }
                if(sub_text.equals("")){
                    Toast.makeText(getApplication(),"サブカテゴリを選択してください！",Toast.LENGTH_LONG).show();
                    return;
                }
                if(color_text.equals("")){
                    Toast.makeText(getApplication(),"カラーを選択してください！",Toast.LENGTH_LONG).show();
                    return;
                }
                //現在時刻を取得
                Calendar calendar = Calendar.getInstance();
                String now = new SimpleDateFormat().format(calendar.getTime());
                Timestamp tm = new Timestamp(System.currentTimeMillis());
                //userNoと現在時刻をファイル名とする
                filename  = userNo + "_" + tm;

                param = new Param(filename,capImage,cate_text,sub_text,color_text,dress_value,casual_value,simple_value,tag1,tag2,tag3,tag4,vol);
                task = new ImagePOST();
                task.setListener(createListener_POST());
                task.execute(param);



            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(sw){
            //現在の大きさを取得
            int camera_info_height = camera_info.getHeight();
            cate_frame_height = cate_frame.getHeight();
            sub_frame_height = sub_frame.getHeight();
            color_frame_height = color_frame.getHeight();
            type_frame_height = type_frame.getHeight();
            tag_frame_height = tag_frame.getHeight();
            vol_frame_height = vol_frame.getHeight();


            //それぞれの幅0にする
            camera_info.getLayoutParams().height = 0;
            cate_frame.getLayoutParams().height = 0;
            sub_frame.getLayoutParams().height = 0;
            color_frame.getLayoutParams().height = 0;
            type_frame.getLayoutParams().height = 0;
            tag_frame.getLayoutParams().height = 0;
            vol_frame.getLayoutParams().height = 0;


            open_cameraINFO_vertical = new Open_layout_animation(camera_info,camera_info_height,0);
            open_cate_horizon = new Open_layout_animation(cate_frame,cate_frame_height,0);
            open_sub_horizon = new Open_layout_animation(sub_frame,sub_frame_height,0);
            open_color_horizon = new Open_layout_animation(color_frame,color_frame_height,0);
            open_type_horizon = new Open_layout_animation(type_frame,type_frame_height,0);
            open_tag_horizon = new Open_layout_animation(tag_frame,tag_frame_height,0);
            open_vol_horizon = new Open_layout_animation(vol_frame,vol_frame_height,0);
            open_cameraINFO_vertical.setFillAfter(true);

            open_cameraINFO_vertical.setDuration(1000);
            open_cate_horizon.setDuration(300);
            open_sub_horizon.setDuration(300);
            open_color_horizon.setDuration(300);
            open_type_horizon.setDuration(300);
            open_tag_horizon.setDuration(300);
            open_vol_horizon.setDuration(300);
            sw = false;
        }



    }

    //画像アップロードの成功した時
    private ImagePOST.Listener createListener_POST(){
        return new ImagePOST.Listener() {
            @Override
            public void onSuccess(String result) {
                ToastMake("登録完了！",0,-200);
                finish();
            }
        };
    }
    private GetCate.Listener createListener_POST_getcate(){
        return new GetCate.Listener() {
            @Override
            public void onSuccess(String[] cate) {
                if(cate.equals("conection error")){
                    finish();
                }
                cate_text = cate[0];
                cate_sp.setSelection(huku_chager.cate_get_idx(cate[0])+1);

                System.out.println("camera 258->cate_get:"+cate[0]);

                Bitmap_cate_set bitmap_cate_set = new Bitmap_cate_set(capImage,cate_text);
                GetSub getSub_task = new GetSub();
                getSub_task.setListener(createListener_POST_getsub());
                getSub_task.execute(cate);

                GetColor getColor_task = new GetColor();
                getColor_task.setListener(createListener_POST_getcolor());
                getColor_task.execute(cate[0]);

                GetType getType_task = new GetType();
                getType_task.setListener(createListener_POST_gettype());
                getType_task.execute(cate[0]);

                GetTag getTag_task = new GetTag();
                getTag_task.setListener(createListener_POST_gettag());
                getTag_task.execute(cate[0]);

                GetVol getVol_task = new GetVol();
                getVol_task.setListener(createListene_POST_getvol());
                getVol_task.execute(cate[0]);
                cate_frame.startAnimation(open_cate_horizon);


            }
        };
    }
    private GetSub.Listener createListener_POST_getsub(){
        return new GetSub.Listener() {
            @Override
            public void onSuccess(final String[] sub) {
                sub_text = sub[0];
                sub_sp.setSelection(huku_chager.sub_get_idx(cate_text,sub[0])+1);
                LinearLayout linearLayout = findViewById(R.id.sub_cate_button_frame);
                int original_height = linearLayout.getHeight();
                linearLayout.getLayoutParams().height = 0;
                if(sub.length >= 2){
                    TextView mosikuwa = findViewById(R.id.mosikuwa);
                    linearLayout.getLayoutParams().height = original_height;
                    mosikuwa.setText("もしくは");
                    yosou1.setText(huku_chager.sub_to_text(cate_text,sub[1]));
                    yosou1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sub_sp.setSelection(huku_chager.sub_get_idx(cate_text,sub[1])+1);
                            yosou2.setVisibility(View.INVISIBLE);
                            view.setVisibility(View.INVISIBLE);
                        }
                    });
                    if(sub.length >= 3){
                        yosou2.setText(huku_chager.sub_to_text(cate_text,sub[2]));
                        yosou2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                sub_sp.setSelection(huku_chager.sub_get_idx(cate_text,sub[2])+1);
                                yosou1.setVisibility(View.INVISIBLE);
                                view.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                }
                sub_frame.startAnimation(open_sub_horizon);
                System.out.println("camera 292->sab get");
            }
        };
    }
    private GetColor.Listener createListener_POST_getcolor(){
        return new GetColor.Listener() {
            @Override
            public void onSuccess(String color) {
                color_text = color;
                color_sp.setSelection(huku_chager.color_get_idx(color)+1);
                System.out.println("camera 302->color get:"+color);
                color_frame.setAnimation(open_color_horizon);
            }
        };
    }
    private GetType.Listener createListener_POST_gettype(){
        return new GetType.Listener() {
            @Override
            public void onSuccess(int[] value) {
                List<PieEntry> pieEntry = new ArrayList<PieEntry>();
                pieEntry.add(new PieEntry(value[0],"シンプル度"));
                pieEntry.add(new PieEntry(value[2],"カジュアル度"));
                pieEntry.add(new PieEntry(value[1],"ドレス度"));
                PieDataSet pieDataSet = new PieDataSet(pieEntry,"この服のタイプ");
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                PieData pieData = new PieData(pieDataSet);
                PieChart pieChart = findViewById(R.id.camera_type_pychart) ;
                pieChart.setData(pieData);
                Legend label = pieChart.getLegend();
                label.setEnabled(false);
                pieChart.invalidate();
                String[] type_name = {"simmple","dress","casual"};
                dress_value = value[1];
                casual_value = value[2];
                simple_value = value[0];
                String type_text = type_name[Array_MAX_Get_idx(value)];
                TextView textView = findViewById(R.id.type_text);
                textView.setText("この服は"+huku_chager.type_to_text(type_text)+"よりですね");
                System.out.println("camera 324->type get:"+type_text);
                type_frame.startAnimation(open_type_horizon);
            }
        };
    }
    private GetTag.Listener createListener_POST_gettag(){
        return new GetTag.Listener() {
            @Override
            public void onSuccess(String[] tag_list) {
                tag1 = tag_list[0];
                tag2 = tag_list[1];
                tag3 = tag_list[2];
                tag4 = tag_list[3];
                TextView tag1 = findViewById(R.id.tag_text_1);
                TextView tag2 = findViewById(R.id.tag_text_2);
                TextView tag3 = findViewById(R.id.tag_text_3);
                TextView tag4 = findViewById(R.id.tag_text_4);
                tag1.setText(huku_chager.tag_to_text(tag_list[0]));
                tag2.setText(huku_chager.tag_to_text(tag_list[1]));
                tag3.setText(huku_chager.tag_to_text(tag_list[2]));
                tag4.setText(huku_chager.tag_to_text(tag_list[3]));
                System.out.println("camera 344->tag get");
                tag_frame.startAnimation(open_tag_horizon);
            }
        };
    }
    private GetVol.Listener createListene_POST_getvol(){
        return new GetVol.Listener() {
            @Override
            public void onSuccess(int[] vol_value_list) {
                List<PieEntry> pieEntry = new ArrayList<PieEntry>();
                pieEntry.add(new PieEntry(vol_value_list[0],"控え目"));
                pieEntry.add(new PieEntry(vol_value_list[1],"派手"));
                PieDataSet pieDataSet = new PieDataSet(pieEntry,"この服の見た目");
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                PieData pieData = new PieData(pieDataSet);
                PieChart pieChart = findViewById(R.id.camera_vol_pychart) ;
                pieChart.setData(pieData);
                Legend label = pieChart.getLegend();
                label.setEnabled(false);
                pieChart.invalidate();
                String[] vol_name = {"hade","hikaeme"};
                vol = vol_name[Array_MAX_Get_idx(vol_value_list)];
                TextView vol_text = findViewById(R.id.vol_text);
                vol_text.setText("この服は"+huku_chager.vol_to_text(vol)+"です");
                System.out.println("camera 365->vol get:"+vol);
                vol_frame.startAnimation(open_vol_horizon);
            }
        };
    }

    //別のアクティビティから帰ってきたら起動する↓
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(REQUEST_CAPTURE_IMAGE == requestCode && resultCode == Activity.RESULT_OK){
            capImage = (Bitmap) data.getExtras().get("data");
            int imageWidth = capImage.getWidth();
            int imageHeight = capImage.getHeight();

            //-----------------アニメーションの準備
            //現在の大きさを取得
            int camera_info_height = camera_info.getHeight();
            cate_frame_height = cate_frame.getHeight();
            sub_frame_height = sub_frame.getHeight();
            color_frame_height = color_frame.getHeight();
            type_frame_height = type_frame.getHeight();
            tag_frame_height = tag_frame.getHeight();
            vol_frame_height = vol_frame.getHeight();


            //それぞれの幅0にする
            camera_info.getLayoutParams().height = 0;
            cate_frame.getLayoutParams().height = 0;
            sub_frame.getLayoutParams().height = 0;
            color_frame.getLayoutParams().height = 0;
            type_frame.getLayoutParams().height = 0;
            tag_frame.getLayoutParams().height = 0;
            vol_frame.getLayoutParams().height = 0;


            open_cameraINFO_vertical = new Open_layout_animation(camera_info,camera_info_height,0);
            open_cate_horizon = new Open_layout_animation(cate_frame,cate_frame_height,0);
            open_sub_horizon = new Open_layout_animation(sub_frame,sub_frame_height,0);
            open_color_horizon = new Open_layout_animation(color_frame,color_frame_height,0);
            open_type_horizon = new Open_layout_animation(type_frame,type_frame_height,0);
            open_tag_horizon = new Open_layout_animation(tag_frame,tag_frame_height,0);
            open_vol_horizon = new Open_layout_animation(vol_frame,vol_frame_height,0);
            open_cameraINFO_vertical.setFillAfter(true);

            open_cameraINFO_vertical.setDuration(1000);
            open_cate_horizon.setDuration(300);
            open_sub_horizon.setDuration(300);
            open_color_horizon.setDuration(300);
            open_type_horizon.setDuration(300);
            open_tag_horizon.setDuration(300);
            open_vol_horizon.setDuration(300);
            //-----------------
            Matrix matrix = new Matrix();
            matrix.setRotate(90,imageWidth/2,imageHeight/2);

            capImage = Bitmap.createBitmap(capImage,0,0,imageWidth,imageHeight,matrix,true);
            Bitmap capImage_after = Bitmap.createScaledBitmap(capImage,(int)(capImage.getWidth()*1.5),(int)(capImage.getHeight()*1.5),true);
            cameraImage.setImageBitmap(capImage_after);

            //-----ここからタスク開始
            GetCate getCate_task = new GetCate();
            getCate_task.setListener(createListener_POST_getcate());
            getCate_task.execute(capImage_after);

            TranslateAnimation translateAnimation = new TranslateAnimation(
                    Animation.ABSOLUTE,0,
                    Animation.ABSOLUTE,0,
                    Animation.ABSOLUTE,add_frame.getY(),
                    Animation.ABSOLUTE,-605
            );
            translateAnimation.setDuration(800);
            translateAnimation.setRepeatCount(0);
            translateAnimation.setFillAfter(true);

            add_frame.startAnimation(translateAnimation);

            camera_info.setVisibility(View.VISIBLE);

            camera_info.startAnimation(open_cameraINFO_vertical);
        }
    }

    //権限の付与の処理が返ってきた時に起動する↓
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        final int REQUEST_CODE = 1;
        if(requestCode == REQUEST_CODE){
            for(int i=0;i<permissions.length;i++){
                //権限リストを上からなめていき、カメラの権限があればifを通る
                final String permission = permissions[i];
                final int grantResult = grantResults[i];

                switch (permission){
                    case Manifest.permission.CAMERA:
                        if(grantResult == PackageManager.PERMISSION_GRANTED){
                            //許可されている
                            String ToastMessage = "カメラは許可されています";
                            ToastMake(ToastMessage,0,-200);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void ToastMake(String message,int x,int y){
        //トーストの宣言
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(this,message,Toast.LENGTH_LONG);
        //位置調整
        toast.setGravity(Gravity.CENTER,x,y);
        toast.show();

    }

    public int Array_MAX_Get_idx(int[] values){
        int max_idx = 0;
        int max_value = values[0];
        for(int i = 1;i<values.length;i++){
            if(max_value < values[i]){
                max_idx = i;
                max_value = values[i];
            }
        }
        return max_idx;
    }
}
