package com.example.assignmenttkdd.KhoangThu.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmenttkdd.KhoanChi.DTO.ObjLoaiChi;
import com.example.assignmenttkdd.KhoanChi.DatabaseKhoanChi.LoaiChiDB;
import com.example.assignmenttkdd.KhoangThu.DTO.ObjLoaiThu;
import com.example.assignmenttkdd.KhoangThu.DataBase.LoaiThuDB;
import com.example.assignmenttkdd.R;
import java.util.List;

public class AdapterLoaiThu extends RecyclerView.Adapter<AdapterLoaiThu.ObjLoaiThuViewHolder>{
    private Context context;
    private List<ObjLoaiThu> arrayList;
    private ObjLoaiThu objLoaiThunew;

    public AdapterLoaiThu(Context context) {
        this.context = context;
    }

    public void setDataLoaiThu(List<ObjLoaiThu> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ObjLoaiThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_loaithu, parent, false);
        return new ObjLoaiThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObjLoaiThuViewHolder holder, int position) {
        ObjLoaiThu objLoaiThu = arrayList.get(position);
        if (objLoaiThu == null) {
            return;
        }
        holder.nameloaithu.setText(objLoaiThu.getNameloaithu());
        holder.edt_loaithu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setView(R.layout.layout_update_lt);
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog dlg = (AlertDialog) dialog;
                        EditText name_new = dlg.findViewById(R.id.edt_product_lt_new);

                        if (name_new.getText().toString().trim().isEmpty()){
                            Toast.makeText(context, "Tên không được để trống", Toast.LENGTH_SHORT).show();
                        }else if(name_new.getText().toString().trim().equals(objLoaiThu.getNameloaithu())) {
                            Toast.makeText(context, "Tên không có gì thay đổi", Toast.LENGTH_SHORT).show();
                        }else {
                            objLoaiThunew = objLoaiThu;
                            objLoaiThunew.setNameloaithu(name_new.getText().toString());
                            LoaiThuDB.getInstance(v.getContext()).loaiThuDAO().updateObjLoaiThu(objLoaiThunew);
                            arrayList = LoaiThuDB.getInstance(v.getContext()).loaiThuDAO().getListObjLT();
                            setDataLoaiThu(arrayList);
                            Toast.makeText(context, "Update successfully", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });
        holder.delete_loaithu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Xóa");
                builder.setMessage("Bạn có chắc muốn xóa không?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoaiThuDB.getInstance(v.getContext()).loaiThuDAO().getObjidLT(objLoaiThu.getId_lt());
                        arrayList = LoaiThuDB.getInstance(v.getContext()).loaiThuDAO().getListObjLT();
                        setDataLoaiThu(arrayList);
                        Toast.makeText(context, "Delete successfully", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(context, "Cancel delete", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class ObjLoaiThuViewHolder extends RecyclerView.ViewHolder{
        private TextView nameloaithu;
        private ImageView edt_loaithu;
        private ImageView delete_loaithu;

        public ObjLoaiThuViewHolder(@NonNull View itemView) {
            super(itemView);
            nameloaithu = itemView.findViewById(R.id.tv_loaithu);
            edt_loaithu = itemView.findViewById(R.id.img_loaithu_edt);
            delete_loaithu = itemView.findViewById(R.id.img_loaithu_delete);
        }
    }
}
