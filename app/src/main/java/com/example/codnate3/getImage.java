
package com.example.codnate3;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class getImage extends AsyncTask<String,Void,String[]>{

    //リスナー
    private Listener listener;

    private HttpURLConnection con = null;

    @Override
    protected String[] doInBackground(String... urls) {

        //POST先のURL
        String GetURL = urls[0];
        //接続するためのクラスを宣言
        int rescode = -1;
        String result = "";
        try {
            URL url = new URL(GetURL);
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
            //レスポンスのボディ受信を許可する
            con.setDoInput(true);
            //リクエストをGETで送る
            con.setRequestMethod("GET");
            //コネクションする
            con.connect();
            //HTTPレスポンスコード（結果）
            rescode = con.getResponseCode();

            if(rescode == HttpURLConnection.HTTP_OK) {
                //通信に成功している
                //テキストを取得する
                final InputStream in = con.getInputStream();
                //エンコードするクラスを作成
                String encoding = con.getContentEncoding();

                if(null == encoding) {
                    //ないなら指定の文字コード
                    encoding = "UTF-8";
                }
                //送られてきた文字列を格納
                final InputStreamReader inReader = new InputStreamReader(in,encoding);
                //こちらで処理できるように格納
                final BufferedReader bufReader = new BufferedReader(inReader);
                //表示する文字列を格納する変数
                result = bufReader.readLine();
                //ストリームを閉じるfinal型だから閉じなければまた使えてしまう
                bufReader.close();
                inReader.close();
                in.close();
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
        System.out.println("result:"+result);
        //jsonで読み込めるようにGSON宣言
        Gson gson = new Gson();
        //文字列の両端を削除
        String path = result.substring(14, result.length()-1);
        //配列に変換
        String[] pathlist = gson.fromJson(path,String[].class);
        System.out.println("pathlist:"+pathlist);
        //読み込むためのストリームを宣言

        return pathlist;
    }
    @Override
    public void onPostExecute(String[] string){
        if(listener != null){
            listener.onSuccess(string);
        }
    }
    void setListener(Listener listener) {
        this.listener = listener;
    }
    interface Listener{
        void onSuccess(String[] pathlist);
    }

}
