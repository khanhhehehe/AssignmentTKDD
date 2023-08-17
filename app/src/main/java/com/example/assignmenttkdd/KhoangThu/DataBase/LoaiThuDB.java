package com.example.assignmenttkdd.KhoangThu.DataBase;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.assignmenttkdd.KhoangThu.DAO.LoaiThuDAO;
import com.example.assignmenttkdd.KhoangThu.DTO.ObjLoaiThu;

@Database(entities = {ObjLoaiThu.class},version = 1)
public abstract class LoaiThuDB extends RoomDatabase {
    private static final String DATABASE_LT = "db_objlt";
    private static LoaiThuDB instance;

    public static synchronized LoaiThuDB getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),LoaiThuDB.class,DATABASE_LT).allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract LoaiThuDAO loaiThuDAO();
}
