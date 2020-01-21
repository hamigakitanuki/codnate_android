package com.example.codnate3.fragment;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.codnate3.R;
import com.example.codnate3.net.Get_image;
import com.example.codnate3.net.Get_recomend_item;
import com.example.codnate3.object.Bitmap_set;
import com.example.codnate3.object.Codenate_path_list;
import com.example.codnate3.object.Path_set;

import static android.content.Context.MODE_PRIVATE;


public class Card_fragment_codnate_item_recomend extends Fragment {

    View mView;
    String url;
    ImageView tops_image,botoms_image,shoese_image;
    private OnFragmentInteractionListener mListener;

    public Card_fragment_codnate_item_recomend(String url){
        this.url = url;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_recomend_item_add,container,false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATA", MODE_PRIVATE);
        int user_id = sharedPreferences.getInt("userNo",0);
        int debug = sharedPreferences.getInt("debug",0);
        if(debug == 0) {
            Get_recomend_item get_recomend_item_ = new Get_recomend_item(user_id);
            get_recomend_item_.setListener(get_reco_task());
            get_recomend_item_.execute(url);
        }
        switch (url){
            case "tops":
                tops_image = mView.findViewById(R.id.recomend_item_image);
                botoms_image = mView.findViewById(R.id.recomend_fititem1_image);
                shoese_image = mView.findViewById(R.id.recomend_fititem2_image);
                break;
            case "botoms":
                tops_image = mView.findViewById(R.id.recomend_fititem1_image);
                botoms_image = mView.findViewById(R.id.recomend_item_image);
                shoese_image = mView.findViewById(R.id.recomend_fititem2_image);
                break;

            case "shoese":
                tops_image = mView.findViewById(R.id.recomend_fititem1_image);
                botoms_image = mView.findViewById(R.id.recomend_fititem2_image);
                shoese_image = mView.findViewById(R.id.recomend_item_image);
                break;
        }
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
            public void onSuccess(Codenate_path_list pathlist) {
                try {

                    if (pathlist.tops_sub[0].isEmpty()) {
                        throw new Exception("コーディネート　リコメンド　アイテム　を取得できませんでした");
                    }

                    Path_set tops_path_set = new Path_set(pathlist.tops_path[0], 0);
                    Get_image get_tops_image = new Get_image();
                    get_tops_image.setListener(get_image_task());
                    get_tops_image.execute(tops_path_set);

                    Path_set botoms_path_set = new Path_set(pathlist.botoms_path[0], 1);
                    Get_image get_botoms_image = new Get_image();
                    get_botoms_image.setListener(get_image_task());
                    get_botoms_image.execute(botoms_path_set);

                    Path_set shoese_path_set = new Path_set(pathlist.shoese_path[0], 2);
                    Get_image get_shoese_image = new Get_image();
                    get_shoese_image.setListener(get_image_task());
                    get_shoese_image.execute(shoese_path_set);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
    }

    Get_image.Listener get_image_task(){
        return new Get_image.Listener() {
            @Override
            public void onSuccess(Bitmap_set bmp) {
                try {
                    if (bmp.idx == 0) {
                        throw new Exception("イメージを取得できませんでした");
                    }
                    switch (bmp.idx) {
                        case 0:
                            tops_image.setImageBitmap(bmp.bmp);
                            break;
                        case 1:
                            botoms_image.setImageBitmap(bmp.bmp);
                            break;
                        case 2:
                            shoese_image.setImageBitmap(bmp.bmp);
                            break;
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(),"イメージを取得できませんでした。",Toast.LENGTH_SHORT);
                    e.printStackTrace();
                }
            }
        };
    }

}
