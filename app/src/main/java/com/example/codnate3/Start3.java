package com.example.codnate3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Start3 extends AppCompatActivity {
    static final int RESULT_Start4 = 4;
    private EditText editText;
    private String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start3);

        Spinner spinner = this.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent , View view, int i, long l) {
                Spinner spinner = (Spinner)parent;
                String item = (String)spinner.getSelectedItem();
                Toast.makeText(Start3.this, item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //↓前のActivityに戻る
        Button button7 = findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        //次のActivityへ
        Button button8 = findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Start3.this, Start5.class);
                editText.setText("");
                startActivityForResult(intent, RESULT_Start4);
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
