package com.example.assignmenttkdd.KhoanChi.AdapterObjLoaiChi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignmenttkdd.KhoanChi.DTO.ObjLoaiChi;
import com.example.assignmenttkdd.R;

import java.util.List;

public class AdapterSpinnerKhoanChi extends BaseAdapter {
    private Context context;
    private List<ObjLoaiChi> arrayList;
    private int layout;

    public AdapterSpinnerKhoanChi(Context context, List<ObjLoaiChi> arrayList, int layout) {
        this.context = context;
        this.arrayList = arrayList;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        KhoanChiHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new KhoanChiHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(layout,null);
            viewHolder.name = convertView.findViewById(R.id.sp_item);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (KhoanChiHolder) convertView.getTag();
        }
        viewHolder.name.setText(arrayList.get(position).getNameloaichi());
        return convertView;
    }
    public static class KhoanChiHolder{
        private TextView name;
    }
}
