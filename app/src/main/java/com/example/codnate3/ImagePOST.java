
package com.example.codnate3;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Xml;

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
    private static final String ENTER_STRING = "\r\n";
    private String readline;
    @Override
    protected String doInBackground(Param... params) {

        //受け取ったParamを格納
        Param param = params[0];
        //読み込み先の領域
        StringBuffer sb = new StringBuffer();
        //接続するためのクラスを宣言
        HttpURLConnection con = null;
        int rescode;
        try {
            //画像をJPEG形式で送れるように準備
            ByteArrayOutputStream png = new ByteArrayOutputStream();
            param.bmp.compress(Bitmap.CompressFormat.PNG,100,png);
            //URLクラス宣言
            URL url = new URL(param.url);
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
            System.out.println(param.filename);
            //OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            PrintStream printStream = new PrintStream(con.getOutputStream(),false, Xml.Encoding.UTF_8.name());
            printStream.print("--" + boundary);
            printStream.print(ENTER_STRING);
            printStream.print("Content-Disposition: form-data; name=\"image\"; filename=\""+param.filename+".png\"");
            printStream.print(ENTER_STRING);
            printStream.print("Content-Type: " + "application/octet-stream");
            printStream.print(ENTER_STRING);
            printStream.print("Content-Transfer-Encoding: binary");
            printStream.print(ENTER_STRING);
            printStream.print(ENTER_STRING);
            ByteArrayOutputStream bos = null;
            try {
                bos = new ByteArrayOutputStream();
                param.bmp.compress(Bitmap.CompressFormat.PNG, 100, bos);
                printStream.write(bos.toByteArray());
            } finally {
                if (bos != null) {
                    bos.close();
                }
            }
            printStream.print(ENTER_STRING);
            printStream.flush();
            printStream.print("--" + boundary + "--");
            //out.close();
            if (printStream != null) {
                printStream.close();
            }
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
    public void onPostExecute(String string){
        super.onPostExecute(string);

        if(listener != null){
            listener.onSuccess(string);
        }
    }

    void setListener(Listener listener){
        this.listener = listener;
    }

    interface Listener{
        void onSuccess(String result);
    }

}
