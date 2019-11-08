package com.example.codnate3;

import android.graphics.Bitmap;

public class Param {
    String filename;
    Bitmap bmp;
    String url;
    String cookie;

    Param(String filename,Bitmap bmp,String url,String cookie){
        this.filename = filename;
        this.bmp = bmp;
        this.url = url;
        this.cookie = cookie;
    }
}
