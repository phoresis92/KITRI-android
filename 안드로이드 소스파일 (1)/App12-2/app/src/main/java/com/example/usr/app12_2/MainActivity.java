package com.example.usr.app12_2;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
    private ListView lv;
    private Cursor c;
    private ArrayList<Person> al;
    private ArrayAdapter<Person> aa;
    private int idx;
    static final private int REQ_ADD = 1;
    static final private int REQ_EDIT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);

        al = new ArrayList<Person>();

        //어댑터 객체 생성
        aa = new ArrayAdapter<Person>(getApplicationContext(),
                android.R.layout.simple_list_item_1, al);

        //생성한 어댑터를 리스트뷰에 셋
        lv.setAdapter(aa);

        //리스트 뷰에 데이터 출력하는 메서드 호출
        makeList();

        //리스트 뷰에 컨텍스트 메뉴 설정
        registerForContextMenu(lv);

    }
    public void save(View view){
        //InsertActivity를 타겟으로 한 명시적 인텐트 생성
        Intent intent = new Intent(getApplicationContext(),
                InsertActivity.class);

        //액티비티 활성화. 요청코드 1
        startActivityForResult(intent, REQ_ADD);
    }

    //데이터 추가나 편집 메뉴를 선택했을때 해당 작업을 위한 액티비티로 이동했다가 되
    //돌아 왔을 때 호출됨
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //요청코드에 따라 처리하고 화면 갱신
        switch (requestCode) {

            //추가 액티비티를 다녀왔으면 목록 출력 갱신
            case REQ_ADD:
                if (resultCode == Activity.RESULT_OK) {
                    makeList();
                }
                break;

            //수정 액티비티를 다녀왔으면 목록 출력 갱신
            case REQ_EDIT:
                if (resultCode == Activity.RESULT_OK) {
                    makeList();
                }
                break;
        }

    }

    //컨텍스트 메뉴 항목 생성
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //메뉴 리소스 파일에 정의한 항목을 메뉴 항목 객체로 생성
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //메뉴 타이틀 설정
        menu.setHeaderTitle("data modify");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        // AdapterContextMenuInfo : 컨텍스트 메뉴와 연결된 뷰 항목의 정보를 갖는
        //클래스
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //사용자가 컨텍스트 메뉴를 띄우기 위해 길게 클릭한 뷰 항목의 인덱스를 읽어옴
        idx = menuInfo.position;

        //컨텍스트 메뉴에서 선택한 항목이 edit이면 edit_data()를 REMOVE이면
        // remove_data()를 호출한다.
        switch (item.getItemId()) {
            case R.id.edit:
                edit_data();
                makeList();
                return true;
            case R.id.delete:
                remove_data();
                makeList();
                return true;
        }
        return false;

    }
    //컨텐트 프로바이더에 의해 검색한 결과를 리스트 뷰에 출력하는 메서드
    public void makeList() {

        //컨텐트 리살버를 이용해 URI가 Data.CONTENT_URI인 컨텐트 프로바이더에 검색
        //요청.
        c = getContentResolver().query(Data.CONTENT_URI,
                new String[] { Data._ID, Phone.NUMBER, Phone.TYPE,
                        Phone.LABEL }, null, null, null);

        String id = null, number = null, label = null;
        int type = 0;

        //ArrayList의 모든 데이터 삭제
        al.clear();

        // Cursor의 시작 위치로 이동해 데이터를 한라인씩 읽는다
        if (c.moveToFirst()) {
            do {
                // 현재 라인의 데이터들 즉 전화번호, 전화종류, 레이블을 읽는다
                // 먼저 number와 type컬럼의 값을 읽는다
                id = c.getString(c.getColumnIndex(Data._ID));
                number = c.getString(c.getColumnIndex(Phone.NUMBER));
                type = c.getShort((c.getColumnIndex(Phone.TYPE)));

                // 레이블 읽는다
                label = c.getString(c.getColumnIndex(Phone.LABEL));

                //읽은 컬럼값들로 Person 객체 생성
                Person p = new Person(id, label, number, type);

                //생성한 객체를 ArrayList에 저장
                al.add(p);

                // Cursor에 데이터가 존재하면 다음 라인으로 이동
            } while (c.moveToNext());
        }

        //데이터의 변경을 화면에 적용. 즉 화면 갱신
        aa.notifyDataSetChanged();
    }

    //컨텍스트 메뉴에서 edit 항목 선택시 호출됨
    public void edit_data() {

        //ArrayList에서 사용자가 선택한 위치의 Person객체를 꺼낸다.
        Person p = al.get(idx);

        //편집 액티비티로 이동하기 위해 인텐트 생성
        Intent intent = new Intent(getApplicationContext(), EditActivity.class);

        //생성한 인텐트에 사용자가 선택한 항목의 id 저장하여 편집 창으로 전달
        intent.putExtra("id", p.getId());

        //편집 액티비티 시작
        startActivityForResult(intent, REQ_EDIT);
    }

    //컨텍스트 메뉴에서 remove 항목 선택시 호출됨
    public void remove_data() {

        //ArrayList에서 사용자가 선택한 위치의 Person객체를 꺼낸다.
        Person p = al.get(idx);

        //사용자가 선택한 항목의 id 읽어 저장
        String id = p.getId();

        //컨텐트 리살버로 id가 동일한 행의 삭제 요청
        getContentResolver().delete(Data.CONTENT_URI, "DATA._ID=?",
                new String[] { id });
    }

}
