
package com.example.codnate3.net;


import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Xml;

import com.example.codnate3.AWS_INTERFACE;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class GetColor extends AsyncTask<Bitmap,Void, String[]>{

    //リスナー
    private Listener listener;
    //改行文字列
    @Override
    protected String[] doInBackground(Bitmap... bitmaps) {
        String[] test = {"black",""};
        return test;
        /*
        //受け取ったParamを格納
        Bitmap bitmap = bitmaps[0];
        //接続するためのクラスを宣言
        HttpURLConnection con = null;
        String readline = "";

        String url_text = "http://"+ AWS_INTERFACE.IPADDRESS_AI3 +"/tanuki/get_Color";

        try {
            //画像をJPEG形式で送れるように準備
            ByteArrayOutputStream png = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG,100,png);
            //URLクラス宣言
            URL url = new URL(url_text);
            //コネクションを開く
            con = (HttpURLConnection)url.openConnection();
            //POSTに変更
            con.setRequestMethod("POST");
            //コネクション上でリクエストボディ送信の許可/不許可を設定できる
            con.setDoOutput(true);
            //レスポンスのボディ受信を許可する
            con.setDoInput(true);
            // ヘッダーの設定(複数設定可能)
            con.setRequestProperty("User-Agent", "Android");
            con.setRequestProperty("Accept-Language", "jp");
            //boudaryに一意な文字列を代入
            final String boundary = UUID.randomUUID().toString();
            //コンテンツタイプを変更
            con.setRequestProperty("Content-type","multipart/form-data; boundary="+boundary+";charset=UTF-8");
            //リクエストボディを書き込んでいく
            //一度コネクト
            con.connect();
            Malt_part_post malt = new Malt_part_post();
            PrintStream printStream = new PrintStream(con.getOutputStream(),false, Xml.Encoding.UTF_8.name());
            malt.textPost(printStream,"sub",  "tekitou",boundary);
            malt.bitmapPost(printStream,"img",bitmap,boundary);
            printStream.print("--" + boundary + "--");
            if (printStream != null) {
                printStream.close();
            }
            int rescode = con.getResponseCode();
            if(rescode == HttpURLConnection.HTTP_OK){
                InputStream in = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
                readline = br.readLine();
                in.close();
                br.close();
            }else {
                readline = "color conection error";
            }

            System.out.println("GetColor 78->"+readline);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            if(con != null) {
                //コネクション切断
                con.disconnect();
            }
        }


        return readline.split(",");

         */
    }


    public void setListener(Listener listener){
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(String[] s) {
        if(listener != null){
            listener.onSuccess(s);
        }
    }

    public interface Listener{
        void onSuccess(String[] color);
    }

}
