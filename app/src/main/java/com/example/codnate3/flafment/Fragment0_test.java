package com.example.codnate3.flafment;

import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.codnate3.R;
import com.example.codnate3.net.GetCodenate;
import com.example.codnate3.net.GoodCodnate_post;
import com.example.codnate3.object.Codenate_path_list;
import com.example.codnate3.object.Codnate_user_set;

import static android.content.Context.MODE_PRIVATE;


public class Fragment0_test extends Fragment {
    boolean sw = true;
    View view;
    ImageButton goodButton,goodButton2;
    int userNo;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle saveInstanceState){


        view = inflater.inflate(R.layout.activity_main2,container,false);
//いいねボタン
        goodButton = view.findViewById(R.id.good_button);
        goodButton2 = view.findViewById(R.id.good_button2);
        goodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time = 1000;
                AnimationSet set = new AnimationSet(true);
                TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,-300);
                translateAnimation.setDuration(time);
                translateAnimation.setRepeatCount(0);
                set.addAnimation(translateAnimation);
                AlphaAnimation alphaAnimation = new AlphaAnimation(1,0.1f);
                alphaAnimation.setDuration(time);
                set.addAnimation(alphaAnimation);


                goodButton.setImageResource(R.drawable.gooded);
                goodButton2.setImageResource(R.drawable.gooded);
                goodButton2.startAnimation(set);

            }
        });
        return view;

    }




}

