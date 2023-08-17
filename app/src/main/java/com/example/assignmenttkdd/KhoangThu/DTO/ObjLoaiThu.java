package com.example.assignmenttkdd.KhoangThu.DTO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_loaithu")
public class ObjLoaiThu {
    @PrimaryKey(autoGenerate = true)
    private int id_lt;
    private String nameloaithu;
    private int edt_loaithu;
    private int delete_loaithu;

    public ObjLoaiThu() {
    }

    public ObjLoaiThu(String nameloaithu) {
        this.nameloaithu = nameloaithu;
    }

    public int getId_lt() {
        return id_lt;
    }

    public void setId_lt(int id_lt) {
        this.id_lt = id_lt;
    }

    public String getNameloaithu() {
        return nameloaithu;
    }

    public void setNameloaithu(String nameloaithu) {
        this.nameloaithu = nameloaithu;
    }

    public int getEdt_loaithu() {
        return edt_loaithu;
    }

    public void setEdt_loaithu(int edt_loaithu) {
        this.edt_loaithu = edt_loaithu;
    }

    public int getDelete_loaithu() {
        return delete_loaithu;
    }

    public void setDelete_loaithu(int delete_loaithu) {
        this.delete_loaithu = delete_loaithu;
    }
}
