package com.example.codnate3.flafment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.codnate3.R;
import com.example.codnate3.net.Get_image;
import com.example.codnate3.net.Get_recomend_item_tops;
import com.example.codnate3.object.Bitmap_set;
import com.example.codnate3.object.Codenate_path_list;


public class Card_fragment_codnate_item_recomend extends Fragment {

    View mView;
    String url;
    private OnFragmentInteractionListener mListener;

    public Card_fragment_codnate_item_recomend(String url){
        this.url = url;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.recomend_item_add,container,false);
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

    Get_recomend_item_tops.Listener get_reco_task(){
        return new Get_recomend_item_tops.Listener() {
            @Override
            public void onSuccess(Codenate_path_list pathlist) {

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

}
