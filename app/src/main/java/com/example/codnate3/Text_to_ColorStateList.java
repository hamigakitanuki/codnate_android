package com.example.codnate3;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.widget.RelativeLayout;


public class Text_to_ColorStateList {
    public static ColorStateList get_ColorStateList(String color_text){
        switch (color_text){
            case "black":
                return ColorStateList.valueOf(Color.parseColor("#000000"));
            case "blue":
                return ColorStateList.valueOf(Color.parseColor("#2196F3"));
            case "brown":
                return ColorStateList.valueOf(Color.parseColor("#795548"));
            case "gray":
                return ColorStateList.valueOf(Color.parseColor("#607D8B"));
            case "green":
                return ColorStateList.valueOf(Color.parseColor("#4CAF50"));
            case "orange":
                return ColorStateList.valueOf(Color.parseColor("#FF9800"));
            case "pink":
                return ColorStateList.valueOf(Color.parseColor("#F06292"));
            case "purple":
                return ColorStateList.valueOf(Color.parseColor("#9C27B0"));
            case "red":
                return ColorStateList.valueOf(Color.parseColor("#f44336"));
            case "white":
                return ColorStateList.valueOf(Color.parseColor("#FAFAFA"));
            case "yellow":
                return ColorStateList.valueOf(Color.parseColor("#FFEB3B"));

        }
        return null;
    }
}
