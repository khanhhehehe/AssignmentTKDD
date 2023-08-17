package com.example.assignmenttkdd.KhoangThu.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmenttkdd.KhoanChi.AdapterObjLoaiChi.AdapterSpinnerKhoanChi;
import com.example.assignmenttkdd.KhoanChi.DTO.ObjKhoanChi;
import com.example.assignmenttkdd.KhoanChi.DatabaseKhoanChi.KhoanChiDaTaB;
import com.example.assignmenttkdd.KhoanChi.DatabaseKhoanChi.LoaiChiDB;
import com.example.assignmenttkdd.KhoangThu.DTO.ObjKhoanThu;
import com.example.assignmenttkdd.KhoangThu.DTO.ObjLoaiThu;
import com.example.assignmenttkdd.KhoangThu.DataBase.KhoanThuDB;
import com.example.assignmenttkdd.KhoangThu.DataBase.LoaiThuDB;
import com.example.assignmenttkdd.R;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdapterKhoanThu extends RecyclerView.Adapter<AdapterKhoanThu.ObjKhoanThuViewHolder>{
    private Context context;
    private List<ObjKhoanThu> arrayList;
    private ObjKhoanThu objKhoanThunew;
    private List<ObjLoaiThu> loaiThuList;
    private AdapterSpinnerKhoanThu adapterSpinnerKhoanThu;
    private Spinner spinner;
    private ObjLoaiThu objLoaiThu = new ObjLoaiThu();

    public AdapterKhoanThu(Context context) {
        this.context = context;
    }

    public void setDataKhoanThu(List<ObjKhoanThu> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ObjKhoanThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoanthu, parent, false);
        return new ObjKhoanThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObjKhoanThuViewHolder holder, int position) {
        ObjKhoanThu objKhoanThu = arrayList.get(position);
        if (objKhoanThu == null) {
            return;
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String money = formatter.format(arrayList.get(position).getKhoanthu());
        holder.khoanthu.setText(money+"VND");
        holder.name_loaithu.setText(arrayList.get(position).getNameloaithu());
        holder.date.setText(arrayList.get(position).getDate_kt());

        holder.edt_khoanthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_khoanthuupdate);
                loaiThuList = LoaiThuDB.getInstance(v.getContext()).loaiThuDAO().getListObjLT();

                Button btn_huy = dialog.findViewById(R.id.btn_huy);
                Button btn_update = dialog.findViewById(R.id.btn_update);
                EditText khoanthu_new = dialog.findViewById(R.id.edt_khoanthu_new);
                spinner = dialog.findViewById(R.id.spinner_khoanthu_new);
                Calendar lich = Calendar.getInstance();

                khoanthu_new.setText("");
                adapterSpinnerKhoanThu = new AdapterSpinnerKhoanThu(v.getContext(), loaiThuList,R.layout.spinner_khoanthu);
                spinner.setAdapter(adapterSpinnerKhoanThu);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        objLoaiThu = loaiThuList.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        objLoaiThu = loaiThuList.get(0);
                    }
                });
                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(khoanthu_new.getText().toString().trim().isEmpty()){
                            Toast.makeText(v.getContext(), "Không được để trống số khoản thu", Toast.LENGTH_SHORT).show();
                        }else{
                            int ngay= lich.get(Calendar.DATE);
                            int thang = lich.get(Calendar.MONTH)+1;
                            int nam = lich.get(Calendar.YEAR);
                            String date = ngay+"/"+thang+"/"+nam;


                            objKhoanThunew = objKhoanThu;
                            objKhoanThunew.setKhoanthu(Integer.parseInt(khoanthu_new.getText().toString()));
                            objKhoanThunew.setNameloaithu(objLoaiThu.getNameloaithu());
                            objKhoanThunew.setDate_kt(date);
                            KhoanThuDB.getInstance(v.getContext()).khoanThuDAO().updateObjKhoanThu(objKhoanThunew);
                            arrayList = KhoanThuDB.getInstance(v.getContext()).khoanThuDAO().getListObjKT();
                            setDataKhoanThu(arrayList);
                            Toast.makeText(v.getContext(), "Update successfully", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                btn_huy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        holder.delete_khoanthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Xóa");
                builder.setMessage("Bạn có chắc muốn xóa không?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        KhoanThuDB.getInstance(v.getContext()).khoanThuDAO().getObjidKT(objKhoanThu.getIdkt());
                        arrayList = KhoanThuDB.getInstance(v.getContext()).khoanThuDAO().getListObjKT();
                        setDataKhoanThu(arrayList);
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

    public class ObjKhoanThuViewHolder extends RecyclerView.ViewHolder{
        private TextView khoanthu;
        private TextView name_loaithu;
        private TextView date;
        private ImageView edt_khoanthu;
        private ImageView delete_khoanthu;
        public ObjKhoanThuViewHolder(@NonNull View itemView) {
            super(itemView);
            khoanthu = itemView.findViewById(R.id.tv_khoanthu);
            name_loaithu = itemView.findViewById(R.id.tv_set_loaithu);
            date = itemView.findViewById(R.id.tv_date_kt);
            edt_khoanthu = itemView.findViewById(R.id.img_khoanthu_edt);
            delete_khoanthu = itemView.findViewById(R.id.img_khoanthu_delete);
        }
    }
}
