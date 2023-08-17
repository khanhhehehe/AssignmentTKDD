package com.example.assignmenttkdd.KhoanChi.DatabaseKhoanChi;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.assignmenttkdd.KhoanChi.DTO.ObjKhoanChi;
import com.example.assignmenttkdd.KhoanChi.KhoanChiDAO.ObjkcDAO;
@Database(entities = {ObjKhoanChi.class},version = 1)
public abstract class KhoanChiDaTaB extends RoomDatabase {
    private static final String DATABASE_KC = "db_objKC";
    private static KhoanChiDaTaB instance;

    public static synchronized KhoanChiDaTaB getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),KhoanChiDaTaB.class,DATABASE_KC).allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract ObjkcDAO objkcDAO();
}