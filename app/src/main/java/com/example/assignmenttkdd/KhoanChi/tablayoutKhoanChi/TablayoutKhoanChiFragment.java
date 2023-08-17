package com.example.assignmenttkdd.KhoanChi.tablayoutKhoanChi;

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
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.assignmenttkdd.KhoanChi.AdapterObjLoaiChi.AdapterObjKhoanChi;
import com.example.assignmenttkdd.KhoanChi.AdapterObjLoaiChi.AdapterObjLoaiChi;
import com.example.assignmenttkdd.KhoanChi.AdapterObjLoaiChi.AdapterSpinnerKhoanChi;
import com.example.assignmenttkdd.KhoanChi.DTO.ObjKhoanChi;
import com.example.assignmenttkdd.KhoanChi.DTO.ObjLoaiChi;
import com.example.assignmenttkdd.KhoanChi.DatabaseKhoanChi.KhoanChiDaTaB;
import com.example.assignmenttkdd.KhoanChi.DatabaseKhoanChi.LoaiChiDB;
import com.example.assignmenttkdd.KhoanChi.tablayoutLoaiChi.TablayoutLoaiChiFragment;
import com.example.assignmenttkdd.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TablayoutKhoanChiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TablayoutKhoanChiFragment extends Fragment {
    private FloatingActionButton actionButton;
    private RecyclerView recyclerView;
    private List<ObjKhoanChi> arrayList = new ArrayList<>();
    private List<ObjLoaiChi> loaiChiList = new ArrayList<>();
    private AdapterSpinnerKhoanChi adapterSpinnerKhoanChi;
    private AdapterObjKhoanChi adapterObjKhoanChi;
    private Spinner spinner;
    private ObjLoaiChi objLoaiChi = new ObjLoaiChi();
    Dialog dialogadd;

    public TablayoutKhoanChiFragment() {
        // Required empty public constructor
    }

    public static TablayoutKhoanChiFragment newInstance() {
        TablayoutKhoanChiFragment fragment = new TablayoutKhoanChiFragment();
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
        return inflater.inflate(R.layout.fragment_tablayout_khoan_chi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        actionButton = view.findViewById(R.id.action_btn_khoanchi);
        recyclerView = view.findViewById(R.id.recycler_khoanchi);
        LayoutInflater inflater = this.getLayoutInflater();
        View itemview = inflater.inflate(R.layout.dialog_addkhoanchi, null);
        EditText edt_kc = itemview.findViewById(R.id.edt_khoanchi);
        spinner = itemview.findViewById(R.id.spinner_khoanchi);


        Calendar lich = Calendar.getInstance();

        adapterObjKhoanChi = new AdapterObjKhoanChi(getActivity());
        getDataKC();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterObjKhoanChi);

        setSpinner();

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemview.getParent() != null) {
                    ((ViewGroup) itemview.getParent()).removeAllViews();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                setSpinner();
                if (loaiChiList.size()>=1){
                    objLoaiChi = loaiChiList.get(0);
                }
                edt_kc.setText("");
                builder.setView(itemview);
                builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(edt_kc.getText().toString().trim().isEmpty()){
                            Toast.makeText(getContext(), "Không được để trống số khoản chi", Toast.LENGTH_SHORT).show();
                        }else{
                            int ngay= lich.get(Calendar.DATE);
                            int thang = lich.get(Calendar.MONTH)+1;
                            int nam = lich.get(Calendar.YEAR);
                            String date = ngay+"/"+thang+"/"+nam;
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
                            ObjKhoanChi objKhoanChi = new ObjKhoanChi(Integer.parseInt(edt_kc.getText().toString()),objLoaiChi.getNameloaichi(),date);
                            KhoanChiDaTaB.getInstance(getContext()).objkcDAO().insertObjkc(objKhoanChi);
                            getDataKC();
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
    public void getDataKC(){
        arrayList = KhoanChiDaTaB.getInstance(getContext()).objkcDAO().getListObjKC();
        adapterObjKhoanChi.setDataKhoanChi(arrayList);
    }
    public void setSpinner(){
        loaiChiList = LoaiChiDB.getInstance(getContext()).objlcDAO().getListObjLC();
        adapterSpinnerKhoanChi = new AdapterSpinnerKhoanChi(getContext(),loaiChiList,R.layout.spinner_khoanchi);
        spinner.setAdapter(adapterSpinnerKhoanChi);
    }
}