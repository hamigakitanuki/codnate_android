package com.example.codnate3.object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Huku_info {
    public String[] sub_list;
    public String cate;
    public String sub;
    public String[] type;
    public float[] type_value;
    public String[] tag;
    public int cate_idx;
    public int[] sub_idx = new int[4];

    public List<String> cate_select_text; //=  new ArrayList<>(Arrays.asList("トップス","ボトムス","ワンピース","ワンピース"));
    public List<String> tops_sub_text;// =  new ArrayList<>(Arrays.asList("ブラウス","ビスチェ","キャミソール","カットソー","インナー","ニット","オフショルダー","パーカー","ポロシャツ","プルオーバー","シャツ","セーター","Tシャツ","タンクトップ","チュニック","その他"));
    public List<String> botoms_sub_text;// =  new ArrayList<>(Arrays.asList("カーゴパンツ","クロップドパンツ","デニム","デニムスカート","フレアスカート","ハーフパンツ","マキシ丈スカート","ミモレスカート","ミニスカート","プリーツスカート","スキニーパンツ","スウェットパンツ","タイトスカート","タックパンツ","チュールスカート","ワイドパンツ"));
    public List<String> onepeace_sub_text;// =  new ArrayList<>(Arrays.asList("オールインワン","キャミドレス","コンビネゾン","ドレス","ひざ丈スカート","マキシドレス","ミニドレス","ニットドレス","ワンピース","オーバーオール","サルペット","シャツドレス"));
    public List<String> outer_sub_text;// =  new ArrayList<>(Arrays.asList("ブルゾン","ポンチョ","カーディガン","チェスターコート","コート","カラーレスジャケット","デニムジャケット","ダウンコート","ダウンジャケット","ダウンベスト","ダッフルコート","ガウン","ファーコート","ジャケット","レザージャケット","MA_1","ミリタリージャケット","モッズコート","マウンテンジャケット","ムートンコート","ピーコート","レインコート"));




    public Huku_info(String cate, String sub, String[] type, float[] type_value, String[] tag){
        this.type = type;
        this.type_value = type_value;
        this.sub = sub;
        this.cate = cate;
        this.tag = tag;


    }
    public void get_cate_idx(){
        cate_select_text =  new ArrayList<>(Arrays.asList("トップス","ボトムス","ワンピース","アウター"));
        tops_sub_text =  new ArrayList<>(Arrays.asList("ブラウス","ビスチェ","キャミソール","カットソー","インナー","ニット","オフショルダー","パーカー","ポロシャツ","プルオーバー","シャツ","セーター","Tシャツ","タンクトップ","チュニック","その他"));
        botoms_sub_text =  new ArrayList<>(Arrays.asList("カーゴパンツ","クロップドパンツ","デニム","デニムスカート","フレアスカート","ハーフパンツ","マキシ丈スカート","ミモレスカート","ミニスカート","プリーツスカート","スキニーパンツ","スウェットパンツ","タイトスカート","タックパンツ","チュールスカート","ワイドパンツ"));
        onepeace_sub_text =  new ArrayList<>(Arrays.asList("オールインワン","キャミドレス","コンビネゾン","ドレス","ひざ丈スカート","マキシドレス","ミニドレス","ニットドレス","ワンピース","オーバーオール","サルペット","シャツドレス"));
        outer_sub_text =  new ArrayList<>(Arrays.asList("ブルゾン","ポンチョ","カーディガン","チェスターコート","コート","カラーレスジャケット","デニムジャケット","ダウンコート","ダウンジャケット","ダウンベスト","ダッフルコート","ガウン","ファーコート","ジャケット","レザージャケット","MA_1","ミリタリージャケット","モッズコート","マウンテンジャケット","ムートンコート","ピーコート","レインコート"));

        List<String> cate_select = new ArrayList<>(Arrays.asList("tops", "botoms", "onepeace", "outer"));
        List<String> tops_sub =  new ArrayList<>(Arrays.asList("blouse","busiter","camisole","cut-and-saw","inner","knit","offshoulder","parker","poloshirt","pullover","shirt","sweater","swrat", "t-shirt","tanktop","tunic"));
        List<String> botoms_sub =  new ArrayList<>(Arrays.asList("cargopants","qloppedepants","denim","denimskirt","flareskirt","harfpants","maxilengthskirt","mimoreskirt","miniskirt","pleatedskirt","skinnypants","sweatpants","tightskirt","tuckpants","tuleskirt", "widepants"));
        List<String> onepeace_sub =  new ArrayList<>(Arrays.asList("all-in-one","camisole","convenience","dress","knee-lengthdress","maxidress","minidress","nittodress","onepeace","overalls","saropetto","shirtdress"));
        List<String> outer_sub =  new ArrayList<>(Arrays.asList("blouson","boncho", "cardigan","chester-coat","coat","color-less-coat","denim-jacket","down-coat","down-jacket", "down-vest","duffle-coat","gown","hur-coat","jacket","leather-jacket","ma1","military-acket","mods-coat","moutain-perker","mouton-coat","pcoat","raincoat" ));
        sub_idx = new int[3];

        cate_idx = cate_select.indexOf(cate);
        switch (cate_idx){
            case 0:
                sub_list = sub.split("_",0);
                for(int i = 0;i<sub_list.length;i++){
                    sub_idx[i] = tops_sub.indexOf(sub_list[i]);
                    sub_list[i] = tops_sub_text.get(sub_idx[i]);
                }
                break;
            case 1:
                sub_list = sub.split("_",0);
                for(int i = 0;i<sub_list.length;i++){
                    sub_idx[i] = botoms_sub.indexOf(sub_list[i]);
                    sub_list[i] = botoms_sub_text.get(sub_idx[i]);
                    System.out.println("sublist:"+sub_list + " sub_idx"+sub_idx);
                }
                break;
            case 2:
                sub_list = sub.split("_",0);
                for(int i = 0;i<sub_list.length;i++){
                    sub_idx[i] = onepeace_sub.indexOf(sub_list[i]);
                    sub_list[i] = onepeace_sub_text.get(sub_idx[i]);
                }
                break;
            case 3:
                sub_list = sub.split("_",0);
                for(int i = 0;i<sub_list.length;i++) {
                    sub_idx[i] = outer_sub.indexOf(sub_list[i]);
                    sub_list[i] = outer_sub_text.get(sub_idx[i]);
                }
                break;
            default:
                break;
        }

    }
}
