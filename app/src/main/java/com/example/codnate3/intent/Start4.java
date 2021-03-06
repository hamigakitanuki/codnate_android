package com.example.codnate3.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.codnate3.R;
import com.example.codnate3.object.Account_data;

public class Start4 extends AppCompatActivity {

    final int RESULT_Start5 = 5;
    private EditText editText;
    public static String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start4);
        editText = findViewById(R.id.start4_edit_text);
        Intent intent = getIntent();
        text = intent.getStringExtra("EXTRA_DATA");

        //↓前のActivityへ
        Button back = findViewById(R.id.back_start4);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });


        //次のActivityへ
        Button next = findViewById(R.id.next_start4);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(editText.toString() != null) {
                    if(text.equals("男性")) {
                        Intent intent = new Intent(getApplication(), Start_Mens.class);
                        startActivityForResult(intent, RESULT_Start5);
                    }else{
                        Intent intent = new Intent(getApplication(), Start5.class);
                        startActivityForResult(intent, RESULT_Start5);
                    }

                }

            }
        });
    }

    protected void onActivityResult(int ReqestCode,int ResponceCode,Intent intent){
        super.onActivityResult(ReqestCode, ResponceCode, intent);
        if(ReqestCode == RESULT_Start5
                && ResponceCode == RESULT_OK
                && null != intent){

            Account_data account_data = (Account_data)intent.getSerializableExtra("Account_data");
            account_data.setName(editText.getText().toString());
            intent.putExtra("Account_data",account_data);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
