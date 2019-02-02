package com.example.addrbook.dbtest;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.addrbook.dbtest.vo.Member;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //private EditText idE;
    private EditText nameE;
    private ListView listView;
    private ArrayAdapter<Member> adapter;
    private ArrayList<Member> list;

    private Member m;

    private MyDBAdapter dbAdapter;

    private int idx;
    private boolean flag;
    private int edit_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //idE = (EditText) findViewById(R.id.idE);
        nameE = (EditText) findViewById(R.id.nameE);


        list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.lv);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        dbAdapter = new MyDBAdapter(this);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);
        showList();

    }

    //전체 검색한 목록을 리스트 뷰에 출력
    public void showList(){

        list.clear(); // 기존 ArrayList 초기화

        dbAdapter.open();
        Cursor cursor = dbAdapter.getAll(); // DB 전체 검색

        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0); //0번 컬럼값 읽기
                String name = cursor.getString(cursor.getColumnIndex("name")); // 1번 컬럼값 읽기
                list.add(new Member(id,name)); // 컬럼값으로 member 객체 생성 ArrayList 추가
                Toast.makeText(this, id+" : "+name, Toast.LENGTH_SHORT).show();
            }while(cursor.moveToNext()); // 커서 다음줄로 이동
            adapter.notifyDataSetChanged();
        }
        dbAdapter.close();
    }

    public void onSave(View v){
        //String id = idE.getText().toString();
        String name = nameE.getText().toString();
        if(name.equals("")){
            Toast.makeText(this, "필수입력사항", Toast.LENGTH_SHORT).show();
            return;
        }

        dbAdapter.open();
        if(flag){
            dbAdapter.updateData(edit_id, name);
            flag = false;
        }else{
            dbAdapter.insertData(name);
        }

        dbAdapter.close();
        showList();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0,1,0,"edit");
        menu.add(0,2,0,"del");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        idx = info.position;
        switch(item.getItemId()){
            case 1:
                edit(idx);
                break;
            case 2:
                del(idx);
                break;
        }

        return super.onContextItemSelected(item);
    }

    public void edit(int idx){
        Member m = list.get(idx);
        edit_id = m.get_id();
        dbAdapter.open();
        nameE.setText(dbAdapter.getName(m.get_id()));
        dbAdapter.close();
        flag = true;
    }

    public void del(int idx){
        Member m = list.get(idx);
        dbAdapter.open();
        dbAdapter.removeData(m.get_id());
        dbAdapter.close();
        showList();
    }
}



/*
환경변수 설정 먼저 path
    C:\Users\hushe\AppData\Local\Android\Sdk\platform-tools

    adb shell

run-as com.example.addrbook.dbtest
        sqlite3 /data/data/com.example.addrbook.dbtest/databases/MyDB.db

        .tables

        select * from mytables;*/
