package com.example.codnate3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Start1 extends AppCompatActivity {
    final int RESULT_Start2 = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start1);
        Button new_account_button = findViewById(R.id.New_account);

        //↓イベント発生を聞き耳を立てている(Buttonが押されたかどうかを監視)
        new_account_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent intent = new Intent(getApplication(),Start2.class);
                startActivityForResult(intent,RESULT_Start2);
            }
        });

    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == RESULT_OK
        && requestCode == RESULT_Start2
        && intent != null){


            Account_data account_data = (Account_data)intent.getSerializableExtra("Account_data");
            new_account task = new new_account();
            task.setListener(create_Lithner());
            task.execute(account_data);

        }

    }
    private new_account.Listener create_Lithner(){
        return new new_account.Listener() {
            @Override
            public void onSuccess(String userNo) {
                SharedPreferences data = getSharedPreferences("DATA",MODE_PRIVATE);
                SharedPreferences.Editor editor = data.edit();
                editor.putInt("userNo",Integer.parseInt(userNo));
                editor.apply();
                System.out.println(userNo+"登録完了");
                finish();

            }
        };
    }
}
