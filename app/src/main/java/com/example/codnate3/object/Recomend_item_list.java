package com.example.codnate3.object;

public class Recomend_item_list {

    public String[] link_url;
    public String[] image_url;
    public String[] sub;
    public String[] price;

    public Recomend_item_list(String[] link_url,
                              String[] image_url,
                              String[] sub,
                              String[] price) {
        this.link_url = link_url;
        this.image_url = image_url;
        this.sub = sub;
        this.price = price;
    }
}
