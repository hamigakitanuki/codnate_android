package com.example.codnate3.object;

public class Codenate_path_list {
    /*
    'tops_path':res_tops_path,
        'tops_color':res_tops_color,
        'tops_sub':res_tops_sub,
        'botoms_path':res_botoms_path,
        'botoms_color':res_botoms_color,
        'botoms_sub':res_botoms_sub,
        'shoese_path':res_shoese_path,
        'shoese_color':res_shoese_sub,
     */
    public String[] tops_path;
    public String[] tops_color;
    public String[] tops_sub;
    public String[] botoms_path;
    public String[] botoms_color;
    public String[] botoms_sub;
    public String[] shoese_path;
    public String[] shoese_color;
    public String[] shoese_sub;
    public Codenate_path_list(String[] tops_path,
                              String[] tops_color,
                              String[] tops_sub,
                              String[] botoms_path,
                              String[] botoms_color,
                              String[] botoms_sub,
                              String[] shoese_path,
                              String[] shoese_color,
                              String[] shoese_sub
                              
                              ){
        this.tops_path = tops_path;
        this.tops_color = tops_color;
        this.tops_sub = tops_sub;
        this.botoms_path = botoms_path;
        this.botoms_color = botoms_color;
        this.botoms_sub = botoms_sub;
        this.shoese_path = shoese_path;
        this.shoese_color = shoese_color;
        this.shoese_sub = shoese_sub;
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
    public String[] get_color(int idx){
        String[] codnate_color = new String[3];
        codnate_color[0] = tops_color[idx];
        codnate_color[1] = botoms_color[idx];
        codnate_color[2] = shoese_color[idx];
        return codnate_color;
    }
    public String[] get_sub(int idx){
        String[] codnate_sub = new String[3];
        codnate_sub[0] = tops_sub[idx];
        codnate_sub[1] = botoms_sub[idx];
        codnate_sub[2] = shoese_sub[idx];
        return codnate_sub;
    }
}
