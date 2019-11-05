package com.example.codnate3;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

public class ToastMake {
    public void Make(Activity myac, String message, int x, int y){
        //トーストの宣言
        Context context = myac.getApplicationContext();
        Toast toast = Toast.makeText(myac,message,Toast.LENGTH_LONG);
        //位置調整
        toast.setGravity(Gravity.CENTER,x,y);
        toast.show();

    }
}
