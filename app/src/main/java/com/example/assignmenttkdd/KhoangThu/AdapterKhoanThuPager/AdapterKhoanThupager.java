package com.example.assignmenttkdd.KhoangThu.AdapterKhoanThuPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.assignmenttkdd.KhoangThu.KhoangThuFragment;
import com.example.assignmenttkdd.KhoangThu.tablayoutKhoanThu.KhoanThuFragment;
import com.example.assignmenttkdd.KhoangThu.tablayoutLoaiThu.LoaiThuFragment;

public class AdapterKhoanThupager extends FragmentStateAdapter {
    public AdapterKhoanThupager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = KhoanThuFragment.newInstance();
                break;
            case 1:
                fragment = LoaiThuFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
