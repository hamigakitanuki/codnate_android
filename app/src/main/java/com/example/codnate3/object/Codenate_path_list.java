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
    public boolean codnate_file_check(int idx){
        System.out.println(tops_path.length);
        if(idx >=tops_path.length){
            return false;
        }
        System.out.println(botoms_path.length);
        if(idx >= botoms_path.length){
            return false;
        }
        System.out.println(shoese_path.length);
        if(idx >= shoese_path.length){
            return false;
        }
        return true;
    }
    public String[] get_path(int idx){
        String codnate_path[] = new String[3];
        codnate_path[0] = tops_path[idx];
        codnate_path[1] = botoms_path[idx];
        codnate_path[2] = shoese_path[idx];
        return codnate_path;
    }
}
