package com.example.assignmenttkdd.KhoanChi.AdapterLoaiChiViewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.assignmenttkdd.KhoanChi.tablayoutKhoanChi.TablayoutKhoanChiFragment;
import com.example.assignmenttkdd.KhoanChi.tablayoutLoaiChi.TablayoutLoaiChiFragment;

public class Adapter_LoaiChi extends FragmentStateAdapter {
    public Adapter_LoaiChi(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = TablayoutKhoanChiFragment.newInstance();
                break;
            case 1:
                fragment = TablayoutLoaiChiFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
