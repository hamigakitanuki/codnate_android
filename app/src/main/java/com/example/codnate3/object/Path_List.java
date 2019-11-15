package com.example.codnate3.object;

/*
'path_list' :path_list,
        'cate_list' :cate_list,
        'sub_list'  :sub_list,
        'color_list':color_list
 */
public class Path_List {
    public String[] path_list;
    public String[] cate_list;
    public String[] sub_list;
    public String[] color_list;

    public Path_List(String[] path_list,String[] cate_list,String[] sub_list,String[] color_list){
        this.path_list = path_list;
        this.cate_list = cate_list;
        this.sub_list = sub_list;
        this.color_list = color_list;
    }
}
