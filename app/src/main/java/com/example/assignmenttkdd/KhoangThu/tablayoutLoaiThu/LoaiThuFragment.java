package com.example.assignmenttkdd.KhoangThu.tablayoutLoaiThu;

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
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignmenttkdd.KhoanChi.DTO.ObjLoaiChi;
import com.example.assignmenttkdd.KhoanChi.DatabaseKhoanChi.LoaiChiDB;
import com.example.assignmenttkdd.KhoangThu.Adapter.AdapterLoaiThu;
import com.example.assignmenttkdd.KhoangThu.DTO.ObjLoaiThu;
import com.example.assignmenttkdd.KhoangThu.DataBase.LoaiThuDB;
import com.example.assignmenttkdd.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoaiThuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoaiThuFragment extends Fragment {
    private FloatingActionButton actionButton;
    private RecyclerView recyclerView;
    private List<ObjLoaiThu> arrayList = new ArrayList<>();
    private AdapterLoaiThu adapterLoaiThu;
    Dialog dialogadd;
    public LoaiThuFragment() {
        // Required empty public constructor
    }

    public static LoaiThuFragment newInstance() {
        LoaiThuFragment fragment = new LoaiThuFragment();
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
        return inflater.inflate(R.layout.fragment_loai_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        actionButton = view.findViewById(R.id.action_btn_loaithu);
        recyclerView = view.findViewById(R.id.recycler_loaithu);
        LayoutInflater inflater = this.getLayoutInflater();
        View itemview = inflater.inflate(R.layout.dialog_add_loaithu, null);
        EditText edt_name = itemview.findViewById(R.id.edt_product_lt);

        adapterLoaiThu = new AdapterLoaiThu(getActivity());
        getDataLT();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterLoaiThu);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemview.getParent() != null) {
                    ((ViewGroup) itemview.getParent()).removeAllViews();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(itemview);
                edt_name.setText("");
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(edt_name.getText().toString().trim().isEmpty()){
                            Toast.makeText(getContext(), "Tên không được để trống", Toast.LENGTH_SHORT).show();
                        }else{
                            ObjLoaiThu objLoaiThu = new ObjLoaiThu(edt_name.getText().toString());
                            LoaiThuDB.getInstance(getContext()).loaiThuDAO().insertObjlt(objLoaiThu);
                            getDataLT();
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

    private void getDataLT() {
        arrayList = LoaiThuDB.getInstance(getContext()).loaiThuDAO().getListObjLT();
        adapterLoaiThu.setDataLoaiThu(arrayList);
    }
}