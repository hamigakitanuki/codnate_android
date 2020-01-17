package com.example.codnate3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.codnate3.flafment.Closet_fragment;
import com.example.codnate3.flafment.MyFragmentStatePagerAdapter;
import com.example.codnate3.intent.Start1;
import com.example.codnate3.net.Get_count;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private final int OPENING_RESULT_CODE = 11;
    private boolean sw = false;
    private ImageButton addButton;
    int userNo;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences data = getSharedPreferences("DATA", MODE_PRIVATE);
        userNo = data.getInt("userNo", 0);
        //新規に始めた場合はusernoが0状態
        if (userNo == 0) {
            Intent intent = new Intent(getApplication(), Start1.class);
            startActivityForResult(intent, OPENING_RESULT_CODE);
            finish();

        } else {
            //上のタイトルバーが消える
//            getSupportActionBar().hide();

            setContentView(R.layout.activity_swaip);
            Button button = findViewById(R.id.float_myPage_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplication(), com.example.codnate3.intent.MyPage.class);
                    startActivity(intent);

                }

            });

            addButton = findViewById(R.id.add_button_swaip);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplication(), com.example.codnate3.intent.camera.class);
                    startActivityForResult(intent,123);
                }
            });
            Get_count get_count = new Get_count();
            get_count.setListener(get_count_task());
            get_count.execute(String.valueOf(userNo));

            viewPager = findViewById(R.id.homePage);
            viewPager.setAdapter(new MyFragmentStatePagerAdapter(getSupportFragmentManager(), 0));




        }
    }


    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK &&
                requestCode == OPENING_RESULT_CODE &&
                data != null) {
            Intent intent = new Intent(getApplication(), com.example.codnate3.MainActivity.class);
            startActivity(intent);
            finish();
        }
        if (resultCode == RESULT_OK &&
                requestCode == Closet_fragment.DETAIL_RESULT_CODE) {
            onDestroy();
            onRestart();
        }
        if(resultCode == RESULT_OK && requestCode == 123){
            Get_count get_count = new Get_count();
            get_count.setListener(get_count_task());
            get_count.execute(String.valueOf(userNo));

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sw) {
            sw = false;
            Intent intent = new Intent();
            intent.setClass(getApplication(), MainActivity.class);
            startActivity(intent);
        }
    }
    Get_count.Listener get_count_task(){
        return new Get_count.Listener() {
            String hukidasi_text = "服の登録が足りないみたいです… 服を追加しましょう!!\n足りないカテゴリは";
            boolean sw = false;
            @Override
            public void onSuccess(int[] count_list) {
                if(count_list[0] < 1){
                    sw = true;
                    hukidasi_text = hukidasi_text +"トップス";
                }
                if(count_list[1] < 1){
                    if(sw){
                        hukidasi_text = hukidasi_text +"、ボトムス";
                    }else {
                        sw = true;
                        hukidasi_text = hukidasi_text + "ボトムス";
                    }
                }
                if(count_list[2] < 1){
                    if(sw){
                        hukidasi_text = hukidasi_text +"、シューズ";
                    }else {
                        sw = true;
                        hukidasi_text = hukidasi_text + "シューズ";
                    }
                }
                TextView hukidasi = findViewById(R.id.add_reco_hukidasi);

                if(sw){
                    hukidasi.setText(hukidasi_text + "です。");
                }else{
                    hukidasi.setVisibility(View.INVISIBLE);
                    TextView blackback = findViewById(R.id.balck_back);
                    blackback.setVisibility(View.INVISIBLE);
                    viewPager.setAdapter(new MyFragmentStatePagerAdapter(getSupportFragmentManager(), 0));


                }
            }
        };
    }
}
