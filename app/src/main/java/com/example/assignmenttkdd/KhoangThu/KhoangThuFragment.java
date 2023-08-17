package com.example.assignmenttkdd.KhoangThu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignmenttkdd.KhoangThu.AdapterKhoanThuPager.AdapterKhoanThupager;
import com.example.assignmenttkdd.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KhoangThuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KhoangThuFragment extends Fragment {
    private ViewPager2 mViewPager2;
    private TabLayout mTabLayout;
    private AdapterKhoanThupager adapterKhoanThupager;
    public KhoangThuFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static KhoangThuFragment newInstance() {
        KhoangThuFragment fragment = new KhoangThuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_khoang_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager2 = view.findViewById(R.id.viewpager_khoanthu);
        mTabLayout = view.findViewById(R.id.tablayout_khoanthu);
        adapterKhoanThupager = new AdapterKhoanThupager(getActivity());
        mViewPager2.setAdapter(adapterKhoanThupager);

        TabLayoutMediator layoutMediator = new TabLayoutMediator(mTabLayout, mViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("khoản thu");
                        tab.setIcon(R.drawable.ic_baseline_monetization_on_24);
                        break;
                    case 1:
                        tab.setText("loại thu");
                        tab.setIcon(R.drawable.ic_baseline_monetization_on_24);
                        break;
                }
            }
        });
        layoutMediator.attach();
    }
}