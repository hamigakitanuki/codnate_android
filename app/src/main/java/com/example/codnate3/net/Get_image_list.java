
package com.example.codnate3.net;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.net.HttpURLConnection;
import java.net.URL;

public class Get_image_list extends AsyncTask<String[],Void, Bitmap[]>{

    //リスナー
    private Listener listener;
    private HttpURLConnection con = null;

    @Override
    protected Bitmap[] doInBackground(String[]... paths) {
        Bitmap bmp[] = new Bitmap[paths[0].length];
        for(int i=0;i<paths[0].length;i++){
            try {
                URL url = new URL(paths[0][i]);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                int status = con.getResponseCode();
                if (status != HttpURLConnection.HTTP_OK) {
                    throw new Exception();
                }

                //file output data

                Bitmap bmp_befor = BitmapFactory.decodeStream(con.getInputStream());
                bmp[i] = Bitmap.createScaledBitmap(bmp_befor,(int)(bmp_befor.getWidth()*2),(int)(bmp_befor.getHeight()*2),true);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
            }
        }
        return bmp;
    }
    @Override
    public void onPostExecute(Bitmap bmp[]){
        if(listener != null){
            listener.onSuccess(bmp);
        }
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public interface Listener{
        void onSuccess(Bitmap bmp[]);
    }

}
