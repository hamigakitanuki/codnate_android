package com.example.codnate3.object;

public class Codenate_path_list {
    public String[] tops_path;
    public String[] botoms_path;
    public String[] outer_path;
    public String[] shoese_path;
    public Codenate_path_list(String[] tops_path,String[] botoms_path,String[] outer_path,String[] shoese_path){
        this.tops_path = tops_path;
        this.botoms_path = botoms_path;
        this.outer_path = outer_path;
        this.shoese_path = shoese_path;
    }
    public boolean codnate_file_check(){
        if(0==tops_path.length){
            return false;
        }
        if(0==botoms_path.length){
            return  false;
        }
        if(0 == shoese_path.length){
            return false;
        }
        return true;
    }
    public String get_path(int cate_idx,int idx){
        switch (cate_idx % 4) {
            case 0:
                System.out.println(tops_path.length);
                if(idx<tops_path.length) {
                    if (tops_path[idx] != null) {
                        return tops_path[idx];
                    }
                }
                break;
            case 1:
                if(idx<botoms_path.length) {
                    if (botoms_path[idx] != null) {
                        return botoms_path[idx];
                    }
                }
                break;
            case 2:
                if(idx<outer_path.length) {
                    if (outer_path[idx] != null) {
                        return outer_path[idx];
                    }
                }
                break;
            case 3:
                if(idx<shoese_path.length) {
                    if (shoese_path[idx] != null) {
                        return shoese_path[idx];
                    }
                }
                break;
        }
        return "";
    }
}