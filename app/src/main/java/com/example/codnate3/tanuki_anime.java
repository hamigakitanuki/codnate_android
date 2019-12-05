package com.example.codnate3;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class tanuki_anime  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("ロード中");
        setContentView(R.layout.load);
        ImageView imageView = findViewById(R.id.load_tanuki);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.tanuki_load_animato);
        imageView.startAnimation(animation);
    }

}
