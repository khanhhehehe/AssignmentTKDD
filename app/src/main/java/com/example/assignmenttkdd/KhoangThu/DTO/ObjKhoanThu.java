package com.example.assignmenttkdd.KhoangThu.DTO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_khoanThu")
public class ObjKhoanThu {
    @PrimaryKey(autoGenerate = true)
    private int idkt;
    private int khoanthu;
    private String nameloaithu;
    private String date_kt;
    private int edt_khoanthu;
    private int delete_khoanthu;

    public ObjKhoanThu() {
    }
    public ObjKhoanThu(int khoanthu, String nameloaithu, String date_kt) {
        this.khoanthu = khoanthu;
        this.nameloaithu = nameloaithu;
        this.date_kt = date_kt;
    }

    public int getKhoanthu() {
        return khoanthu;
    }

    public void setKhoanthu(int khoanthu) {
        this.khoanthu = khoanthu;
    }

    public String getNameloaithu() {
        return nameloaithu;
    }

    public void setNameloaithu(String nameloaithu) {
        this.nameloaithu = nameloaithu;
    }

    public String getDate_kt() {
        return date_kt;
    }

    public void setDate_kt(String date_kt) {
        this.date_kt = date_kt;
    }

    public int getIdkt() {
        return idkt;
    }

    public void setIdkt(int idkt) {
        this.idkt = idkt;
    }

    public int getEdt_khoanthu() {
        return edt_khoanthu;
    }

    public void setEdt_khoanthu(int edt_khoanthu) {
        this.edt_khoanthu = edt_khoanthu;
    }

    public int getDelete_khoanthu() {
        return delete_khoanthu;
    }

    public void setDelete_khoanthu(int delete_khoanthu) {
        this.delete_khoanthu = delete_khoanthu;
    }
}
