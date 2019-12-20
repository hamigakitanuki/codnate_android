package com.example.codnate3.flafment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.codnate3.R;
import com.example.codnate3.net.Get_image;
import com.example.codnate3.net.Get_recomend_item;
import com.example.codnate3.object.Bitmap_set;
import com.example.codnate3.object.Recomend_item;


public class Card_fragment_osusume extends Fragment {

    View mView;

    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.osusume,container,false);
        Button osusumeButton = mView.findViewById(R.id.recomend_link_button);
        Button osusumeButton2 = mView.findViewById(R.id.recomend_link_button2);
        osusumeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastMake("ぐーぐー、作業に疲れたぬき～",0,-300);
            }
        });
        osusumeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastMake("ぐーぐー、作業に疲れたぬき～",0,-300);
            }
        });
        return mView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    Get_recomend_item.Listener get_reco_task(){
        return new Get_recomend_item.Listener() {
            @Override
            public void onSuccess(Recomend_item item) {

            }
        };
    }

    Get_image.Listener get_image_task(){
        return new Get_image.Listener() {
            @Override
            public void onSuccess(Bitmap_set bmp) {
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
}
