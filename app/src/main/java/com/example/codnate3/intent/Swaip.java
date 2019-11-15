package com.example.codnate3.intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.codnate3.R;
import com.example.codnate3.flafment.MyFragmentStatePagerAdapter;

public class Swaip extends AppCompatActivity {
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swaip);
        viewPager = findViewById(R.id.homePage);
        viewPager.setAdapter(new MyFragmentStatePagerAdapter(getSupportFragmentManager(),0));
        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),camera.class);
                startActivity(intent);
            }
        });


    }
}
