
package com.example.codnate3.net;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Xml;

import com.example.codnate3.AWS_INTERFACE;
import com.example.codnate3.object.Param;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class ImagePOST extends AsyncTask<Param,Void,String>{

    //リスナー
    private Listener listener;
    //遷移してきたアクティビティへの戻り値を返す時に使う
    private Activity mActivity;
    //改行文字列
    @Override
    protected String doInBackground(Param... params) {

        //受け取ったParamを格納
        Param param = params[0];
        //接続するためのクラスを宣言
        HttpURLConnection con = null;
        String readline = "";
        String url_text = "http://"+ AWS_INTERFACE.IPADDRESS +"/tanuki/imgInDB";

        try {
            //画像をJPEG形式で送れるように準備
            ByteArrayOutputStream png = new ByteArrayOutputStream();
            param.bmp.compress(Bitmap.CompressFormat.PNG,100,png);
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
            malt.textPost(printStream,"cate", param.cate,boundary);
            malt.textPost(printStream,"sub",  param.sub,boundary);
            malt.textPost(printStream,"color",param.color,boundary);
            malt.textPost(printStream,"dress",param.dress_value,boundary);
            malt.textPost(printStream,"casual",param.casual_value,boundary);
            malt.textPost(printStream,"simple",param.simple_value,boundary);
            malt.textPost(printStream,"tag1",param.tag1,boundary);
            malt.textPost(printStream,"tag2",param.tag2,boundary);
            malt.textPost(printStream,"tag3",param.tag3,boundary);
            malt.textPost(printStream,"tag4",param.tag4,boundary);
            malt.textPost(printStream,"vol",param.vol,boundary);


            malt.bitmapPost(printStream,param.filename,param.bmp,boundary);
            printStream.print("--" + boundary + "--");
            if (printStream != null) {
                printStream.close();
            }
            int rescode = con.getResponseCode();
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
