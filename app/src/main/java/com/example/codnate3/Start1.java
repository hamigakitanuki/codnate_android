package com.example.codnate3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Start1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start1);
        Button nm = findViewById(R.id.button2);

        //↓イベント発生を聞き耳を立てている(Buttonが押されたかどうかを監視)
        nm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(Start1.this,Start2.class);
                startActivity(intent);
            }
        });
    }
}
