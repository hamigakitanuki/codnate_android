package com.example.codnate3.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.codnate3.R;
import com.example.codnate3.net.new_account;
import com.example.codnate3.object.Account_data;

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
        System.out.println("start1 35->");
        if(resultCode == RESULT_OK
        && requestCode == RESULT_Start2
        && intent != null){
            System.out.println("start1 39->");


            Account_data account_data = (Account_data)intent.getSerializableExtra("Account_data");
            new_account task = new new_account();
            task.setListener(create_Lithner());
            task.execute(account_data);

            finish();



        }

    }
    private new_account.Listener create_Lithner(){
        return new new_account.Listener() {
            @Override
            public void onSuccess(String userNo) {
                SharedPreferences data = getSharedPreferences("DATA",MODE_PRIVATE);
                SharedPreferences.Editor editor = data.edit();
                editor.putInt("userNo", Integer.parseInt(userNo));
                editor.commit();
                System.out.println(userNo + "登録完了");
                Intent intent2 = new Intent(getApplication(),com.example.codnate3.MainActivity.class);
                startActivity(intent2);
            }
        };
    }
}
