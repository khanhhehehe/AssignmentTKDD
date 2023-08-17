package com.example.assignmenttkdd.ThongKe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmenttkdd.KhoangThu.DTO.ObjKhoanThu;
import com.example.assignmenttkdd.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterKhoanThuTK extends RecyclerView.Adapter<AdapterKhoanThuTK.TKViewHolder> {
    private Context context;
    private List<ObjKhoanThu> arrayList;

    public AdapterKhoanThuTK(Context context) {
        this.context = context;
    }
    public void setData(List<ObjKhoanThu> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TKViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iten_row_thongke_kt,parent,false);
        return new TKViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TKViewHolder holder, int position) {
        ObjKhoanThu objKhoanThu = arrayList.get(position);
        if (objKhoanThu==null){
            return;
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String money = formatter.format(arrayList.get(position).getKhoanthu());
        holder.khoanthu_tk.setText(money+"VND");
        holder.name_loaithu_tk.setText(objKhoanThu.getNameloaithu());
        holder.date_tk.setText(objKhoanThu.getDate_kt());
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class TKViewHolder extends RecyclerView.ViewHolder {
        private TextView khoanthu_tk;
        private TextView name_loaithu_tk;
        private TextView date_tk;
        public TKViewHolder(@NonNull View itemView) {
            super(itemView);
            khoanthu_tk = itemView.findViewById(R.id.tv_khoanthu_tk);
            name_loaithu_tk = itemView.findViewById(R.id.tv_set_loaithu_tk);
            date_tk = itemView.findViewById(R.id.tv_date_tk);

        }
    }
}
