package com.example.assignmenttkdd.ThongKe;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignmenttkdd.KhoanChi.DTO.ObjKhoanChi;
import com.example.assignmenttkdd.KhoanChi.DatabaseKhoanChi.KhoanChiDaTaB;
import com.example.assignmenttkdd.KhoangThu.DTO.ObjKhoanThu;
import com.example.assignmenttkdd.KhoangThu.DataBase.KhoanThuDB;
import com.example.assignmenttkdd.R;
import com.example.assignmenttkdd.ThongKe.Adapter.AdapterKhoanChiTK;
import com.example.assignmenttkdd.ThongKe.Adapter.AdapterKhoanThuTK;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThongKeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThongKeFragment extends Fragment {
    private TextView sum_kc,sum_kt;
    private TextView tv_today;
    private Button btn_thongke;
    private RecyclerView recyclerView1,recyclerView2;
    private List<ObjKhoanThu> arrayList = new ArrayList<>();
    private AdapterKhoanThuTK adapterKhoanThuTK;
    private List<ObjKhoanChi> khoanChiList = new ArrayList<>();
    private AdapterKhoanChiTK adapterKhoanChiTK;
    private int sumKT=0,sumKC=0;
    public ThongKeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ThongKeFragment newInstance() {
        ThongKeFragment fragment = new ThongKeFragment();
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
        return inflater.inflate(R.layout.fragment_thong_ke, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        Calendar date = Calendar.getInstance();

        adapterKhoanThuTK = new AdapterKhoanThuTK(getActivity());
        getDataKT();
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setAdapter(adapterKhoanThuTK);

        adapterKhoanChiTK = new AdapterKhoanChiTK(getActivity());
        getDataKC();
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(adapterKhoanChiTK);


        btn_thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ObjKhoanThu t : arrayList){
                    sumKT+=t.getKhoanthu();
                }
                for (ObjKhoanChi c : khoanChiList){
                    sumKC+=c.getKhoanchi();
                }
                DecimalFormat formatterKT = new DecimalFormat("###,###,###");
                String moneyKT = formatterKT.format(sumKT);
                DecimalFormat formatterKC = new DecimalFormat("###,###,###");
                String moneyKC = formatterKC.format(sumKC);
                sum_kt.setText(moneyKT+"VND");
                sum_kc.setText(moneyKC+"VND");

                int month_today = date.get(Calendar.MONTH)+1;
                tv_today.setText("Ngày: "+date.get(Calendar.DATE)+"/"+month_today+"/"+date.get(Calendar.YEAR));
            }
        });
    }

    private void getDataKT() {
        arrayList = KhoanThuDB.getInstance(getContext()).khoanThuDAO().getListObjKT();
        adapterKhoanThuTK.setData(arrayList);
    }
    private void getDataKC() {
        khoanChiList = KhoanChiDaTaB.getInstance(getContext()).objkcDAO().getListObjKC();
        adapterKhoanChiTK.setDataKCTK(khoanChiList);
    }
}