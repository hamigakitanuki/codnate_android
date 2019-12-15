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
    public int[] dress_value_list;
    public int[] casual_value_list;
    public int[] simple_value_list;
    public String[] tag1;
    public String[] tag2;
    public String[] tag3;
    public String[] tag4;
    public float dress;
    public float casual;
    public float simple;


    public Path_List(String[] path_list,
                     String[] cate_list,
                     String[] sub_list,
                     String[] color_list,
                     float dress,
                     float casual,
                     float simple,
                     int[] dress_value_list,
                     int[] casual_value_list,
                     int[] simple_value_list,
                     String[] tag1,
                     String[] tag2,
                     String[] tag3,
                     String[] tag4
                     ){
        this.path_list = path_list;
        this.cate_list = cate_list;
        this.sub_list = sub_list;
        this.color_list = color_list;
        this.dress = dress;
        this.casual = casual;
        this.simple = simple;
        this.dress_value_list = dress_value_list;
        this.casual_value_list = casual_value_list;
        this.simple_value_list = simple_value_list;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.tag4 = tag4;
    }
}
