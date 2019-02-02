package com.example.usr.app12_4;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private EditText et1;
    private EditText et2;
    private ListView myListView;
    private ArrayAdapter<Member> s;
    private ArrayList<Member> data;
    private Cursor c;
    public static final Uri CONTENT_URI = Uri
            .parse("content://com.example.usr.app12.myProvider/email");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListView = (ListView) findViewById(R.id.listView);
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);

        data = new ArrayList<Member>();

        //ArrayAdapter 생성
        s = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);

        // 생성한 cursor adapter를 리스트 뷰에 설정하여 cursor에 있는 데이터를 리스트
        //뷰에 자동 출력하게 한다
        myListView.setAdapter(s);

        // 리스트 뷰 출력 메서드 호출
        show();

    }
    public void searchById(View v) {

        //에디트 텍스트에 입력한 id를 URI 끝에 추가
        Uri idUri = Uri.withAppendedPath(CONTENT_URI, et1.getText()+ "");

        //ContentResolver를 이용해 ContentProvider에 검색 요청
        //URI 끝에 id가 있으므로 provider에서 검색할 때 id 비교문을 where절
        //에 추가하여 처리한다. 검색 결과는 cursor로 받는다.
        Cursor cursor = getContentResolver().query(idUri, null, null, null, null);

        //email 컬럼 인덱스 저장
        int emailIdx = cursor.getColumnIndexOrThrow("email");

        //cursor 첫 라인부터 끝까지 email 컬럼 값을 꺼내 토스트로 출력
        if (cursor.moveToFirst())
            do {
                String email = cursor.getString(emailIdx);
                Toast.makeText(getApplicationContext(),
                        email, Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
    }

    public void searchByName(View v) {

        //에디트 텍스트에 입력한 이름 저장
        String name = et2.getText() + "";

        //검색할 컬럼명
        String[] col1 = new String[] { "email" };

        //where절에서 사용한 ? 값
        String[] col2 = new String[] { name };

        //where 절
        String where = "name=?";

        //ContentResolver를 이용해 ContentProvider에 검색 요청
        //파라메터로 전달한 where절로 검색하고 결과는 cursor로 전달
        Cursor cursor = getContentResolver().query(CONTENT_URI, col1,
                where, col2, null);

        //email 컬럼 인덱스 저장
        int emailIdx = cursor.getColumnIndexOrThrow("email");

        //cursor 첫 라인부터 끝까지 email 컬럼 값을 꺼내 토스트로 출력
        if (cursor.moveToFirst())
            do {
                String email = cursor.getString(emailIdx);
                Toast.makeText(getApplicationContext(),
                        email, Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
    }
    public void clear(View view) {
        ((EditText)view).setText("");
    }
    public void show() {
        // DataProvider.CONTENT_URI 상수와 동일한 URI의 provider에 테이블 전체
        //데이터 검색요청. 결과로 커서 객체를 받아온다.
        c = getContentResolver().query(CONTENT_URI, null, null, null, null);
        s.clear();
        if(c.moveToFirst()) {
            do{
                Member m = new Member();
                m.setId(c.getInt(0));
                m.setName(c.getString(1));
                s.add(m);
            }while (c.moveToNext());
        }
        // 어댑터가 화면을 갱신한다.
        s.notifyDataSetChanged();
    }

}
