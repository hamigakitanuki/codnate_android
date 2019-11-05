
package com.example.codnate3;


import android.app.Activity;
import android.graphics.Bitmap;
import android.net.sip.SipAudioCall;
import android.net.sip.SipSession;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class ImagePOST extends AsyncTask<Param,Void,String>{

    //リスナー
    private Listener listener;
    //遷移してきたアクティビティへの戻り値を返す時に使う
    private Activity mActivity;
    //改行文字列
    private static final String EOL = "\r\n";

    @Override
    protected String doInBackground(Param... params) {
        //受け取ったParamを格納
        Param param = params[0];
        //POST先のURL
        String PostURL = "http://3.133.83.204/tanuki/imgInDB";
        //読み込み先の領域
        StringBuffer sb = new StringBuffer();
        //接続するためのクラスを宣言
        HttpURLConnection con = null;
        int rescode;
        try {
            /*----GETでcookieを取得----*/
            //URLクラスを宣言
            URL url = new URL(PostURL);
            //コネクションの変数にHttpURLConnection型でurlを開いて入れる
            con = (HttpURLConnection) url.openConnection();
            //接続タイムアウトの時間を設定する
            con.setConnectTimeout(100000);
            //レスポンスデータ読み取りタイムアウトを設定する
            con.setReadTimeout(100000);
            //GETに指定
            con.setRequestMethod("GET");
            //ヘッダーにuser-Agentを設定する
            con.setRequestProperty("User-Agent", "Android");
            //ヘッダーにAccept-Languageを設定する
            //con.setRequestProperty("Accept-Language", Locale.getDefault().toString());
            //コネクション上でリクエストボディ送信の許可/不許可を設定できる
            con.setDoOutput(true);
            //レスポンスのボディ受信を許可する
            con.setDoInput(true);
            //キャッシュは使わない
            con.setUseCaches(false);
            //一度アクセスしてくる
            con.connect();
            //HTTPレスポンスコード（結果）
            rescode = con.getResponseCode();

            if(rescode == HttpURLConnection.HTTP_OK) {
                System.out.println("GET OK");
                //通信に成功している
                //画像をJPEG形式で送れるように準備
                ByteArrayOutputStream jpg = new ByteArrayOutputStream();
                param.bmp.compress(Bitmap.CompressFormat.JPEG,100,jpg);
                //クッキーの取得
                con.setRequestProperty("Cookie",con.getHeaderField("Set-Cookie"));
                //POSTに変更
                con.setRequestMethod("POST");
                //コンテンツタイプを変更
                con.setRequestProperty("Content-Type","multipart/form-data");
                //一度コネクト
                con.connect();
                //リクエストボディを書き込んでいく
                try(OutputStream out = con.getOutputStream()){
                    out.write(("Content-Disposition: form-data: name=\"file\":" +
                               "filename=\"" + param.filename +"\"" + EOL +
                               "Content-Type: application/octet-stream" + EOL + EOL)
                               .getBytes(StandardCharsets.UTF_8));
                    //JPEGを書き込んでいく
                    out.write(jpg.toByteArray());
                    out.flush();

                    //データを受け取る
                    InputStream is = con.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                    String line ;
                    while((line = reader.readLine()) != null)sb.append(line);
                    is.close();
                }
            }else {
                System.out.println("GET conection error");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(con != null) {
                //コネクション切断
                con.disconnect();
            }
        }
        return sb.toString();
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
