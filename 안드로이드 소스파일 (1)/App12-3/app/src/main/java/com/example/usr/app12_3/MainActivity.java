package com.example.usr.app12_3;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends Activity {

    private ListView myListView;
    private EditText name;
    private EditText email;
    private ArrayAdapter s;
    private ArrayList<Member> data;
    private Cursor c;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListView = (ListView) findViewById(R.id.listView);
        name = (EditText) findViewById(R.id.editText);
        email = (EditText) findViewById(R.id.editText2);

        data = new ArrayList<Member>();

        //ArrayAdapter 생성
        s = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);

        //생성한 cursor adapter를 리스트 뷰에 설정하여 cursor에 있는 데이터를 리스트
        //뷰에 자동 출력하게 한다
        myListView.setAdapter(s);

        //리스트 뷰 출력 함수 호출
        listPrint();

        //리스트 뷰에 컨텍스트 메뉴 설정
        registerForContextMenu(myListView);
    }

    public void clear(View view) {
        ((EditText)view).setText("");
    }

    public void save(View view) {
        // 사용자가 입력한 이름과 이메일을 변수에 저장
        String str_name = name.getText().toString();
        String str_email = email.getText().toString();

        //입력받은 데이터를 contentvalues에 저장
        ContentValues cv = new ContentValues();
        cv.put(MyContentProvider.KEY_NAME, str_name);
        cv.put(MyContentProvider.KEY_EMAIL, str_email);

        //DataProvider.CONTENT_URI 상수와 동일한 URI의 provider에 insert
        //요청
        getContentResolver().insert(MyContentProvider.CONTENT_URI, cv);

        //리스트 뷰 출력 함수 호출하여 화면 갱신
        listPrint();

        //에디트 텍스트 초기화
        name.setText("");
        email.setText("");
    }
    //리스트 뷰 출력 함수
    public void listPrint() {

        // DataProvider.CONTENT_URI 상수와 동일한 URI의 provider에 테이블 전체 데이
        //터 검색요청 결과로 커서 객체를 받아온다.
        c = getContentResolver().query(MyContentProvider.CONTENT_URI, null, null, null, null);
        s.clear();
        if(c.moveToFirst()) {
            do{
                Member m = new Member();
                m.setId(c.getInt(0));
                m.setName(c.getString(1));
                m.setEmail(c.getString(2));
                s.add(m);
            }while (c.moveToNext());
        }
        // 어댑터가 화면을 갱신한다.
        s.notifyDataSetChanged();

    }

    //컨텍스트 메뉴 항목 생성
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //메뉴 리소스 파일에 정의한 항목을 메뉴 항목 객체로 생성
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //컨텍스트 메뉴의 타이틀 지정
        menu.setHeaderTitle("Delete Data");
    }

    //컨텍스트 메뉴의 아이템 중 하나를 선택했을때 호출되는 메서드
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //리스트 뷰의 한 항목을 길게 눌러 컨텍스트 메뉴가 실행되었을때 리스트 뷰에서
        //선택된 항목의 위치를 index 변수에 할당
        int id = 0;
        int index = menuInfo.position;

        //ArrayList에서 index 위치의 객체를 꺼낸다.
        Member m = data.get(index);

        id = m.getId();

        //사용자가 선택한 컨텍스트 메뉴 항목의 id를 읽어온다.
        switch (item.getItemId()) {

            case R.id.del:

                //id가 0이 아니면, 즉 정상적인 id이면
                if (id != 0) {

                    //삭제할 id를 URI 끝에 추가하여
                    Uri dataURI =
                            ContentUris.withAppendedId(MyContentProvider.CONTENT_URI, id);

                    //변경된 URI로 삭제 요청. provider가 삭제 수행시 이 id를 where절에
                    //추가하여 수행
                    getContentResolver().delete(dataURI, null, null);
                }
                //데이터 변경이 수행되었으므로 화면 출력 메서드 호출하여 데이터를 새로
                //출력
                listPrint();
                break;
        }
        return true;
    }

}
