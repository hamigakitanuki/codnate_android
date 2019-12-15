package com.example.codnate3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Huku_Chager {
    private final List<String> cate_select_text =  new ArrayList<>(Arrays.asList("トップス","ボトムス","ワンピース","アウター"));
    private final List<String> tops_sub_text =  new ArrayList<>(Arrays.asList("ブラウス","ビスチェ","キャミソール","カットソー","インナー","ニット","オフショルダー","パーカー","ポロシャツ","プルオーバー","シャツ","セーター","スウェット","Tシャツ","タンクトップ","チュニック","その他"));
    private final List<String> botoms_sub_text =  new ArrayList<>(Arrays.asList("カーゴパンツ","クロップドパンツ","デニム","デニムスカート","フレアスカート","ハーフパンツ","マキシ丈スカート","ミモレスカート","ミニスカート","プリーツスカート","スキニーパンツ","スウェットパンツ","タイトスカート","タックパンツ","チュールスカート","ワイドパンツ","その他"));
    private final List<String> onepeace_sub_text =  new ArrayList<>(Arrays.asList("オールインワン","キャミドレス","コンビネゾン","ドレス","ひざ丈スカート","マキシドレス","ミニドレス","ニットドレス","ワンピース","オーバーオール","サルペット","シャツドレス","その他"));
    private final List<String> outer_sub_text =   new ArrayList<>(Arrays.asList("ブルゾン","ポンチョ","カーディガン","チェスターコート","コート","カラーレスコート","デニムジャケット","ダウンコート","ダウンジャケット","ダウンベスト","ダッフルコート","ガウン","ファーコート","ジャケット","レザージャケット","MA1","ミリタリージャケット","モッズコート","マウンテンパーカー","マウンテンコート","ピーコート","レインコート","その他"));

    private final ArrayList<String> tag_text = new ArrayList<>(Arrays.asList("ふわふわ", "きれい", "子供っぽい", "大人っぽい", "かわいい", "かっこいい", "ゆるい", "ワイルド",""));
    private final ArrayList<String> type_text = new ArrayList<>(Arrays.asList("ドレス", "カジュアル", "シンプル"));
    private final ArrayList<String> vol_text = new ArrayList<>(Arrays.asList("派手","控え目"));
    private final ArrayList<String> color_text = new ArrayList<>(Arrays.asList("ブラック", "ブルー", "ブラウン", "グレー", "グリーン", "オレンジ", "ピンク", "パープル", "レッド", "ホワイト", "イエロー","その他"));
    private final List<String> cate_select = new ArrayList<>(Arrays.asList("tops", "botoms", "onepeace", "outer"));
    private final List<String> tops_sub =  new ArrayList<>(Arrays.asList("blouse","busiter","camisole","cut-and-saw","inner","knit","offshoulder","parker","poloshirt","pullover","shirt","sweater","swrat", "t-shirt","tanktop","tunic","other"));
    private final List<String> botoms_sub =  new ArrayList<>(Arrays.asList("cargopants","qloppedepants","denim","denimskirt","flareskirt","harfpants","maxilengthskirt","mimoreskirt","miniskirt","pleatedskirt","skinnypants","sweatpants","tightskirt","tuckpants","tuleskirt", "widepants","other"));
    private final List<String> onepeace_sub =  new ArrayList<>(Arrays.asList("all-in-one","camisole","convenience","dress","knee-lengthdress","maxidress","minidress","nittodress","onepeace","overalls","saropetto","shirtdress","other"));
    private final List<String> outer_sub =  new ArrayList<>(Arrays.asList("blouson","boncho", "cardigan","chester-coat","coat","color-less-coat","denim-jacket","down-coat","down-jacket", "down-vest","duffle-coat","gown","hur-coat","jacket","leather-jacket","ma1","military-acket","mods-coat","moutain-perker","mouton-coat","pcoat","raincoat","other"));
    private final List<String> color_select =  new ArrayList<>(Arrays.asList("black","blue", "brown","gray","green","orange","pink","purple","red", "white","yellow","other"));
    private final List<String> tag_select =  new ArrayList<>(Arrays.asList("huwahuwa","beuty", "child","adult","kawaii","cool","yurui","wild",""));
    private final List<String> type_select =  new ArrayList<>(Arrays.asList("dress","casual", "simmple"));
    private final List<String> vol_select =  new ArrayList<>(Arrays.asList("hade","hikaeme"));

    public int cate_get_idx(String cate){
        return cate_select.indexOf(cate);
    }
    public int sub_get_idx(String cate,String sub){
        int cate_idx = cate_select.indexOf(cate);
        switch (cate_idx) {
            case 0:
                return tops_sub.indexOf(sub);
            case 1:
                return botoms_sub.indexOf(sub);
            case 2:
                return onepeace_sub.indexOf(sub);
            case 3:
                return outer_sub.indexOf(sub);
        }
        System.out.println("Huku_Changer 43->error cate:"+cate+" sub:"+sub);
        return 0;
    }
    public int color_get_idx(String color){
        return color_select.indexOf(color);
    }
    public String cate_to_text(String cate){
        return cate_select_text.get(cate_get_idx(cate));
    }
    public String sub_to_text(String cate,String sub) {
        System.out.println("Huku_Changer53->cate:"+cate+" sub:"+sub);
        int cate_idx = cate_get_idx(cate);
        switch (cate_idx) {
            case 0:
                return tops_sub_text.get(tops_sub.indexOf(sub));
            case 1:
                return botoms_sub_text.get(botoms_sub.indexOf(sub));
            case 2:
                return onepeace_sub_text.get(onepeace_sub.indexOf(sub));
            case 3:
                return outer_sub_text.get(outer_sub.indexOf(sub));
        }
        System.out.println("Huku_Changer 65->サブ変換でエラーはいてます");

        return null;
    }
    public String color_to_text(String color){
        return color_text.get(cate_get_idx(color));
    }
    public String type_to_text(String type){
        return type_text.get(type_select.indexOf(type));
    }
    public String tag_to_text(String tag){
        System.out.println("Huku_Changer 76->chage to tag"+tag);
        return tag_text.get(tag_select.indexOf(tag));
    }
    public String vol_to_text(String vol){
        return vol_text.get(vol_select.indexOf(vol));
    }
    public String cate_text_to_eng(String cate){
        if(cate.isEmpty()){
            return "";
        }
        return cate_select.get(cate_select_text.indexOf(cate));
    }
    public String sub_text_to_eng(String cate,String sub){
        if(cate==""){
            return "";
        }
        if(sub==""){
            return "";
        }
        int cate_idx = cate_get_idx(cate);
        switch (cate_idx){
            case 0:
                return tops_sub.get(tops_sub_text.indexOf(sub));
            case 1:
                return botoms_sub.get(botoms_sub_text.indexOf(sub));
            case 2:
                return onepeace_sub.get(onepeace_sub_text.indexOf(sub));
            case 3:
                return outer_sub.get(outer_sub_text.indexOf(sub));
        }
        System.out.print("Huku_Changer 97->サブの英語変換でエラーです。");
        return null;
    }
    public String color_text_to_eng(String color){
        if(color.isEmpty()){
            return "";
        }
        return color_select.get(color_text.indexOf(color));
    }
}
