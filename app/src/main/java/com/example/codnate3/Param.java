package com.example.codnate3;

import android.graphics.Bitmap;

public class Param {
    String filename;
    Bitmap bmp;
    String cate,sub,color;

    Param(String filename,Bitmap bmp,String cate,String sub,String color){
        this.filename = filename;
        this.bmp = bmp;
        this.cate = cate;
        this.sub = sub;
        this.color = color;
    }
}
