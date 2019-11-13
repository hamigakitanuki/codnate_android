
package com.example.codnate3;


import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class GetCodenate extends AsyncTask<String,Void, Codenate_path_list>{

    //リスナー
    private Listener listener;

    private HttpURLConnection con = null;

    @Override
    protected Codenate_path_list doInBackground(String... userNos) {
        String userNo = userNos[0];
        //POST先のURL
        String GetURL = "http://3.133.83.204/tanuki/getCodenate?UserNo="+userNo;
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
                System.out.println("not connection");
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
        //クラス変数に格納
        Codenate_path_list path_list = gson.fromJson(result,Codenate_path_list.class);

        return path_list;
    }
    @Override
    public void onPostExecute(Codenate_path_list pathlist){
        if(listener != null){
            listener.onSuccess(pathlist);
        }
    }
    void setListener(Listener listener) {
        this.listener = listener;
    }
    interface Listener{
        void onSuccess(Codenate_path_list pathlist);
    }

}
