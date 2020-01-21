
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
import java.util.zip.Inflater;

public class GetType extends AsyncTask<String,Void, int[]>{

    //リスナー
    private Listener listener;
    //改行文字列
    @Override
    protected int[] doInBackground(String... paths) {

        int[] test = {30,30,40};
        return test;
        /*


        String path = paths[0];
        //接続するためのクラスを宣言
        HttpURLConnection con = null;
        String readline = "";
        String type_value_text[] = new String[3];

        String url_text = "http://"+ AWS_INTERFACE.IPADDRESS_AI3+"/tanuki/get_Type";

        try {
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
            malt.textPost(printStream,"path",  path,boundary);
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
                type_value_text = readline.split(",");
            }else {
                readline = "Gettype 77->type conection error";
            }

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


        return  String_array_to_int(type_value_text);


         */



    }


    public void setListener(Listener listener){
        this.listener = listener;
    }

    @Override
    protected void onPostExecute(int[] anInt) {
        if(listener != null){
            listener.onSuccess(anInt);
        }
    }

    public interface Listener{
        void onSuccess(int[] value);
    }
    //文字列配列を数字配列に変換
    public int[] String_array_to_int(String[] array_text){
        int values[] = new int[array_text.length];
        for(int i=0;i<array_text.length;i++){
            values[i] = Integer.parseInt(array_text[i]);
        }
        return values;
    }
}
