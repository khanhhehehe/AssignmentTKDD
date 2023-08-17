package com.example.assignmenttkdd.KhoanChi.KhoanChiDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.assignmenttkdd.KhoanChi.DTO.ObjLoaiChi;
import java.util.List;
@Dao
public interface ObjlcDAO {
    @Insert
    void insertObjlc(ObjLoaiChi objLoaiChi);

    @Query("SELECT * FROM tb_loaichi")
    List<ObjLoaiChi> getListObjLC();

    @Query("DELETE FROM tb_loaichi where id= :objid")
    abstract int getObjidLC(int objid);

    @Update
    void updateObjLoaiChi(ObjLoaiChi objloaiChi);
}
