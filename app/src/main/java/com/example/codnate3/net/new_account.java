
package com.example.codnate3.net;


import android.os.AsyncTask;
import android.util.Xml;

import com.example.codnate3.AWS_INTERFACE;
import com.example.codnate3.object.Account_data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class
new_account extends AsyncTask<Account_data,Void,String>{

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
        String new_account_url = "http://"+ AWS_INTERFACE.IPADDRESS +"/tanuki/newAccount";

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

            con.setRequestProperty("User-Agent", "Android");
            // ヘッダーの設定(複数設定可能)
            con.setRequestProperty("Accept-Language", "jp");
            //コンテンツタイプを変更

            final String boundary = UUID.randomUUID().toString();
            con.setRequestProperty("Content-type","multipart/form-data; boundary="+boundary);
            //リクエストボディを書き込んでいく
            //一度コネクト
            con.connect();
            PrintStream printStream = new PrintStream(con.getOutputStream(),false, Xml.Encoding.UTF_8.name());
            Malt_part_post malt_part_post = new Malt_part_post();
            malt_part_post.textPost(printStream,"name",account.getName(),boundary);
            malt_part_post.textPost(printStream,"sex",account.getSex(),boundary);
            malt_part_post.textPost(printStream,"age",account.getAge(),boundary);
            malt_part_post.textPost(printStream,"type",account.getType(),boundary);
            printStream.print("--" + boundary + "--");
            printStream.close();

            rescode = con.getResponseCode();
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            readline = br.readLine();

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

    public void setListener(Listener listener){
        this.listener = listener;
    }

    public interface Listener{
        void onSuccess(String  userNo);
    }

}
