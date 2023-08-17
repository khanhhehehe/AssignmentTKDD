package com.example.assignmenttkdd.KhoanChi.DTO;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_khoanChi")
public class ObjKhoanChi {
    @PrimaryKey(autoGenerate = true)
    private int idkc;
    private int khoanchi;
    private String nameloaichi;
    private String date_kc;
    private int edt_khoanchi;
    private int delete_khoanchi;

    public ObjKhoanChi() {
    }

    public ObjKhoanChi(int khoanchi, String nameloaichi, String date_kc) {
        this.khoanchi = khoanchi;
        this.nameloaichi = nameloaichi;
        this.date_kc = date_kc;
    }

    public int getIdkc() {
        return idkc;
    }

    public void setIdkc(int idkc) {
        this.idkc = idkc;
    }

    public int getKhoanchi() {
        return khoanchi;
    }

    public void setKhoanchi(int khoanchi) {
        this.khoanchi = khoanchi;
    }

    public String getNameloaichi() {
        return nameloaichi;
    }

    public void setNameloaichi(String nameloaichi) {
        this.nameloaichi = nameloaichi;
    }

    public String getDate_kc() {
        return date_kc;
    }

    public void setDate_kc(String date_kc) {
        this.date_kc = date_kc;
    }

    public int getEdt_khoanchi() {
        return edt_khoanchi;
    }

    public void setEdt_khoanchi(int edt_khoanchi) {
        this.edt_khoanchi = edt_khoanchi;
    }

    public int getDelete_khoanchi() {
        return delete_khoanchi;
    }

    public void setDelete_khoanchi(int delete_khoanchi) {
        this.delete_khoanchi = delete_khoanchi;
    }
}
