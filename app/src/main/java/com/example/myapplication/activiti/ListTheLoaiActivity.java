package com.example.myapplication.activiti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import android.view.Menu;

import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.Adapter.TheLoaiAdapter;
import com.example.myapplication.R;
import com.example.myapplication.TheLoai;
import com.example.myapplication.atvivit2.TheLoaiDetailActivity;
import com.example.myapplication.dao.TheLoaiDAO;

import java.util.ArrayList;
import java.util.List;

public class ListTheLoaiActivity extends AppCompatActivity {
    public static List<TheLoai> dsTheLoai = new ArrayList<>();
    ListView lvTheLoai;
    TheLoaiAdapter adapter = null;
    TheLoaiDAO theLoaiDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("THỂ LOẠI");
        setContentView(R.layout.activity_list_the_loai);
        lvTheLoai = (ListView) findViewById(R.id.lvTheLoai);
        registerForContextMenu(lvTheLoai);
        theLoaiDAO = new TheLoaiDAO(ListTheLoaiActivity.this);
        dsTheLoai = theLoaiDAO.getAllTheLoai();
        Log.e(""+dsTheLoai.size(),"s");
        adapter = new TheLoaiAdapter(this, dsTheLoai);
        lvTheLoai.setAdapter(adapter);
        lvTheLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new
                        Intent(ListTheLoaiActivity.this,TheLoaiDetailActivity.class);
                Bundle b = new Bundle();
                b.putString("MATHELOAI", dsTheLoai.get(position).getMaTheLoai());
                b.putString("TENTHELOAI", dsTheLoai.get(position).getTenTheLoai());
                b.putString("MOTA", dsTheLoai.get(position).getMoTa());
                b.putString("VITRI",
                        String.valueOf(dsTheLoai.get(position).getViTri()));
                Log.e("ra man hinh","ra man hinh");
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_theloai, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {switch(item.getItemId()) {
        case R.id.add:
            Intent intent = new
                    Intent(ListTheLoaiActivity.this, TheLoaiActivity.class);
            startActivity(intent);
            return(true);
    }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        dsTheLoai.clear();
        dsTheLoai = theLoaiDAO.getAllTheLoai();
        adapter.changeDataset(dsTheLoai);
    }
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v,
//                                    ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_context, menu);
//        menu.setHeaderTitle("Chọn thông tin");
//    }
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        switch(item.getItemId()) {
//            case R.id.eedit:
//                Intent intent1 = new
//                        Intent(ListTheLoaiActivity.this,TheLoaiActivity.class);
//                startActivity(intent1);
//                return(true);
//            case R.id.ddelete:
//                Intent intent2 = new
//                        Intent(ListTheLoaiActivity.this,TheLoaiActivity.class);
//                startActivity(intent2);
//                return(true);
//        }
//        return super.onContextItemSelected(item);
//    }
}