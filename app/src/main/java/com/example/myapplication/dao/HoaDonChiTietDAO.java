package com.example.myapplication.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.myapplication.DatabaseHelper;
import com.example.myapplication.HoaDon;
import com.example.myapplication.HoaDonChiTiet;
import com.example.myapplication.NguoiDung;
import com.example.myapplication.R;
import com.example.myapplication.Sach;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "HoaDonChiTiet";
    public static final String SQL_HOA_DON_CHI_TIET ="CREATE TABLE HoaDonChiTiet(maHDCT INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "maHoaDon text NOT NULL, maSach text NOT NULL, soLuong INTEGER);";
    public static final String TAG = "HoaDonChiTiet";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public HoaDonChiTietDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //insert
    public int inserHoaDonChiTiet(HoaDonChiTiet hd){
        ContentValues values = new ContentValues();
        values.put("mahoadon",hd.getHoaDon().getMaHoaDon());
        values.put("maSach",hd.getSach().getMaSach());
        values.put("soLuong",hd.getSoLuongMua());
        try {
            if(db.insert(TABLE_NAME,null,values)== -1){
                return -1;
            }
        }catch (Exception ex){
            Log.e(TAG,ex.toString());
        }
        return 1;
    }
    //getAll
    public List<HoaDonChiTiet> getAllHoaDonChiTiet(){
        List<HoaDonChiTiet> dsHoaDonChiTiet = new ArrayList<>();
        String sSQL = "SELECT maHDCT, HoaDon.maHoaDon,HoaDon.ngayMua, " + "Sach.maSach, Sach.maTheLoai, Sach.tenSach, Sach.tacGia, Sach.NXB, Sach.giaBia, " +
        "Sach.soLuong,HoaDonChiTiet.soLuong FROM HoaDonChiTiet INNER JOIN HoaDon " +
        "on HoaDonChiTiet.maHoaDon = HoaDon.maHoaDon INNER JOIN Sach on Sach.maSach = HoaDonChiTiet.maSach";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        try {
            while (c.isAfterLast()==false){
                HoaDonChiTiet ee = new HoaDonChiTiet();
                ee.setMaHDCT(c.getInt(0));
                ee.setHoaDon(new HoaDon(c.getString(1),sdf.parse(c.getString(2))));
                ee.setSach(new Sach(c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getInt(8),c.getInt(9)));
                ee.setSoLuongMua(c.getInt(10));
                dsHoaDonChiTiet.add(ee);
                Log.d("//=====",ee.toString());
                c.moveToNext();
            }
            c.close();
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return dsHoaDonChiTiet;
    }
    //getAll
    public List<HoaDonChiTiet> getAllHoaDonChiTietByID(String maHoaDon){
        List<HoaDonChiTiet> dsHoaDonChiTiet = new ArrayList<>();
        String sSQL = "SELECT maHDCT, HoaDon.maHoaDon,HoaDon.ngayMua, " +
                "Sach.maSach, Sach.maTheLoai, Sach.tenSach, Sach.tacGia, Sach.NXB, Sach.giaBia, " +
        "Sach.soLuong,HoaDonChiTiet.soLuong FROM HoaDonChiTiet INNER JOIN HoaDon " +
        "on HoaDonChiTiet.maHoaDon = HoaDon.maHoaDon INNER JOIN Sach on Sach.maSach = HoaDonChiTiet.maSach where HoaDonChiTiet.maHoaDon='"+maHoaDon+"'";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        try {
            while (c.isAfterLast()==false){
                HoaDonChiTiet ee = new HoaDonChiTiet();
                ee.setMaHDCT(c.getInt(0));
                ee.setHoaDon(new HoaDon(c.getString(1),sdf.parse(c.getString(2))));
                ee.setSach(new
                        Sach(c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getInt(8),c.getInt(9)));
                ee.setSoLuongMua(c.getInt(10));
                dsHoaDonChiTiet.add(ee);
                Log.d("//=====",ee.toString());
                c.moveToNext();
            }
            c.close();
        } catch (Exception e) {
            Log.d(TAG, e.toString());
        }
        return dsHoaDonChiTiet;
    }
    //update
    public int updateHoaDonChiTiet(HoaDonChiTiet hd){
        ContentValues values = new ContentValues();
        values.put("maHDCT",hd.getMaHDCT());
        values.put("mahoadon",hd.getHoaDon().getMaHoaDon());
        values.put("maSach",hd.getSach().getMaSach());
        values.put("soLuong",hd.getSoLuongMua());
        int result = db.update(TABLE_NAME,values,"maHDCT=?", new
                String[]{String.valueOf(hd.getMaHDCT())});
        if (result == 0){
            return -1;
        }
        return 1;}
    //delete
    public int deleteHoaDonChiTietByID(String maHDCT){
        int result = db.delete(TABLE_NAME,"maHDCT=?",new String[]{maHDCT});
        if (result == 0)
            return -1;
        return 1;
    }
    //check
    public boolean checkHoaDon(String maHoaDon){
        //SELECT
        String[] columns = {"maHoaDon"};
        //WHERE clause
        String selection = "maHoaDon=?";
        //WHERE clause arguments
        String[] selectionArgs = {maHoaDon};
        Cursor c = null;
        try{
            c = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null,
                    null);
            c.moveToFirst();
            int i = c.getCount();
            c.close();
            if(i <= 0){
                return false;
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public double getDoanhThuTheoNgay(){
        double doanhThu = 0;
        String sSQL ="SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien' " +
        "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
        "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach where HoaDon.ngayMua = date('now') GROUP BY HoaDonChiTiet.maSach)tmp";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
    public double getDoanhThuTheoThang(){
        double doanhThu = 0;
        String sSQL ="SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien' " +
        "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
        "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach where strftime('%m',HoaDon.ngayMua) = strftime('%m','now') GROUP BY HoaDonChiTiet.maSach)tmp";Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
    public double getDoanhThuTheoNam(){
        double doanhThu = 0;
        String sSQL ="SELECT SUM(tongtien) from (SELECT SUM(Sach.giaBia * HoaDonChiTiet.soLuong) as 'tongtien' " +
        "FROM HoaDon INNER JOIN HoaDonChiTiet on HoaDon.maHoaDon = HoaDonChiTiet.maHoaDon " +
        "INNER JOIN Sach on HoaDonChiTiet.maSach = Sach.maSach where strftime('%Y',HoaDon.ngayMua) = strftime('%Y','now') GROUP BY HoaDonChiTiet.maSach)tmp";
        Cursor c = db.rawQuery(sSQL, null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            doanhThu = c.getDouble(0);
            c.moveToNext();
        }
        c.close();
        return doanhThu;
    }
}

