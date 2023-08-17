package com.example.assignmenttkdd.KhoanChi.KhoanChiDAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.assignmenttkdd.KhoanChi.DTO.ObjKhoanChi;
import java.util.List;
@Dao
public interface ObjkcDAO {
    @Insert
    void insertObjkc(ObjKhoanChi objKhoanChi);

    @Query("SELECT * FROM tb_khoanChi")
    List<ObjKhoanChi> getListObjKC();

    @Query("DELETE FROM tb_khoanChi where idkc= :objidKc")
    abstract int getObjidKC(int objidKc);

    @Update
    void updateObjKhoanChi(ObjKhoanChi objKhoanChi);
}
