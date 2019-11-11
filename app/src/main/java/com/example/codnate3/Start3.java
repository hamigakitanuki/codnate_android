package com.example.codnate3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Start3 extends AppCompatActivity {
    final int RESULT_Start4 = 4;
    String age_text ;
    private EditText editText;
    private String message;
    public static String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start3);


        Intent intent = getIntent();
        text = intent.getStringExtra("EXTRA_DATA");
        Spinner spinner = this.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent , View view, int i, long l) {
                Spinner spinner = (Spinner)parent;
                String item = (String)spinner.getSelectedItem();
                age_text = item;
                Toast.makeText(Start3.this, item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //↓前のActivityに戻る
        Button back = findViewById(R.id.back_start3);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        //次のActivityへ
        Button next = findViewById(R.id.next_start3);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            if(age_text != null){
                Intent intent = new Intent(getApplication(), Start4.class);
                intent.putExtra("EXTRA_DATA", text);
                startActivityForResult(intent,RESULT_Start4);
            }
            }
        });
    }

    protected void onActivityResult(int ReqestCode,int ResponceCode,Intent intent){
        super.onActivityResult(ReqestCode, ResponceCode, intent);

        if(ReqestCode == RESULT_Start4
                && ResponceCode == RESULT_OK
                && intent != null){
            Account_data account_data = (Account_data)intent.getSerializableExtra("Account_data");
            account_data.setAge(age_text);
            intent.putExtra("Account_data",account_data);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
