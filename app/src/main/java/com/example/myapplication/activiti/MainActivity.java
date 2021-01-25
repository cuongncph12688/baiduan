package com.example.myapplication.activiti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.atvivit2.ListBookActivity;
import com.example.myapplication.atvivit2.ListHoaDonActivity;
import com.example.myapplication.atvivit2.LuotSachBanChayActivity;
import com.example.myapplication.atvivit2.ThongKeDoanhThuActivity;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ViewThongKeActivity(View view) {
        intent=new Intent(this, ThongKeDoanhThuActivity.class);
        startActivity(intent);
    }

    public void ViewTopSach(View view) {
        intent=new Intent(this, LuotSachBanChayActivity.class);
        startActivity(intent);
    }

    public void ViewListHoaDonActivity(View view) {
        intent=new Intent(this, ListHoaDonActivity.class);
        startActivity(intent);
    }

    public void viewListBookActivity(View view) {
        intent=new Intent(this, ListBookActivity.class);
        startActivity(intent);

    }

    public void viewTheLoai(View view) {
        intent=new Intent(this,ListTheLoaiActivity.class);
        startActivity(intent);
    }

    public void viewNguoiDung(View view) {
            intent=new Intent(this,ListNguoiDungActivity.class);
            startActivity(intent);
    }
}