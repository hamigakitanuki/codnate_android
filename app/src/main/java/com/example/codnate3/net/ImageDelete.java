
package com.example.codnate3.net;


import android.os.AsyncTask;
import android.util.Xml;

import com.example.codnate3.AWS_INTERFACE;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class ImageDelete extends AsyncTask<String,Void,String>{

    //リスナー
    private Listener listener;
    private String readline;
    @Override
    protected String doInBackground(String... paths) {

        //受け取ったParamを格納
        String path = paths[0];
        //読み込み先の領域
        StringBuffer sb = new StringBuffer();
        //接続するためのクラスを宣言
        HttpURLConnection con = null;
        String new_account_url = "http://"+ AWS_INTERFACE.IPADDRESS +"/tanuki/img_Delete";
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
            malt_part_post.textPost(printStream,"filePath",path,boundary);
            printStream.print("--" + boundary + "--");
            printStream.close();

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
    public void onPostExecute(String result_text){
        if(listener != null){
            listener.onSuccess(result_text);
        }
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    public interface Listener{
        void onSuccess(String  result_text);
    }

}
