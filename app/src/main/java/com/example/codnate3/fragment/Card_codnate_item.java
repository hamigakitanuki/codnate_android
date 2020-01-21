package com.example.codnate3.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.codnate3.Huku_Chager;
import com.example.codnate3.R;
import com.example.codnate3.Text_to_ColorStateList;
import com.example.codnate3.net.BadCodnate_post;
import com.example.codnate3.net.Get_image;
import com.example.codnate3.net.GoodCodnate_post;
import com.example.codnate3.object.Bitmap_set;
import com.example.codnate3.object.Codnate_user_set;
import com.example.codnate3.object.Path_set;

import static android.content.Context.MODE_PRIVATE;

public class Card_codnate_item extends Fragment {


    private final String[] path;
    private final String[] color;
    private final String[] sub;
    private final String sample;
    private ImageButton goodButton;
    private ImageButton badButton;
    private ImageView goodButton2,badButton2;
    private int codnate_idx;
    private boolean good_bad_sw = true ;
    private boolean good_good = false ;
    private boolean good_bad = false ;
    private ImageView codnate[] = new ImageView[4];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public Card_codnate_item(String path[], String color[], String sub[], String sample){
        this.path = path;
        this.color = color;
        this.sub = sub;
        this.sample = sample;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_codnate_add, container, false);
        goodButton = view.findViewById(R.id.good_button);
        badButton = view.findViewById(R.id.bad_button);
        goodButton2 = view.findViewById(R.id.good_button2);
        badButton2 = view.findViewById(R.id.bad_button2);

        //いいねボタン
        goodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(good_bad_sw){
                    GoodCodnate_post goodCodnate_post = new GoodCodnate_post();
                    goodCodnate_post.setListener(good_task());
                    SharedPreferences data = getActivity().getSharedPreferences("DATA", MODE_PRIVATE);
                    int userNo = data.getInt("userNo", 0);
                    Codnate_user_set codnate = new Codnate_user_set(path[0],path[1],path[2],userNo);
                    goodCodnate_post.execute(codnate);
                    good_bad_sw = false;
                    good_good = true;
                    ToastMake("高評価ありがとうございます！",0,-300);

                    goodButton.setImageResource(R.drawable.gooded);
                }
                if(good_good){
                    int time = 800;
                    AnimationSet set = new AnimationSet(true);
                    TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,-200);
                    translateAnimation.setDuration(time);
                    translateAnimation.setRepeatCount(0);
                    set.addAnimation(translateAnimation);
                    AlphaAnimation alphaAnimation = new AlphaAnimation(1,0.1f);
                    alphaAnimation.setDuration(time);
                    set.addAnimation(alphaAnimation);
                    goodButton2.setImageResource(R.drawable.gooded);
                    goodButton2.startAnimation(set);
                }
            }
        });
        //わるいねボタン
        badButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(good_bad_sw){
                    BadCodnate_post badCodnate_post = new BadCodnate_post();
                    badCodnate_post.setListener(bad_task());
                    SharedPreferences data = getActivity().getSharedPreferences("DATA", MODE_PRIVATE);
                    int userNo = data.getInt("userNo", 0);
                    Codnate_user_set codnate = new Codnate_user_set(path[0],path[1],path[2],userNo);
                    badCodnate_post.execute(codnate);
                    good_bad_sw = false;
                    good_bad = true;
                    badButton.setImageResource(R.drawable.baded);
                    ToastMake("精進します…！",0,-300);
                }
                if (good_bad){
                    int time = 800;
                    AnimationSet set = new AnimationSet(true);
                    TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,200);
                    translateAnimation.setDuration(time);
                    translateAnimation.setRepeatCount(0);
                    set.addAnimation(translateAnimation);
                    AlphaAnimation alphaAnimation = new AlphaAnimation(1,0.1f);
                    alphaAnimation.setDuration(time);
                    set.addAnimation(alphaAnimation);
                    badButton2.setImageResource(R.drawable.baded);
                    badButton2.startAnimation(set);
                }
            }
        });

        codnate[0] = view.findViewById(R.id.main_tops_1);
        codnate[1] = view.findViewById(R.id.main_botoms1);
        codnate[2] = view.findViewById(R.id.main_shoese1);
        codnate[3] = view.findViewById(R.id.codnate_sample_image);
        Path_set path_set = null;
        for(int i=0;i<3;i++){
            path_set = new Path_set(path[i],i);
            Get_image task = new Get_image();
            task.setListener(get_image_task());
            task.execute(path_set);
        }
        if(sample.equals("") == false){
            path_set = new Path_set(sample,3);
            Get_image sample_get_task = new Get_image();
            sample_get_task.setListener(get_sample_task());
            sample_get_task.execute(path_set);
        }
        TextView tops_bar = view.findViewById(R.id.tops_color1);
        System.out.println(color[0]);
        tops_bar.setBackgroundTintList(Text_to_ColorStateList.get_ColorStateList(color[0]));
        TextView botoms_bar = view.findViewById(R.id.botoms_color1);
        botoms_bar.setBackgroundTintList(Text_to_ColorStateList.get_ColorStateList(color[1]));
        TextView shoese_bar = view.findViewById(R.id.shoese_color1);
        shoese_bar.setBackgroundTintList(Text_to_ColorStateList.get_ColorStateList(color[2]));

        TextView tops_text = view.findViewById(R.id.codnate_tops_text);
        TextView botoms_text = view.findViewById(R.id.codnate_botoms_text);
        TextView shoese_text = view.findViewById(R.id.codnate_shoese_text);
        Huku_Chager huku_chager = new Huku_Chager();
        tops_text.setText(huku_chager.color_to_text(color[0])+"の"+huku_chager.sub_to_text("tops",sub[0]));
        botoms_text.setText(huku_chager.color_to_text(color[1])+"の"+huku_chager.sub_to_text("botoms",sub[1]));
        shoese_text.setText(huku_chager.color_to_text(color[2])+"の"+huku_chager.sub_to_text("shoese",sub[2]));

        return view;
    }

    private Get_image.Listener get_image_task(){
        return new Get_image.Listener() {
            @Override
            public void onSuccess(Bitmap_set bmp_set) {
                codnate[bmp_set.idx].setImageBitmap(bmp_set.bmp);
            }
        };
    }
    private Get_image.Listener get_sample_task(){
        return new Get_image.Listener() {
            @Override
            public void onSuccess(Bitmap_set bmp) {
                codnate[bmp.idx].setImageBitmap(bmp.bmp);
            }
        };
    }

    private BadCodnate_post.Listener bad_task(){
        return new BadCodnate_post.Listener() {
            @Override
            public void onSuccess(String result) {

            }
        };
    }
    public void ToastMake(String message,int x,int y){
        //トーストの宣言
        Context context = getActivity().getApplicationContext();
        Toast toast = Toast.makeText(getActivity(),message,Toast.LENGTH_LONG);
        //位置調整
        toast.setGravity(Gravity.CENTER,x,y);
        toast.show();

    }
    private GoodCodnate_post.Listener good_task(){
        return new GoodCodnate_post.Listener() {
            @Override
            public void onSuccess(String result) {

            }
        };
    }
}
