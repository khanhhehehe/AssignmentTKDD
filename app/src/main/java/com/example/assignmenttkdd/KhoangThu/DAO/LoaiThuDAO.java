package com.example.assignmenttkdd.KhoangThu.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.assignmenttkdd.KhoanChi.DTO.ObjLoaiChi;
import com.example.assignmenttkdd.KhoangThu.DTO.ObjLoaiThu;

import java.util.List;

@Dao
public interface LoaiThuDAO {
    @Insert
    void insertObjlt(ObjLoaiThu objLoaiThu);

    @Query("SELECT * FROM tb_loaithu")
    List<ObjLoaiThu> getListObjLT();

    @Query("DELETE FROM tb_loaithu where id_lt= :objid")
    abstract int getObjidLT(int objid);

    @Update
    void updateObjLoaiThu(ObjLoaiThu objLoaiThu);
}
