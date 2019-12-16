package com.example.codnate3;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.codnate3.flafment.Fragment1;
import com.example.codnate3.flafment.MyFragmentStatePagerAdapter;
import com.example.codnate3.intent.Start1;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private final int OPENING_RESULT_CODE = 1;
    private boolean sw = false;
    private ImageButton addButton;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences data = getSharedPreferences("DATA", MODE_PRIVATE);
        int userNo = data.getInt("userNo", 0);
        //新規に始めた場合はusernoが0状態
        if (userNo == 0) {
            Intent intent = new Intent(getApplication(), Start1.class);
            startActivityForResult(intent, OPENING_RESULT_CODE);
            finish();

        } else {

            //上のタイトルバーが消える
            getSupportActionBar().hide();

            setContentView(R.layout.activity_swaip);
            ImageButton button = findViewById(R.id.float_myPage_button);
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
                    startActivity(intent);
                }
            });
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
                requestCode == Fragment1.DETAIL_RESULT_CODE) {
            onDestroy();
            onRestart();
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
}
