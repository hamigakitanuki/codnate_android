package com.example.codnate3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Start5 extends AppCompatActivity {
    private EditText editText;
    private String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start5);


        Button button12 = findViewById(R.id.button12);
        //↓前のActivityへ
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    //ラジオボタンの処理
        RadioGroup radioGroup =  findViewById(R.id.RadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group,int checkedId2){
                if(checkedId2 != -1){
                    //選択されているラジオボタンの取得
                    RadioButton radiobutton2 = findViewById(checkedId2);

                    //ラジオボタンのテキストを取得
                    String text = radiobutton2.getText().toString();

                    Log log = null;
                    log.v("checked",text);
                }else{
                    TextView textview = findViewById(R.id.textView8);
                    textview.setText("選択されていません");
                }
            }
        });


    }
}
