package com.example.assignmenttkdd.KhoangThu.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.assignmenttkdd.KhoangThu.DAO.KhoanThuDAO;
import com.example.assignmenttkdd.KhoangThu.DTO.ObjKhoanThu;

@Database(entities = {ObjKhoanThu.class},version = 1)
public abstract class KhoanThuDB extends RoomDatabase {
    private static final String DATABASE_KT = "db_objKT";
    private static KhoanThuDB instance;

    public static synchronized KhoanThuDB getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),KhoanThuDB.class,DATABASE_KT).allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract KhoanThuDAO khoanThuDAO();
}
