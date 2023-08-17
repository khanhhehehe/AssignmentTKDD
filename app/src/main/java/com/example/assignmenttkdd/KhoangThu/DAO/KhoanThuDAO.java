package com.example.assignmenttkdd.KhoangThu.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.assignmenttkdd.KhoangThu.DTO.ObjKhoanThu;

import java.util.List;

@Dao
public interface KhoanThuDAO {
    @Insert
    void insertObjkt(ObjKhoanThu objKhoanThu);

    @Query("SELECT * FROM tb_khoanThu")
    List<ObjKhoanThu> getListObjKT();

    @Query("DELETE FROM tb_khoanThu where idkt= :objidKt")
    abstract int getObjidKT(int objidKt);

    @Update
    void updateObjKhoanThu(ObjKhoanThu objKhoanThu);
}
