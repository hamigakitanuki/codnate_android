package com.example.codnate3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.codnate3.flafment.Fragment1;
import com.example.codnate3.flafment.MyFragmentStatePagerAdapter;
import com.example.codnate3.flafment.MyFragmentStatePagerAdapter_Test;
import com.example.codnate3.intent.Start1;

public class TestActivity extends AppCompatActivity {
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
//            getSupportActionBar().hide();

            setContentView(R.layout.activity_swaip);


            addButton = findViewById(R.id.add_button_swaip);
            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplication(), com.example.codnate3.intent.camera.class);
                    startActivity(intent);
                }
            });
            viewPager = findViewById(R.id.homePage);
            viewPager.setAdapter(new MyFragmentStatePagerAdapter_Test(getSupportFragmentManager(), 0));




        }
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK &&
                requestCode == OPENING_RESULT_CODE &&
                data != null) {
            Intent intent = new Intent(getApplication(), TestActivity.class);
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
            intent.setClass(getApplication(), TestActivity.class);
            startActivity(intent);
        }
    }
}
