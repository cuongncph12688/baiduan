package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.DatabaseHelper;
import com.example.myapplication.TheLoai;

import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "TheLoai";
    public static final String SQL_THE_LOAI ="CREATE TABLE TheLoai (matheloai textprimary key, tentheloai text, mota text, vitri int);";
    public static final String TAG = "TheLoaiDAO";
    public TheLoaiDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //insert
    public int inserTheLoai(TheLoai theLoai){
        ContentValues values = new ContentValues();
        values.put("matheloai",theLoai.getMaTheLoai());
        values.put("tentheloai",theLoai.getTenTheLoai());
        values.put("mota",theLoai.getMoTa());
        values.put("vitri",theLoai.getViTri());
        try {
            if(db.insert(TABLE_NAME,null,values)== -1){
                return -1;
            }
        }catch (Exception ex){
            Log.e(TAG,ex.toString());
        }
        return 1;
    }
    //getAllTheLoai
    public List<TheLoai> getAllTheLoai(){
        List<TheLoai> dsTheLoai = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            TheLoai ee = new TheLoai();
            ee.setMaTheLoai(c.getString(0));
            ee.setTenTheLoai(c.getString(1));
            ee.setMoTa(c.getString(2));
            ee.setViTri(c.getInt(3));
            dsTheLoai.add(ee);
            Log.d("//=====",ee.toString());
            c.moveToNext();
            Log.e("2","2");
        }
        c.close();
        return dsTheLoai;
    }
    //update
    public int updateTheLoai(TheLoai theLoai){
        ContentValues values = new ContentValues();
        values.put("matheloai",theLoai.getMaTheLoai());
        values.put("tentheloai",theLoai.getTenTheLoai());
        values.put("mota",theLoai.getMoTa());
        values.put("vitri",theLoai.getViTri());
        int result = db.update(TABLE_NAME,values,"matheloai=?", new
                String[]{theLoai.getMaTheLoai()});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    //delete
    public int deleteTheLoaiByID(String matheloai){
        int result = db.delete(TABLE_NAME,"matheloai=?",new String[]{matheloai});
        if (result == 0)
            return -1;
        return 1;
    }
    public int updateInfoTheLoai(String matheloai, String s, String s1, String s2, String s3) {
        ContentValues values = new ContentValues();
        values.put("matheloai", s);
        values.put("tentheloai", s1);
        values.put("mota", s2);
        values.put("vitri", s3);
        int result = db.update(TABLE_NAME, values, "matheloai=?", new
                String[]{matheloai});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

}
