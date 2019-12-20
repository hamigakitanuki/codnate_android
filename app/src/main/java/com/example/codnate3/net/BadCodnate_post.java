
package com.example.codnate3.net;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Xml;

import com.example.codnate3.AWS_INTERFACE;
import com.example.codnate3.object.Codnate_user_set;
import com.example.codnate3.object.Param;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class BadCodnate_post extends AsyncTask<Codnate_user_set,Void,String>{

    //リスナー
    private Listener listener;;
    //改行文字列
    @Override
    protected String doInBackground(Codnate_user_set... codnate) {

        String tops_path = codnate[0].tops_path;
        String botoms_path = codnate[0].botoms_path;
        String shoese_path = codnate[0].shoese_path;
        String user_no = codnate[0].user_no;
        //接続するためのクラスを宣言
        HttpURLConnection con = null;
        String readline = "";
        String url_text = "http://"+ AWS_INTERFACE.IPADDRESS +"/tanuki/bad_codnate_post";

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
            con.setRequestProperty("Content-type","multipart/form-data; boundary="+boundary);
            //リクエストボディを書き込んでいく
            //一度コネクト
            con.connect();
            Malt_part_post malt = new Malt_part_post();
            PrintStream printStream = new PrintStream(con.getOutputStream(),false, Xml.Encoding.UTF_8.name());
            malt.textPost(printStream,"user_no", user_no,boundary);
            malt.textPost(printStream,"tops_path", tops_path,boundary);
            malt.textPost(printStream,"botoms_path", botoms_path,boundary);
            malt.textPost(printStream,"shoese_path", shoese_path,boundary);
            printStream.print("--" + boundary + "--");
            if (printStream != null) {
                printStream.close();
            }
            int rescode = con.getResponseCode();
            InputStream in = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            readline = br.readLine();
            br.close();
            in.close();
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
    public void onPostExecute(String string){
        super.onPostExecute(string);

        if(listener != null){
            listener.onSuccess(string);
        }
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    public interface Listener{
        void onSuccess(String result);
    }

}
