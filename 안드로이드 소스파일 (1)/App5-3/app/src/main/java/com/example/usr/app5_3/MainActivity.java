package com.example.usr.app5_3;

import android.app.Activity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private ListView listView;
    private ArrayList<String> strs;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);

        //ArrayList 생성
        strs = new ArrayList<String>();

        //ArrayList에 문자열 저장
        strs.add("aaa");
        strs.add("bbb");
        strs.add("ccc");

        //리스트 뷰에서 사용할 어댑터 생성
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs);
        //리스트 뷰에 어댑터 설정
        listView.setAdapter(adapter);

        //리스트 뷰 항목 선택 모드 설정
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        //CHOICE_MODE_MULTIPLE_MODAL 이벤트에 대한 리스너를 리스트뷰에 설정
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            String str = "";

            //리스트 뷰 항목 선택 상태가 변하면 호출
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                //항목 체크 상태가 true이면 선택 항목의 텍스트를 멤버변수 str에 저장
                if(checked){
                    str = strs.get(position);
                }
            }

            //액션 메뉴 항목을 선택하면 호출
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

                //선텍한 메뉴 항목의 종류에 따라 처리
                //리스트 뷰에 선택한 항목의 텍스트와 메뉴 항목의 텍스트를 출력
                switch (item.getItemId()) {
                    case R.id.item1:
                        Toast.makeText(getApplicationContext(), str+"item1", Toast.LENGTH_SHORT).show();
                        mode.finish();
                        return true;
                    case R.id.item2:
                        Toast.makeText(getApplicationContext(), str+"item2", Toast.LENGTH_SHORT).show();
                        mode.finish();
                        return true;
                    case R.id.item3:
                        Toast.makeText(getApplicationContext(), str+"item3", Toast.LENGTH_SHORT).show();
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            //액션 메뉴가 생성될 때 호출
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // 메뉴 인프레터로 menu_main.xml를 메뉴 객체로 변환
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_main, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }
        });
    }

}
