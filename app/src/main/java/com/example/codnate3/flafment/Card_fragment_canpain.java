package com.example.codnate3.flafment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.codnate3.R;


public class Card_fragment_canpain extends Fragment {

    View mView;
    int idx;
    private OnFragmentInteractionListener mListener;
    public Card_fragment_canpain(int idx){
        this.idx = idx;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.kyanpage,container,false);
        Button campain_button = mView.findViewById(R.id.cameraButton);
        campain_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastMake("フレー！フレー！がんばれー！",0,-300);
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
    public void ToastMake(String message,int x,int y){
        //トーストの宣言
        Context context = getActivity().getApplicationContext();
        Toast toast = Toast.makeText(getActivity(),message,Toast.LENGTH_LONG);
        //位置調整
        toast.setGravity(Gravity.CENTER,x,y);
        toast.show();

    }
}
