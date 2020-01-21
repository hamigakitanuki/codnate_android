package com.example.codnate3.object;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Bitmap_path_set implements Serializable {
    public Bitmap bmp;
    public String path;

    public Bitmap_path_set(Bitmap bmp, String path){
        this.bmp = bmp;
        this.path = path;
    }


}
