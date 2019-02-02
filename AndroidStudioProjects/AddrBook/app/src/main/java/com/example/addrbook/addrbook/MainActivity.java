package com.example.addrbook.addrbook;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import vo.Member;

public class MainActivity extends AppCompatActivity {

    //어댑터 설정
    private ListView profileList ;
    private ArrayList<Member> list ;
    private AddrAdapter2 adapter;

    //맴버빈
    private Member mem = null;

    //컨택스트 메뉴 설정
    private static final int EDIT = 1;
    private static final int DEL = 2;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileList = (ListView) findViewById(R.id.profileList);
        list = new ArrayList<Member>();
        adapter = new AddrAdapter2(this, R.layout.item_layout2, list);
        profileList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        //컨택스트 메뉴 설정
        registerForContextMenu(profileList);


    }

    // 컨택스트 메뉴 설정 ======================================================================================
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("UPDATE");
        menu.add(0, EDIT , 0, "EDIT");
        menu.add(0, DEL , 0, "DEL");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        index = menuInfo.position;


        switch(item.getItemId()){
            case EDIT:
                //editForm();
                Intent intent = new Intent(this, editFormIntent.class);
                Member m = list.get(index);
                intent.putExtra("m", m);
                startActivityForResult(intent, 2);
                break;
            case DEL:
                list.remove(index);
                break;
        }
        adapter.notifyDataSetChanged();
        return true;
    }

    //옵션메뉴 설정 ===========================================================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0,1,0,"추가");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case 1:
                addProfile();
                break;
        }

        return true;
    }

    // 프로필 추가 메소드 ===============================================
    public void addProfile(){
        Intent intent = new Intent(this, addProfile.class);
        startActivityForResult(intent, 1);
    }
    // 받아온 정보로 프로필 추가
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch(requestCode){

                case 1:
                    mem = (Member) data.getSerializableExtra("m");
                    list.add(0,mem);
                    break;
                case 2:
                     mem = (Member) data.getSerializableExtra("m");
                    list.set(index, mem);

                    break;
            }
            adapter.notifyDataSetChanged();
        }


    }
}
