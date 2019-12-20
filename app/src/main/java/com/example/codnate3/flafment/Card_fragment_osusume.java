package com.example.codnate3.flafment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

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
        mView = inflater.inflate(R.layout.hokanimo,container,false);
        
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
}
