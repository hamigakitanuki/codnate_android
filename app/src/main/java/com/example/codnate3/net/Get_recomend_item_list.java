
package com.example.codnate3.net;


import android.os.AsyncTask;
import android.widget.ListView;

import com.example.codnate3.AWS_INTERFACE;
import com.example.codnate3.object.Codenate_path_list;
import com.example.codnate3.object.Recomend_item_list;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

public class Get_recomend_item_list extends AsyncTask<String,Void, Recomend_item_list>{

    //リスナー
    private Listener listener;

    private HttpURLConnection con = null;
    private int userNo;
    public Get_recomend_item_list(int userNo){
        this.userNo = userNo;
    }

    @Override
    protected Recomend_item_list doInBackground(String... param) {
        String cate = param[0];
        //POST先のURL
        String GetURL = "http://"+ AWS_INTERFACE.IPADDRESS +"/tanuki/get_recomend_list?UserNo="+Integer.toString(userNo);
        //接続するためのクラスを宣言
        int rescode = -1;
        String result = "";
        try {
            URL url = new URL(GetURL);
            //コネクションの変数にHttpURLConnection型でurlを開いて入れる
            con = (HttpURLConnection) url.openConnection();
            //接続タイムアウトの時間を設定する
            con.setConnectTimeout(10000);
            //レスポンスデータ読み取りタイムアウトを設定する
            con.setReadTimeout(10000);
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
        Recomend_item_list recomend_item_list = gson.fromJson(result, Recomend_item_list.class);
        System.out.println(result);
        return recomend_item_list;
    }
    @Override
    public void onPostExecute(Recomend_item_list recomend_item_list){
        if(listener != null){
            listener.onSuccess(recomend_item_list);
        }
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public interface Listener{
        void onSuccess(Recomend_item_list recomend_item_list);
    }

}
