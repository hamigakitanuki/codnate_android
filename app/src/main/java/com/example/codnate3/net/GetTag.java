
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
import java.util.Random;
import java.util.UUID;

public class GetTag extends AsyncTask<Bitmap,Void, String[]>{

    //リスナー
    private Listener listener;
    //改行文字列
    @Override
    protected String[] doInBackground(Bitmap... bmp) {


        String[] tag_list = {"huwahuwa","beuty", "child","adult","kawaii","cool","yurui","wild"};
        int tag_n = tag_list.length -1;
        int tag_idx;
        Random ran = new Random();
        String[] res_tag_list = new String[4];
        String get_tag;
        for(int i = 0;i<4;i++){
            while(true){
                tag_idx = ran.nextInt(tag_n);
                get_tag = tag_list[tag_idx];
                if (!get_tag.equals("")) {
                    tag_list[tag_idx] = "";
                    res_tag_list[i] = get_tag;
                    break;
                }
            }
        }
        return res_tag_list;
        /*
        //受け取ったParamを格納
        Bitmap bitmap = bmp[0];

        //接続するためのクラスを宣言
        HttpURLConnection con = null;
        String readline = "";

        String url_text = "http://"+ AWS_INTERFACE.IPADDRESS_AI3 +"/tanuki/get_Tag";

        try {
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
            }else {
                readline = "tag conection error";
            }

            System.out.println("GetTag 77->"+readline);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {
            if(con != null) {
                //コネクション切断
                con.disconnect();
            }
        }


        String tag_list[] = readline.split(",");


        return tag_list;


         */

    }

    @Override
    protected void onPostExecute(String[] string) {
        if(listener != null){
            listener.onSuccess(string);
        }
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    public interface Listener{
        void onSuccess(String tag_list[]);
    }

}
