package com.example.codnate3.intent
        ;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.codnate3.R;

public class MyPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_my_page);
    }
}
