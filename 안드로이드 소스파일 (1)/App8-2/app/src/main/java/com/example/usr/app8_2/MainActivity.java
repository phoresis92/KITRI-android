package com.example.usr.app8_2;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> strs;
    private int idx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        strs = new ArrayList<String>();
        strs.add("aaa");
        strs.add("bbb");
        strs.add("ccc");
        strs.add("ddd");

        //어댑터 생성
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs);

        //리스트 뷰에 어댑터 설정
        listView.setAdapter(adapter);

        //리스트 뷰에 컨텍스트 메뉴 등록
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //컨텍스트 메뉴 항목 생성
        getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //컨텍스트 메뉴 항목 선택 시 선택한 뷰 항목의 정보 객체 생성
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //선택한 뷰 항목의 위치 저장
        idx = info.position;

        //선택한 메뉴 항목이 delete이면 showDialog() 호출
        switch (item.getItemId()){
            case R.id.delete:
                showDialog();
                return true;
        }
        return false;
    }

    void showDialog() {
        //다이얼로그 프래그먼트 생성
        DialogFragment newFragment = MyDialogFragment.newInstance("are you sure?");

        //다이얼로그 실행
        newFragment.show(getFragmentManager(), "dialog");
    }

    //다이얼로그의 긍정버튼 클릭시 호출
    public void doPositiveClick() {
        //ArrayList에서 선택한 항목을 삭제
        strs.remove(idx);

        //어댑터에 데이터 변경을 알려 화면 갱신
        adapter.notifyDataSetChanged();
    }

    //다이얼로그의 부정 버튼 클릭시 호출
    public void doNegativeClick() {
        //토스트로 취소되었음을 알림
        Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
    }
}
