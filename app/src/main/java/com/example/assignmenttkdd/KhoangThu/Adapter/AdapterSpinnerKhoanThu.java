package com.example.assignmenttkdd.KhoangThu.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.assignmenttkdd.KhoanChi.DTO.ObjLoaiChi;
import com.example.assignmenttkdd.KhoangThu.DTO.ObjLoaiThu;
import com.example.assignmenttkdd.R;

import java.util.List;

public class AdapterSpinnerKhoanThu extends BaseAdapter {
    private Context context;
    private List<ObjLoaiThu> arrayList;
    private int layout;

    public AdapterSpinnerKhoanThu(Context context, List<ObjLoaiThu> arrayList, int layout) {
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
        KhoanThuHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new KhoanThuHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(layout,null);
            viewHolder.name = convertView.findViewById(R.id.sp_item_kt);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (KhoanThuHolder) convertView.getTag();
        }
        viewHolder.name.setText(arrayList.get(position).getNameloaithu());
        return convertView;
    }
    public static class KhoanThuHolder{
        private TextView name;
    }
}
