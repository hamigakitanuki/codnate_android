package com.example.codnate3.intent;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.codnate3.R;
import com.example.codnate3.net.GetCate;
import com.example.codnate3.net.ImagePOST;
import com.example.codnate3.object.ArrayPulldown;
import com.example.codnate3.object.Param;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class camera extends Activity {

    final int REQUEST_CAPTURE_IMAGE = 150;
    private ImagePOST task;
    private GetCate getCate_task;
    private int userNo;
    private Bitmap capImage;
    private Button cameraButton,addButton;
    private ImageButton cameraImage;
    Spinner cate ,sub,color;
    String cookie;
    Param param;
    String url,filename, cate_text,sub_text,color_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //ボタンの配置
        cameraButton = findViewById(R.id.cameraButton);
        addButton = findViewById(R.id.huku_add);
        //プルダウンの設置
        cate = findViewById(R.id.cate_spiner);
        sub = findViewById(R.id.sub_spiner);
        color = findViewById(R.id.color_spiner);
        cate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner spinner = (Spinner)adapterView;
                cate_text = (String)spinner.getSelectedItem();
                ArrayAdapter<String> subAdapter;
                switch (cate_text){
                    case "トップス":
                        subAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_spinner_item, ArrayPulldown.tops_sub);
                        sub.setAdapter(subAdapter);
                        break;
                    case "ボトムス":
                        subAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_spinner_item,ArrayPulldown.botoms_sub);
                        sub.setAdapter(subAdapter);
                        break;
                    case "ワンピース":
                        subAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_spinner_item,ArrayPulldown.onepeace_sub);
                        sub.setAdapter(subAdapter);
                        break;
                    case "アウター":
                        subAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_spinner_item,ArrayPulldown.outer_sub);
                        sub.setAdapter(subAdapter);
                        break;
                    case "シューズ":
                        subAdapter = new ArrayAdapter<>(getApplication(),android.R.layout.simple_spinner_item,ArrayPulldown.shoese_sub);
                        sub.setAdapter(subAdapter);
                        break;
                    default:
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sub.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner spiner = (Spinner)adapterView;
                sub_text = (String)spiner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner spinner = (Spinner)adapterView;
                color_text = (String)spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //撮影した写真の表示する場所兼ボタン
        cameraImage = findViewById(R.id.cameraImage);

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
        cameraButton.setOnClickListener(new View.OnClickListener() {
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

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cate_text == null){
                    Toast.makeText(getApplication(),"カテゴリを選択してください！",Toast.LENGTH_LONG).show();
                    return;
                }
                if(sub_text == null){
                    Toast.makeText(getApplication(),"サブカテゴリを選択してください！",Toast.LENGTH_LONG).show();
                    return;
                }
                if(color_text == null){
                    Toast.makeText(getApplication(),"カテゴリを選択してください！",Toast.LENGTH_LONG).show();
                    return;
                }
                //現在時刻を取得
                Calendar calendar = Calendar.getInstance();
                String now = new SimpleDateFormat().format(calendar.getTime());
                Timestamp tm = new Timestamp(System.currentTimeMillis());
                //userNoと現在時刻をファイル名とする
                filename  = userNo + "_" + tm;
                param = new Param(filename,capImage,cate_text,sub_text,color_text);
                task = new ImagePOST();
                task.setListener(createListener_POST());
                task.execute(param);
            }
        });
    }

    private ImagePOST.Listener createListener_POST(){
        return new ImagePOST.Listener() {
            @Override
            public void onSuccess(String result) {

            }
        };
    }
    private GetCate.Listener createListener_POST_getcate(){
        return new GetCate.Listener() {
            @Override
            public void onSuccess(String result) {

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

            Matrix matrix = new Matrix();
            matrix.setRotate(90,imageWidth/2,imageHeight/2);

            Bitmap bitmap_rotate = Bitmap.createBitmap(capImage,0,0,imageWidth,imageHeight,matrix,true);

            cameraImage.setImageBitmap(bitmap_rotate);

            getCate_task = new GetCate();
            getCate_task.setListener(createListener_POST_getcate());
            getCate_task.execute(capImage);
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
}
