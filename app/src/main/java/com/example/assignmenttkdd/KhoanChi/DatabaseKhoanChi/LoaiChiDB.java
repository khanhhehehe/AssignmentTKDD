package com.example.assignmenttkdd.KhoanChi.DatabaseKhoanChi;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.assignmenttkdd.KhoanChi.DTO.ObjLoaiChi;
import com.example.assignmenttkdd.KhoanChi.KhoanChiDAO.ObjlcDAO;

@Database(entities = {ObjLoaiChi.class},version = 1)
public abstract class LoaiChiDB extends RoomDatabase {
    private static final String DATABASE_LC = "db_objlc";
    private static LoaiChiDB instance;

    public static synchronized LoaiChiDB getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),LoaiChiDB.class,DATABASE_LC).allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract ObjlcDAO objlcDAO();
}
