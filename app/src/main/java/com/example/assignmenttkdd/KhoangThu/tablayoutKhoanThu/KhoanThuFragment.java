package com.example.assignmenttkdd.KhoangThu.tablayoutKhoanThu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assignmenttkdd.KhoanChi.AdapterObjLoaiChi.AdapterObjKhoanChi;
import com.example.assignmenttkdd.KhoanChi.AdapterObjLoaiChi.AdapterSpinnerKhoanChi;
import com.example.assignmenttkdd.KhoanChi.DTO.ObjKhoanChi;
import com.example.assignmenttkdd.KhoanChi.DTO.ObjLoaiChi;
import com.example.assignmenttkdd.KhoanChi.DatabaseKhoanChi.KhoanChiDaTaB;
import com.example.assignmenttkdd.KhoanChi.DatabaseKhoanChi.LoaiChiDB;
import com.example.assignmenttkdd.KhoangThu.Adapter.AdapterKhoanThu;
import com.example.assignmenttkdd.KhoangThu.Adapter.AdapterSpinnerKhoanThu;
import com.example.assignmenttkdd.KhoangThu.DTO.ObjKhoanThu;
import com.example.assignmenttkdd.KhoangThu.DTO.ObjLoaiThu;
import com.example.assignmenttkdd.KhoangThu.DataBase.KhoanThuDB;
import com.example.assignmenttkdd.KhoangThu.DataBase.LoaiThuDB;
import com.example.assignmenttkdd.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KhoanThuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KhoanThuFragment extends Fragment {
    private FloatingActionButton actionButton;
    private RecyclerView recyclerView;
    private List<ObjKhoanThu> arrayList = new ArrayList<>();
    private List<ObjLoaiThu> loaiThuList = new ArrayList<>();
    private AdapterSpinnerKhoanThu adapterSpinnerKhoanThu;
    private AdapterKhoanThu adapterKhoanThu;
    private Spinner spinner;
    private ObjLoaiThu objLoaiThu = new ObjLoaiThu();
    Dialog dialogadd;
    public KhoanThuFragment() {
        // Required empty public constructor
    }
    public static KhoanThuFragment newInstance() {
        KhoanThuFragment fragment = new KhoanThuFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_khoan_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        actionButton = view.findViewById(R.id.action_btn_khoanthu);
        recyclerView = view.findViewById(R.id.recycler_khoanthu);
        LayoutInflater inflater = this.getLayoutInflater();
        View itemview = inflater.inflate(R.layout.dialog_addkhoanthu, null);
        EditText edt_kt = itemview.findViewById(R.id.edt_khoanthu);
        spinner = itemview.findViewById(R.id.spinner_khoanthu);


        Calendar lich = Calendar.getInstance();

        adapterKhoanThu = new AdapterKhoanThu(getActivity());
        getDataKT();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterKhoanThu);

        setSpinner();
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemview.getParent() != null) {
                    ((ViewGroup) itemview.getParent()).removeAllViews();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                setSpinner();
                if (loaiThuList.size()>=1){
                    objLoaiThu = loaiThuList.get(0);
                }
                edt_kt.setText("");
                builder.setView(itemview);
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(edt_kt.getText().toString().trim().isEmpty()){
                            Toast.makeText(getContext(), "Không được để trống số khoản chi", Toast.LENGTH_SHORT).show();
                        }else{
                            int ngay= lich.get(Calendar.DATE);
                            int thang = lich.get(Calendar.MONTH)+1;
                            int nam = lich.get(Calendar.YEAR);
                            String date = ngay+"/"+thang+"/"+nam;
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
                            ObjKhoanThu objKhoanThu = new ObjKhoanThu(Integer.parseInt(edt_kt.getText().toString()),objLoaiThu.getNameloaithu(),date);
                            KhoanThuDB.getInstance(getContext()).khoanThuDAO().insertObjkt(objKhoanThu);
                            getDataKT();
                            Toast.makeText(getContext(), "Add successfully", Toast.LENGTH_SHORT).show();
                        }
                        dialogadd.dismiss();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogadd.dismiss();
                    }
                });
                dialogadd = builder.create();
                dialogadd = builder.show();
            }
        });
    }

    private void setSpinner() {
        loaiThuList = LoaiThuDB.getInstance(getContext()).loaiThuDAO().getListObjLT();
        adapterSpinnerKhoanThu = new AdapterSpinnerKhoanThu(getContext(),loaiThuList,R.layout.spinner_khoanthu);
        spinner.setAdapter(adapterSpinnerKhoanThu);
    }

    private void getDataKT() {
        arrayList = KhoanThuDB.getInstance(getContext()).khoanThuDAO().getListObjKT();
        adapterKhoanThu.setDataKhoanThu(arrayList);
    }
}