package com.example.assignmenttkdd.KhoanChi.AdapterObjLoaiChi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

import com.example.assignmenttkdd.KhoanChi.DTO.ObjKhoanChi;
import com.example.assignmenttkdd.KhoanChi.DTO.ObjLoaiChi;
import com.example.assignmenttkdd.KhoanChi.DatabaseKhoanChi.KhoanChiDaTaB;
import com.example.assignmenttkdd.KhoanChi.DatabaseKhoanChi.LoaiChiDB;
import com.example.assignmenttkdd.R;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdapterObjKhoanChi extends RecyclerView.Adapter<AdapterObjKhoanChi.ObjKhoanChiViewHolder>{
    private Context context;
    private List<ObjKhoanChi> arrayList;
    private ObjKhoanChi objKhoanChinew;
    private List<ObjLoaiChi> loaiChiList;
    private AdapterSpinnerKhoanChi adapterSpinnerKhoanChi;
    private Spinner spinner;
    private ObjLoaiChi objLoaiChi = new ObjLoaiChi();

    public AdapterObjKhoanChi(Context context) {
        this.context = context;
    }

        public void setDataKhoanChi(List<ObjKhoanChi> arrayList) {
            this.arrayList = arrayList;
            notifyDataSetChanged();
        }
    @NonNull
    @Override
    public ObjKhoanChiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoanchi, parent, false);
        return new ObjKhoanChiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObjKhoanChiViewHolder holder, int position) {
        ObjKhoanChi objKhoanChi = arrayList.get(position);
        if (objKhoanChi == null) {
            return;
        }
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        String money = formatter.format(arrayList.get(position).getKhoanchi());
        holder.khoanchi.setText(money+"VND");
        holder.name_loaichi.setText(arrayList.get(position).getNameloaichi());
        holder.date.setText(arrayList.get(position).getDate_kc());


        holder.edt_khoanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext(), androidx.appcompat.R.style.Theme_AppCompat_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_khoanchiupdate);
                loaiChiList = LoaiChiDB.getInstance(v.getContext()).objlcDAO().getListObjLC();

                Button btn_huy = dialog.findViewById(R.id.btn_huy);
                Button btn_update = dialog.findViewById(R.id.btn_update);
                EditText khoanchi_new = dialog.findViewById(R.id.edt_khoanchi_new);
                spinner = dialog.findViewById(R.id.spinner_khoanchi_new);
                Calendar lich = Calendar.getInstance();

                khoanchi_new.setText("");
                adapterSpinnerKhoanChi = new AdapterSpinnerKhoanChi(v.getContext(), loaiChiList,R.layout.spinner_khoanchi);
                spinner.setAdapter(adapterSpinnerKhoanChi);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        objLoaiChi = loaiChiList.get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        objLoaiChi = loaiChiList.get(0);
                    }
                });
                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(khoanchi_new.getText().toString().trim().isEmpty()){
                            Toast.makeText(v.getContext(), "Không được để trống số khoản chi", Toast.LENGTH_SHORT).show();
                        }else{
                            int ngay= lich.get(Calendar.DATE);
                            int thang = lich.get(Calendar.MONTH)+1;
                            int nam = lich.get(Calendar.YEAR);
                            String date = ngay+"/"+thang+"/"+nam;


                            objKhoanChinew = objKhoanChi;
                            objKhoanChinew.setKhoanchi(Integer.parseInt(khoanchi_new.getText().toString()));
                            objKhoanChinew.setNameloaichi(objLoaiChi.getNameloaichi());
                            objKhoanChinew.setDate_kc(date);
                            KhoanChiDaTaB.getInstance(v.getContext()).objkcDAO().updateObjKhoanChi(objKhoanChinew);
                            arrayList = KhoanChiDaTaB.getInstance(v.getContext()).objkcDAO().getListObjKC();
                            setDataKhoanChi(arrayList);
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
        holder.delete_khoanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Xóa");
                builder.setMessage("Bạn có chắc muốn xóa không?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        KhoanChiDaTaB.getInstance(v.getContext()).objkcDAO().getObjidKC(objKhoanChi.getIdkc());
                        arrayList = KhoanChiDaTaB.getInstance(v.getContext()).objkcDAO().getListObjKC();
                        setDataKhoanChi(arrayList);
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

    public class ObjKhoanChiViewHolder extends RecyclerView.ViewHolder{
        private TextView khoanchi;
        private TextView name_loaichi;
        private TextView date;
        private ImageView edt_khoanchi;
        private ImageView delete_khoanchi;
        public ObjKhoanChiViewHolder(@NonNull View itemView) {
            super(itemView);
            khoanchi = itemView.findViewById(R.id.tv_khoanchi);
            name_loaichi = itemView.findViewById(R.id.tv_set_loaichi);
            date = itemView.findViewById(R.id.tv_date);
            edt_khoanchi = itemView.findViewById(R.id.img_khoanchi_edt);
            delete_khoanchi = itemView.findViewById(R.id.img_khoanchi_delete);
        }
    }
}
