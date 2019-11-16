package com.example.codnate3.intent;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.codnate3.R;
import com.example.codnate3.flafment.MyFragmentStatePagerAdapter;

public class Swaip extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_swaip);
        ImageButton button = findViewById(R.id.float_myPage_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),com.example.codnate3.intent.MyPage.class);
                startActivity(intent);
            }
        });
        viewPager = findViewById(R.id.homePage);
        viewPager.setAdapter(new MyFragmentStatePagerAdapter(getSupportFragmentManager(),0));
    }
}
