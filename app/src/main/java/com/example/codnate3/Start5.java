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

    private String type_text;
    TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start5);
        error = findViewById(R.id.start5_error);


        Button back = findViewById(R.id.back_start5);
        //↓前のActivityへ
        back.setOnClickListener(new View.OnClickListener() {
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

                    //選択されているラジオボタンの取得
                    RadioButton radiobutton2 = findViewById(checkedId2);

                    //ラジオボタンのテキストを取得
                    String text = radiobutton2.getText().toString();
                    type_text = text;
                    Log log = null;
                    log.v("checked",text);

            }
        });
        //↓呼び出したActivityにデータを返す
        Button finish = findViewById(R.id.next_start5);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type_text != null){
                    Account_data account_data = new Account_data();
                    account_data.setType(type_text);
                    Intent intent = new Intent();
                    intent.putExtra("Account_data",account_data);
                    setResult(RESULT_OK,intent);
                    finish();
                }else{
                    error.setText("タイプを選択してください");
                }
            }
        });
    }

}
