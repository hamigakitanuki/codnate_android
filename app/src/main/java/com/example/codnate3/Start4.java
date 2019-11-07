package com.example.codnate3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Start4 extends AppCompatActivity {

    static final int RESULT_Start5 = 5;
    public static final String EXTRA_MESSAGE = "YourPackageName.MESSAGE";
    private TextView textView;
    private EditText editText;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start4);
        final EditText editText = findViewById(R.id.editText);


        //↓前のActivityへ
        Button button10 = findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        //送信のボタンを押すとPlainTextに入力されている文字を取得
        Button button13 = findViewById(R.id.button11);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // エディットテキストのテキストを取得
                String text = editText.getText().toString();
                // 取得したテキストを TextView に張り付ける
                editText.setText(text);
            }
        });
        //次のActivityへ
        Button button11 = findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Start4.this, Start5.class);
                editText.setText("");
                startActivityForResult(intent, RESULT_Start5);
            }
        });
    }
        //Start5からコーデの種類を受け取る
        protected void onActivityResult(int requestCode,int resultCode,Intent intent){
            super.onActivityResult(requestCode,resultCode,intent);

            if(resultCode == RESULT_OK && requestCode == RESULT_Start5 && null != intent){
            String res = intent.getStringExtra(Start4.EXTRA_MESSAGE);
            textView.setText(res);
            }
           
    }
}
