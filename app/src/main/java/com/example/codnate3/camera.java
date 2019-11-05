package com.example.codnate3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.jar.Pack200;

public class camera extends Activity {
    //取得する画像の大きさ
    final int REQUEST_CAPTURE_IMAGE = 100;
    private ImagePOST task;
    private int userNo = 1;
    private Bitmap capImage;
    Button cameraButton;
    Button postButton;
    ImageView cameraImage;
    TextView resText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        //ボタンの配置
        cameraButton = findViewById(R.id.cameraButton);
        postButton = findViewById(R.id.PostButton);
        //撮影した写真の表示する場所
        cameraImage = findViewById(R.id.cameraImage);
        //POSTの結果を表示
        resText = findViewById(R.id.textView);


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
        //画像の送信ボタン
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                String now = new SimpleDateFormat().format(calendar.getTime());
                String filename  = userNo + "_" + now;
                Param param = new Param(filename,capImage);
                task = new ImagePOST();
                task.setListener(createListener());
                task.execute(param);
            }
        });



    }
    private ImagePOST.Listener createListener(){
        return new ImagePOST.Listener() {
            @Override
            public void onSuccess(String result) {
                resText.setText(result);
            }
        };
    }

    //別のアクティビティから帰ってきたら起動する↓
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(REQUEST_CAPTURE_IMAGE == requestCode && resultCode == Activity.RESULT_OK){
            capImage = (Bitmap) data.getExtras().get("data");
            cameraImage.setImageBitmap(capImage);
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
