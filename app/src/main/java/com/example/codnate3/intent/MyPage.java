package com.example.codnate3.intent
        ;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.codnate3.R;
import com.example.codnate3.net.Change_Account;
import com.example.codnate3.net.Get_Account_Data;
import com.example.codnate3.object.ArrayPulldown;
import com.example.codnate3.object.ChangeAccountData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyPage extends AppCompatActivity {


    private String jiko,name,type;
    private int userNo,age;
    private Spinner age_spinner,type_spinner;
    private EditText name_form,jiko_form;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        SharedPreferences data = getSharedPreferences("DATA",MODE_PRIVATE);
        userNo = data.getInt("userNo",0);

        //age スピナー
        age_spinner = this.findViewById(R.id.mypage_age);
        type_spinner = this.findViewById(R.id.mypage_type);
        age_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent , View view, int i, long l) {
                Spinner spinner = (Spinner)parent;
                String item = (String)spinner.getSelectedItem();
                age = Integer.parseInt(item);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent , View view, int i, long l) {
                Spinner spinner = (Spinner)parent;
                String item = (String)spinner.getSelectedItem();
                type = item;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        name_form = findViewById(R.id.mypage_name);
        jiko_form = findViewById(R.id.mypage_jiko);

        Button save_button = findViewById(R.id.save_button);
        Button back_button = findViewById(R.id.back_button);

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = name_form.getText().toString();
                jiko = jiko_form.getText().toString();

                Change_Account change_account = new Change_Account();
                change_account.setListener(create_change_task());
                ChangeAccountData changeAccountData = new ChangeAccountData(userNo,age,name,jiko,type);
                change_account.execute(changeAccountData);
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Get_Account_Data get_account_data = new Get_Account_Data();
        get_account_data.setListener(create_get_task());
        get_account_data.execute(String.valueOf(userNo));
    }

    Change_Account.Listener create_change_task(){
        return new Change_Account.Listener() {
            @Override
            public void onSuccess(String userNo) {
                ToastMake("セーブ完了",0,-200);
                finish();
            }
        };
    }
    Get_Account_Data.Listener create_get_task(){
        return new Get_Account_Data.Listener() {
            @Override
            public void onSuccess(String[] str) {
                age = Integer.parseInt(str[0]);
                age_spinner.setSelection(age-7);
                name_form.setText(str[1]);
                jiko_form.setText(str[2]);
                if (str[3].equals("男性")){
                    List<String> type_list =  new ArrayList<>(Arrays.asList("カジュアル","シンプル","クール"));
                    String[] arraytype = {"カジュアル","シンプル","クール"};
                    ArrayAdapter<String> subAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, arraytype);
                    type_spinner.setAdapter(subAdapter);

                    type_spinner.setSelection(type_list.indexOf(str[4]));
                }else{
                    List<String> type_list =  new ArrayList<>(Arrays.asList("フェミニン","カジュアル","クール"));
                    String[] arraytype = {"フェミニン","カジュアル","クール"};
                    ArrayAdapter<String> subAdapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item, arraytype);
                    type_spinner.setAdapter(subAdapter);
                    type_spinner.setSelection(type_list.indexOf(str[4]));
                }

            }
        };
    }
    public void ToastMake(String message,int x,int y){
        //トーストの宣言
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(this,message,Toast.LENGTH_LONG);
        //位置調整
        toast.setGravity(Gravity.CENTER,x,y);
        toast.show();

    }
}
