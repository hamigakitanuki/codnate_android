package com.example.codnate3.object;

import android.graphics.Bitmap;

public class Param {
    public String filename;
    public Bitmap bmp;
    public String cate,sub,color,type,tag1,tag2,tag3,tag4;

    public Param(String filename,
                 Bitmap bmp,
                 String cate,
                 String sub,
                 String color,
                 String type,
                 String tag1,
                 String tag2,
                 String tag3,
                 String tag4){
        this.filename = filename;
        this.bmp = bmp;
        this.cate = cate;
        this.sub = sub;
        this.color = color;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.tag4 = tag4;
    }
}
