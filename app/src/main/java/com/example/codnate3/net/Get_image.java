
package com.example.codnate3.net;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.codnate3.object.Bitmap_set;
import com.example.codnate3.object.Path_set;
import java.net.HttpURLConnection;
import java.net.URL;

public class Get_image extends AsyncTask<String,Void, Bitmap>{

    //リスナー
    private Listener listener;
    private HttpURLConnection con = null;

    @Override
    protected Bitmap doInBackground(String... paths) {
        Bitmap bmp = null;
        try {
            URL url = new URL(paths[0]);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            int status = con.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                throw new Exception();
            }

            //file output data
            bmp = BitmapFactory.decodeStream(con.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

        return bmp;
    }
    @Override
    public void onPostExecute(Bitmap bmp){
        if(listener != null){
            listener.onSuccess(bmp);
        }
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public interface Listener{
        void onSuccess(Bitmap bmp);
    }

}
