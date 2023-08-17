package com.example.assignmenttkdd.KhoanChi.tablayoutLoaiChi;

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

import com.example.assignmenttkdd.KhoanChi.AdapterObjLoaiChi.AdapterObjLoaiChi;
import com.example.assignmenttkdd.KhoanChi.DTO.ObjLoaiChi;
import com.example.assignmenttkdd.KhoanChi.DatabaseKhoanChi.LoaiChiDB;
import com.example.assignmenttkdd.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TablayoutLoaiChiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TablayoutLoaiChiFragment extends Fragment {
    private FloatingActionButton actionButton;
    private RecyclerView recyclerView;
    private List<ObjLoaiChi> arrayList = new ArrayList<>();
    private AdapterObjLoaiChi adapterObjLoaiChi;
    Dialog dialogadd;

    public TablayoutLoaiChiFragment() {
        // Required empty public constructor
    }
    public static TablayoutLoaiChiFragment newInstance() {
        TablayoutLoaiChiFragment fragment = new TablayoutLoaiChiFragment();
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
        return inflater.inflate(R.layout.fragment_tablayout_loai_chi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        actionButton = view.findViewById(R.id.action_btn_loaichi);
        recyclerView = view.findViewById(R.id.recycler_loaichi);
        LayoutInflater inflater = this.getLayoutInflater();
        View itemview = inflater.inflate(R.layout.dialog_add, null);
        EditText edt_name = itemview.findViewById(R.id.edt_product);

        adapterObjLoaiChi = new AdapterObjLoaiChi(getActivity());
        getData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterObjLoaiChi);


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
                            ObjLoaiChi objLoaiChi = new ObjLoaiChi(edt_name.getText().toString());
                            LoaiChiDB.getInstance(getContext()).objlcDAO().insertObjlc(objLoaiChi);
                            getData();
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
    public void getData() {
        arrayList = LoaiChiDB.getInstance(getContext()).objlcDAO().getListObjLC();
        adapterObjLoaiChi.setDataLoaiChi(arrayList);
    }
}