package com.example.assignmenttkdd.KhoanChi.DTO;

import android.widget.ImageView;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_loaichi")
public class ObjLoaiChi {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nameloaichi;
    private int edt_loaichi;
    private int delete_loaichi;

    public ObjLoaiChi() {
    }

    public ObjLoaiChi(String nameloaichi) {
        this.nameloaichi = nameloaichi;
    }

    public String getNameloaichi() {
        return nameloaichi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameloaichi(String nameloaichi) {
        this.nameloaichi = nameloaichi;
    }

    public int getEdt_loaichi() {
        return edt_loaichi;
    }

    public void setEdt_loaichi(int edt_loaichi) {
        this.edt_loaichi = edt_loaichi;
    }

    public int getDelete_loaichi() {
        return delete_loaichi;
    }

    public void setDelete_loaichi(int delete_loaichi) {
        this.delete_loaichi = delete_loaichi;
    }
}
