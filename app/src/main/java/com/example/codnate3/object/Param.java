package com.example.codnate3.object;

import android.graphics.Bitmap;

public class Param {
    public String filename;
    public Bitmap bmp;
    public String cate,sub,color,tag1,tag2,tag3,tag4,vol;
    public int dress_value,casual_value,simple_value;

    public Param(String filename,
                 Bitmap bmp,
                 String cate,
                 String sub,
                 String color,
                 int dress,
                 int casual,
                 int simple,
                 String tag1,
                 String tag2,
                 String tag3,
                 String tag4,
                 String vol){


        this.cate = cate;
        this.sub = sub;
        this.color = color;
        this.dress_value = dress;
        this.casual_value = casual;
        this.simple_value = simple;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.tag4 = tag4;
        this.vol = vol;

        this.filename = filename;
        this.bmp = bmp;

    }
}
