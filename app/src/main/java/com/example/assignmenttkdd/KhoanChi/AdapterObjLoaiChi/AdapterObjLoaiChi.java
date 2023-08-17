package com.example.assignmenttkdd.KhoanChi.AdapterObjLoaiChi;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmenttkdd.KhoanChi.DTO.ObjLoaiChi;
import com.example.assignmenttkdd.KhoanChi.DatabaseKhoanChi.LoaiChiDB;
import com.example.assignmenttkdd.R;

import java.util.List;

public class AdapterObjLoaiChi extends RecyclerView.Adapter<AdapterObjLoaiChi.ObjLoaiChiViewHolder>{
    private Context context;
    private List<ObjLoaiChi> arrayList;
    private ObjLoaiChi objLoaiChinew;

    public AdapterObjLoaiChi(Context context) {
        this.context = context;
    }

    public void setDataLoaiChi(List<ObjLoaiChi> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ObjLoaiChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_product, parent, false);
        return new ObjLoaiChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObjLoaiChiViewHolder holder, int position) {
        ObjLoaiChi objLoaiChi = arrayList.get(position);
        if (objLoaiChi == null) {
            return;
        }
        holder.nameloaichi.setText(arrayList.get(position).getNameloaichi());
        holder.edt_loaichi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setView(R.layout.layout_update);
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog dlg = (AlertDialog) dialog;
                        EditText name_new = dlg.findViewById(R.id.edt_product_new);

                        if (name_new.getText().toString().trim().isEmpty()){
                            Toast.makeText(context, "Tên không được để trống", Toast.LENGTH_SHORT).show();
                        }else if(name_new.getText().toString().trim().equals(objLoaiChi.getNameloaichi())) {
                            Toast.makeText(context, "Tên không có gì thay đổi", Toast.LENGTH_SHORT).show();
                        }else {
                            objLoaiChinew = objLoaiChi;
                            objLoaiChinew.setNameloaichi(name_new.getText().toString());
                            LoaiChiDB.getInstance(v.getContext()).objlcDAO().updateObjLoaiChi(objLoaiChinew);
                            arrayList = LoaiChiDB.getInstance(v.getContext()).objlcDAO().getListObjLC();
                            setDataLoaiChi(arrayList);
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
        holder.delete_loaichi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Xóa");
                builder.setMessage("Bạn có chắc muốn xóa không?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoaiChiDB.getInstance(v.getContext()).objlcDAO().getObjidLC(objLoaiChi.getId());
                        arrayList = LoaiChiDB.getInstance(v.getContext()).objlcDAO().getListObjLC();
                        setDataLoaiChi(arrayList);
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

    public class ObjLoaiChiViewHolder extends RecyclerView.ViewHolder {
        private TextView nameloaichi;
        private ImageView edt_loaichi;
        private ImageView delete_loaichi;

        public ObjLoaiChiViewHolder(@NonNull View itemView) {
            super(itemView);
            nameloaichi = itemView.findViewById(R.id.tv_loaichi);
            edt_loaichi = itemView.findViewById(R.id.img_loaichi_edt);
            delete_loaichi = itemView.findViewById(R.id.img_loaichi_delete);

        }
    }
}
