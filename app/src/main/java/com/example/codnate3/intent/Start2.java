package com.example.codnate3.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.codnate3.R;
import com.example.codnate3.object.Account_data;


public class Start2 extends AppCompatActivity {
    final int RESULT_Start3 = 3;
    TextView error;
    private boolean check = false;
    public String sex_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start2);

        RadioGroup radioGroup =  findViewById(R.id.RadioGroupOs);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group,int checkedId) {
                if (checkedId != -1) {
                    //選択されているラジオボタンの取得
                    RadioButton radiobutton = findViewById(checkedId);

                    //ラジオボタンのテキストを取得
                    String text = radiobutton.getText().toString();
                    sex_text = text;
                    check = true;
                    Log log = null;
                    log.v("checked", text);

                }
            }
        });
        Button back = findViewById(R.id.back_start2);
        Button next = findViewById(R.id.next_start2);
        //↓前のActivity(このactivityを終わる)に戻る
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        //↓Activty3へ
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(check){

                    Intent intent = new Intent(getApplication(), Start3.class);
                    intent.putExtra("EXTRA_DATA", sex_text);
                    startActivityForResult(intent,RESULT_Start3);
                    check = false;
                }else{
                }

            }
        });
    }
    protected void onActivityResult(int ReqestCode,int ResponceCode,Intent intent){
        super.onActivityResult(ReqestCode, ResponceCode, intent);

        if(ReqestCode == RESULT_Start3 && ResponceCode == RESULT_OK && intent != null){
            Account_data account_data = (Account_data)intent.getSerializableExtra("Account_data");
            account_data.setSex(sex_text);

            intent.putExtra("Account_data",account_data);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
