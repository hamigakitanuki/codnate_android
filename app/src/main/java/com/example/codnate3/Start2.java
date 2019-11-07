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


public class Start2 extends AppCompatActivity {
    static final int RESULT_Start3 = 3;
    private EditText editText;
    private String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start2);

        RadioGroup radioGroup =  findViewById(R.id.RadioGroupOs);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group,int checkedId){
                if(checkedId != -1){
                    //選択されているラジオボタンの取得
                    RadioButton radiobutton = findViewById(checkedId);

                    //ラジオボタンのテキストを取得
                    String text = radiobutton.getText().toString();

                    Log log = null;
                    log.v("checked",text);
                }else{
                    TextView textview = findViewById(R.id.textView8);
                    textview.setText("選択されていません");
                }
            }
        });
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        //↓前のActivity(このactivityを終わる)に戻る
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        //↓Activty3へ
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                        Intent intent = new Intent(Start2.this, Start5.class);
                        editText.setText("");
                        startActivityForResult(intent, RESULT_Start3);
                    }
                });

                //↓呼び出したActivityにデータを返す
                Button button = findViewById(R.id.button14);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        if(editText.getText() != null){
                            String str = message + editText.getText().toString();
                            intent.putExtra(Start4.EXTRA_MESSAGE,str);
                        }
                        editText.setText("");
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
    }
}
