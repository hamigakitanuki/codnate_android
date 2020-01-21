package com.example.codnate3.fragment;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class Open_layout_animation_horizon extends Animation {
    View view;
    int add_width;
    int start_width;

    public Open_layout_animation_horizon(View view, int add_width, int start_width){
        this.view = view;
        this.start_width = start_width;
        this.add_width = add_width;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newWidth = (int) (start_width + add_width * interpolatedTime);
        view.getLayoutParams().width = newWidth;
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
