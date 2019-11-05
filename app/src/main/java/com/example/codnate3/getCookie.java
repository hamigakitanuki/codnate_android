package com.example.codnate3;


import android.net.sip.SipAudioCall;
import android.os.AsyncTask;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class getCookie extends AsyncTask {

    private SipAudioCall.Listener listener;
    @Override
    protected Object doInBackground(Object[] object) {
        String PostURL = "http://3.133.83.204/tanuki/getImage?UserNo=1";
        HttpURLConnection con = null;
        CookieManager msCookieManager = new CookieManager();
        int rescode;
        try {
            URL url = new URL(PostURL);
            //コネクションの変数にHttpURLConnection型でurlを開いて入れる
            con = (HttpURLConnection) url.openConnection();
            //接続タイムアウトの時間を設定する
            con.setConnectTimeout(100000);
            //レスポンスデータ読み取りタイムアウトを設定する
            con.setReadTimeout(100000);
            //ヘッダーにuser-Agentを設定する
            con.setRequestProperty("User-Agent", "Android");
            //ヘッダーにAccept-Languageを設定する
            con.setRequestProperty("Accept-Language", Locale.getDefault().toString());
            //コネクション上でリクエストボディ送信の許可/不許可を設定できる
            con.setDoInput(true);
            //レスポンスのボディ受信を許可する
            con.setDoInput(true);
            //リクエストをPOSTで送る
            con.setRequestMethod("GET");
            //送るデータのタイプを設定する
            con.setRequestProperty("Content-Type","application/json;charset=UTF-8" );
            //コネクションする
            con.connect();
            //HTTPレスポンスコード（結果）
            rescode = con.getResponseCode();
            if(rescode == HttpURLConnection.HTTP_OK) {
                //通信に成功している
                //クッキーの取得
                String cookieHeaders = con.getHeaderField("Set-Cookie");

            }else {
                System.out.println("コネクションできませんでしたーーー！！！！m9(^Д^)ﾌﾟｷﾞｬｰ");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(con != null) {
                //コネクション切断
                con.disconnect();
            }
        }

        return null;
    }
}
