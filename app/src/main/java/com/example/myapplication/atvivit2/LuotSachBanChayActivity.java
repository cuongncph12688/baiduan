package com.example.myapplication.atvivit2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Adapter.BookAdapter;
import com.example.myapplication.R;
import com.example.myapplication.Sach;
import com.example.myapplication.dao.SachDAO;

import java.util.ArrayList;
import java.util.List;

public class LuotSachBanChayActivity extends AppCompatActivity {
    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBook;
    BookAdapter adapter = null;
    SachDAO sachDAO;
    EditText edThang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("TOP 10 SÁCH BÁN CHẠY");
        setContentView(R.layout.activity_luot_sach_ban_chay);
        lvBook = (ListView) findViewById(R.id.lvBookTop);
        edThang = (EditText) findViewById(R.id.edThang);
    }
    public void VIEW_SACH_TOP_10(View view){
       try {
           if (Integer.parseInt(edThang.getText().toString())>13 ||
                   Integer.parseInt(edThang.getText().toString())<0){
               Toast.makeText(getApplicationContext(),"Không đúng định dạng tháng (1- 12)",Toast.LENGTH_SHORT).show();
           }else {
               sachDAO = new SachDAO(LuotSachBanChayActivity.this);
               dsSach = sachDAO.getSachTop10(edThang.getText().toString());adapter = new BookAdapter(this, dsSach);
               lvBook.setAdapter(adapter);
           }
       } catch (NumberFormatException e) {
           e.printStackTrace();
           Toast.makeText(this, "mới bạn nhập số", Toast.LENGTH_SHORT).show();
       }
    }
}