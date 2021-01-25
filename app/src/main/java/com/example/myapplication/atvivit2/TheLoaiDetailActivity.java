package com.example.myapplication.atvivit2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.TheLoaiDAO;

public class TheLoaiDetailActivity extends AppCompatActivity {
    EditText edMatheloai, edTentheloai, edMota, edVitri;
    TheLoaiDAO theloaiDAO;
    String  ten, vi, mo, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("CHI TIẾT THỂ LOẠI");
        setContentView(R.layout.activity_the_loai_detail);

        edMatheloai = findViewById(R.id.edMatheloai);
        edTentheloai = findViewById(R.id.edTentheloai);
        edMota = findViewById(R.id.edMota);
        edVitri = findViewById(R.id.edVitri);
        theloaiDAO = new TheLoaiDAO(this);

        Intent in = getIntent();
        Bundle c = in.getExtras();
        user = c.getString("MATHELOAI");
        ten = c.getString("TENTHELOAI");
        mo = c.getString("MOTA");
        vi = c.getString("VITRI");
        edMatheloai.setText(user);
        edTentheloai.setText(ten);
        edMota.setText(mo);
        edVitri.setText(vi);
    }

    public void updateU(View view) {

        if (theloaiDAO.updateInfoTheLoai(user, edMatheloai.getText().toString(), edTentheloai.getText().toString(), edMota.getText().toString(), edVitri.getText().toString()) > 0) {
            Toast.makeText(getApplicationContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
        }
    }

    public void Huy(View view) {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}