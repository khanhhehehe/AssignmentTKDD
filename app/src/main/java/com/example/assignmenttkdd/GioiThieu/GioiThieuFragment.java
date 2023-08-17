package com.example.assignmenttkdd.GioiThieu;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignmenttkdd.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GioiThieuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GioiThieuFragment extends Fragment {
    private int[] myImgList = new int[]{R.drawable.img,R.drawable.bg1,R.drawable.bg2,R.drawable.bg3};
    private int i;
    public GioiThieuFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static GioiThieuFragment newInstance() {
        GioiThieuFragment fragment = new GioiThieuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gioi_thieu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button backbg = view.findViewById(R.id.back);
        Button nextbg = view.findViewById(R.id.next);
        TextView tv_background = view.findViewById(R.id.tv_background);
        i=0;
        backbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i--;
                if(i<0){
                    i=myImgList.length-1;
                    tv_background.setBackgroundResource(myImgList[i]);
                }else {
                    tv_background.setBackgroundResource(myImgList[i]);
                }
            }
        });
        nextbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if(i>myImgList.length-1){
                    i=0;
                    tv_background.setBackgroundResource(myImgList[i]);
                }else {
                    tv_background.setBackgroundResource(myImgList[i]);
                }
            }
        });
    }
}