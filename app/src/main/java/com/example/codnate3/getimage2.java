
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

public class getimage2 extends AsyncTask<Path_set,Void,Bitmap_set>{

    //リスナー
    private Listener listener;

    private HttpURLConnection con = null;

    @Override
    protected Bitmap_set doInBackground(Path_set... Path_set) {
        Bitmap_set bm_set = null;
        //XML上の画像(Image View)を取得
        try {
            URL url = new URL(Path_set[0].path);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            int status = con.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                throw new Exception();
            }

            //file output data
            Bitmap bmp = BitmapFactory.decodeStream(con.getInputStream());
            int idx = Path_set[0].idx;

            bm_set = new Bitmap_set(bmp,idx);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

        return bm_set;
    }
    @Override
    public void onPostExecute(Bitmap_set bmp){
        if(listener != null){
            listener.onSuccess(bmp);
        }
    }
    void setListener(Listener listener) {
        this.listener = listener;
    }
    interface Listener{
        void onSuccess(Bitmap_set bmp);
    }

}
