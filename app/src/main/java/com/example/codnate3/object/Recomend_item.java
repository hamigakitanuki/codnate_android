package com.example.codnate3.object;

import java.io.Serializable;

public class Recomend_item implements Serializable {
    String title;
    String path;
    String subtitle;

    public Recomend_item(String title,String path,String subtitle){
        this.title = title;
        this.path = path;
        this.subtitle = subtitle;
    }
}
