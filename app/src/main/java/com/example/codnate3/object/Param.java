package com.example.codnate3.object;

import android.graphics.Bitmap;

public class Param {
    public String filename;
    public Bitmap bmp;
    public String cate,sub,color,type,tag1,tag2,tag3,tag4,vol;

    public Param(String filename,
                 Bitmap bmp,
                 String cate,
                 String sub,
                 String color,
                 String type,
                 String tag1,
                 String tag2,
                 String tag3,
                 String tag4,
                 String vol){


        this.cate = cate;
        this.sub = sub;
        this.color = color;
        this.type = type;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.tag4 = tag4;
        this.vol = vol;

        this.filename = filename;
        this.bmp = bmp;

    }
}
