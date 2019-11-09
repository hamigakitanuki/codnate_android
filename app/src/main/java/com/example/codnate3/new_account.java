
package com.example.codnate3;


import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class new_account extends AsyncTask<Account_data,Void,String>{

    //リスナー
    private Listener listener;
    private String readline;
    @Override
    protected String doInBackground(Account_data... accounts) {

        //受け取ったParamを格納
        Account_data account = accounts[0];
        //読み込み先の領域
        StringBuffer sb = new StringBuffer();
        //接続するためのクラスを宣言
        HttpURLConnection con = null;
        String new_account_url = "http://3.133.83.204:8080/tanuki/newAccount";
        String post_param = "name="+account.getName()+
                            "&sex=" +account.getSex()+
                            "&age=" +account.getAge()+
                            "&type="+account.getType();
        System.out.println(post_param);

        int rescode;
        try {
            //URLクラス宣言
            URL url = new URL(new_account_url);
            //コネクションを開く
            con = (HttpURLConnection)url.openConnection();
            //POSTに変更
            con.setRequestMethod("POST");
            //コネクション上でリクエストボディ送信の許可/不許可を設定できる
            con.setDoOutput(true);
            //レスポンスのボディ受信を許可する
            con.setDoInput(true);

            con.setRequestProperty("Content-type","text/plain;charset=utf-8");
            con.setRequestProperty("User-Agent", "Android");
            // ヘッダーの設定(複数設定可能)
            con.setRequestProperty("Accept-Language", "jp");
            //コンテンツタイプを変更

            con.connect();
            OutputStream out = con.getOutputStream();
            out.write(post_param.getBytes());
            out.close();

            rescode = con.getResponseCode();
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            readline = br.readLine();
            System.out.println(readline);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            if(con != null) {
                //コネクション切断
                con.disconnect();
            }
        }
        return readline;
    }
    @Override
    public void onPostExecute(String userNo){
        super.onPostExecute(userNo);
        System.out.println(userNo);
        if(listener != null){
            listener.onSuccess(userNo);
        }
    }

    void setListener(Listener listener){
        this.listener = listener;
    }

    interface Listener{
        void onSuccess(String  userNo);
    }

}
