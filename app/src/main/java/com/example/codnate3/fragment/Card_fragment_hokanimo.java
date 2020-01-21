package com.example.codnate3.fragment;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.codnate3.R;
import com.example.codnate3.net.Get_image;
import com.example.codnate3.net.Get_recomend_item_list;
import com.example.codnate3.object.Bitmap_set;
import com.example.codnate3.object.Path_set;
import com.example.codnate3.object.Recomend_item_list;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;

import static android.content.Context.MODE_PRIVATE;


public class Card_fragment_hokanimo extends Fragment {

    View mView;
    private OnFragmentInteractionListener mListener;
    PorterShapeImageView[] porterShapeImageView = new PorterShapeImageView[4];
    TextView[] item_name = new TextView[4];
    TextView[] item_price = new TextView[4];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_hokanimo,container,false);

        SharedPreferences data = getActivity().getSharedPreferences("DATA",MODE_PRIVATE);
        int userNo = data.getInt("userNo",0);
        int debug = data.getInt("debug",0);
        if(debug == 0) {
            Get_recomend_item_list get_recomend_item_list = new Get_recomend_item_list(userNo);
            get_recomend_item_list.setListener(recomend_item_list_task());
            get_recomend_item_list.execute("none");
        }

        porterShapeImageView[0] = mView.findViewById(R.id.hokanimo_image_1);
        porterShapeImageView[1] = mView.findViewById(R.id.hokanimo_image_2);
        porterShapeImageView[2] = mView.findViewById(R.id.hokanimo_image_3);
        porterShapeImageView[3] = mView.findViewById(R.id.hokanimo_image_4);

        item_name[0] = mView.findViewById(R.id.hokanimo_text1);
        item_name[1] = mView.findViewById(R.id.hokanimo_text2);
        item_name[2] = mView.findViewById(R.id.hokanimo_text3);
        item_name[3] = mView.findViewById(R.id.hokanimo_text4);

        item_price[0] = mView.findViewById(R.id.hokanimo_price1);
        item_price[1] = mView.findViewById(R.id.hokanimo_price2);
        item_price[2] = mView.findViewById(R.id.hokanimo_price3);
        item_price[3] = mView.findViewById(R.id.hokanimo_price4);

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

    Get_recomend_item_list.Listener recomend_item_list_task(){
        return  new Get_recomend_item_list.Listener() {
            @Override
            public void onSuccess(Recomend_item_list recomend_item_list) {
                try {
                    if(recomend_item_list.image_url[0].isEmpty()){
                        throw new Exception("サーバーに接続できません");
                    }
                    for (int i = 0; i < 4; i++) {
                        Path_set path_set = new Path_set(recomend_item_list.image_url[0], i);
                        Get_image get_image = new Get_image();
                        get_image.setListener(get_image_task());
                        get_image.execute(path_set);
                        item_name[i].setText(recomend_item_list.sub[i]);
                        item_price[i].setText(recomend_item_list.price[i]);
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(),"サーバーに接続できませんでした",Toast.LENGTH_SHORT);
                    e.printStackTrace();
                }
            }
        };
    }

    Get_image.Listener get_image_task(){
        return  new Get_image.Listener() {
            @Override
            public void onSuccess(Bitmap_set bmp) {
                porterShapeImageView[bmp.idx].setImageBitmap(bmp.bmp);
            }
        };
    }

}
