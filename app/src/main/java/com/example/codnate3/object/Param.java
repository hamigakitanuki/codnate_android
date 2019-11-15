package com.example.codnate3.object;

import android.graphics.Bitmap;

public class Param {
    public String filename;
    public Bitmap bmp;
    public String cate,sub,color;

    public Param(String filename,Bitmap bmp,String cate,String sub,String color){
        this.filename = filename;
        this.bmp = bmp;
        this.cate = cate;
        this.sub = sub;
        this.color = color;
    }
}
