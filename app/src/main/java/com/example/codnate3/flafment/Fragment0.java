package com.example.codnate3.flafment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.codnate3.R;
import com.example.codnate3.net.GetCodenate;
import com.example.codnate3.object.Codenate_path_list;

import static android.content.Context.MODE_PRIVATE;


public class Fragment0 extends Fragment {
    boolean sw = true;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle saveInstanceState){


        View view = inflater.inflate(R.layout.activity_main,container,false);
        if(sw){
            SharedPreferences data = this.getActivity().getSharedPreferences("DATA", MODE_PRIVATE);
            int userNo = data.getInt("userNo", 0);



            LinearLayout linearLayout = getActivity().findViewById(R.id.fragment_codnate_liner);
            linearLayout.setVisibility(View.INVISIBLE);
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.load_tanuki, Load_Flagment.newInstance()).commit();


            sw = false;

            GetCodenate getCodenate = new GetCodenate();
            getCodenate.setListener(create_codnate_task());
            getCodenate.execute(String.valueOf(userNo));

        }
        return view;

    }

    private GetCodenate.Listener create_codnate_task() {
        return new GetCodenate.Listener() {
            @Override
            public void onSuccess(Codenate_path_list pathlist) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                if ( pathlist != null){
                    for(int i = 0;i<3;i++){
                        if(pathlist.codnate_file_check(i)){
                            Codnate_add codnate_add = new Codnate_add(pathlist.get_path(i));
                            fragmentTransaction.add(R.id.codnate_liner_list,codnate_add);
                        }else{
                            break;
                        }
                    }
                }
                LinearLayout linearLayout = getActivity().findViewById(R.id.fragment_codnate_liner);
                linearLayout.setVisibility(View.VISIBLE);
                FrameLayout frameLayout = getActivity().findViewById(R.id.load_tanuki);
                frameLayout.setVisibility(View.INVISIBLE);
                fragmentTransaction.commit();

            }
        };
    }


}

