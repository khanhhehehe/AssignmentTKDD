package com.example.assignmenttkdd.KhoanChi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignmenttkdd.KhoanChi.AdapterLoaiChiViewPager.Adapter_LoaiChi;
import com.example.assignmenttkdd.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KhoanChiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KhoanChiFragment extends Fragment {
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private Adapter_LoaiChi adapterKhoanChi;
    public KhoanChiFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static KhoanChiFragment newInstance() {
        KhoanChiFragment fragment = new KhoanChiFragment();
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
        return inflater.inflate(R.layout.fragment_khoan_chi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager2 = view.findViewById(R.id.viewpager_khoanchi);
        tabLayout = view.findViewById(R.id.tablayout_khoanchi);
        adapterKhoanChi = new Adapter_LoaiChi(getActivity());
        viewPager2.setAdapter(adapterKhoanChi);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("khoản chi");
                        tab.setIcon(R.drawable.ic_baseline_currency_exchange_24);
                        break;
                    case 1:
                        tab.setText("loại chi");
                        tab.setIcon(R.drawable.ic_baseline_currency_exchange_24);
                        break;
                }
            }
        });
        tabLayoutMediator.attach();
    }

}