package com.example.codnate3.fragment;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class Open_layout_animation extends Animation {
    View view;
    int add_height;
    int start_height;

    public Open_layout_animation(View view, int add_height, int start_height){
        this.view = view;
        this.start_height = start_height;
        this.add_height = add_height;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newheight = (int) (start_height + add_height * interpolatedTime);
        view.getLayoutParams().height = newheight;
        view.requestLayout();
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}
