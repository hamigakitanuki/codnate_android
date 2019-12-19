
package com.example.codnate3.net;


import android.os.AsyncTask;

import com.example.codnate3.AWS_INTERFACE;
import com.example.codnate3.object.Path_List;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class Get_count extends AsyncTask<String,Void, int[]>{

    //リスナー
    private Listener listener;

    private HttpURLConnection con = null;

    @Override
    protected int[] doInBackground(String... userNos) {

        int userNo = Integer.parseInt(userNos[0]);
        String GetURL = "http://"+ AWS_INTERFACE.IPADDRESS +"/tanuki/get_photo_count?UserNo="+userNo;
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

                //ないなら指定の文字コード
                encoding = "UTF-8";

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
        String[] befor_count_list = result.split(",");
        int [] count_list = new int[befor_count_list.length];
        for(int i = 0;i<befor_count_list.length;i++){
            count_list[i] = Integer.parseInt(befor_count_list[i]);
        }
        return  count_list;
    }
    @Override
    public void onPostExecute(int[] count_list){
        if(listener != null){
            if(count_list != null){
                listener.onSuccess(count_list);
            }
        }
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public interface Listener{
        void onSuccess(int[] count_list);
    }

}
