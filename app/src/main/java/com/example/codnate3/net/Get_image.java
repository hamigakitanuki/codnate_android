
package com.example.codnate3.net;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.codnate3.object.Bitmap_set;
import com.example.codnate3.object.Path_set;
import java.net.HttpURLConnection;
import java.net.URL;

public class Get_image extends AsyncTask<Path_set,Void, Bitmap_set>{

    //リスナー
    private Listener listener;
    private HttpURLConnection con = null;

    @Override
    protected Bitmap_set doInBackground(Path_set... paths) {
        Bitmap_set bmp_set = null;
        try {
            URL url = new URL(paths[0].path);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            int status = con.getResponseCode();
            if (status != HttpURLConnection.HTTP_OK) {
                throw new Exception();
            }

            //file output data
            Bitmap photo = BitmapFactory.decodeStream(con.getInputStream());
            bmp_set = new Bitmap_set(photo,paths[0].idx);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return bmp_set;
    }
    @Override
    public void onPostExecute(Bitmap_set bmp){
        if(listener != null){
            listener.onSuccess(bmp);
        }
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public interface Listener{
        void onSuccess(Bitmap_set bmp);
    }

}
