package com.example.codnate3.object;

public class Codnate_user_set {
    public String tops_path;
    public String botoms_path;
    public String shoese_path;
    public String user_no;

    public Codnate_user_set(String tops_path,String botoms_path,String shoese_path,int user_no){
        this.tops_path = tops_path;
        this.botoms_path = botoms_path;
        this.shoese_path = shoese_path;
        this.user_no = String.valueOf(user_no);
    }
}
