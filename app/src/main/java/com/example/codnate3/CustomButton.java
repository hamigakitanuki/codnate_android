package com.example.codnate3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

@SuppressLint("AppCompatCustomView")
public class CustomButton extends Button {
    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setPressed(boolean pressed){
        if(pressed){

            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    1.0f,0.6f/1.0f,1.0f,0.6f/1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f
            );
            scaleAnimation.setDuration((150));
            scaleAnimation.setRepeatCount(0);
            scaleAnimation.setFillAfter(true);
        }else{

            ScaleAnimation scaleAnimation = new ScaleAnimation(
                    0.6f/1.0f,1.0f,0.6f/1.0f,1.0f,
                    Animation.RELATIVE_TO_SELF,0.5f,
                    Animation.RELATIVE_TO_SELF,0.5f
            );
            scaleAnimation.setDuration((150));
            scaleAnimation.setRepeatCount(0);
            scaleAnimation.setFillAfter(true);
        }
        super.setPressed(pressed);
    }
}
