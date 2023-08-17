package com.example.assignmenttkdd.ThongKe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmenttkdd.KhoanChi.DTO.ObjKhoanChi;
import com.example.assignmenttkdd.KhoangThu.DTO.ObjKhoanThu;
import com.example.assignmenttkdd.R;

import java.text.DecimalFormat;
import java.util.List;

public class AdapterKhoanChiTK extends RecyclerView.Adapter<AdapterKhoanChiTK.TKKCViewHolder>{
    private Context context;
    private List<ObjKhoanChi> arrayList;

    public AdapterKhoanChiTK(Context context) {
        this.context = context;
    }

    public void setDataKCTK(List<ObjKhoanChi> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TKKCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_thongke_kc,parent,false);
        return new TKKCViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TKKCViewHolder holder, int position) {
        ObjKhoanChi objKhoanChi = arrayList.get(position);
        if (objKhoanChi==null){
            return;
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String money = formatter.format(arrayList.get(position).getKhoanchi());
        holder.khoanchi_tk.setText(money+"VND");
        holder.name_loaichi_tk.setText(objKhoanChi.getNameloaichi());
        holder.date_kc_tk.setText(objKhoanChi.getDate_kc());
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class TKKCViewHolder extends RecyclerView.ViewHolder {
        private TextView khoanchi_tk;
        private TextView name_loaichi_tk;
        private TextView date_kc_tk;
        public TKKCViewHolder(@NonNull View itemView) {
            super(itemView);
            khoanchi_tk = itemView.findViewById(R.id.tv_khoanchi_tk);
            name_loaichi_tk = itemView.findViewById(R.id.tv_set_loaichi_tk);
            date_kc_tk = itemView.findViewById(R.id.tv_date_kc_tk);

        }
    }
}
