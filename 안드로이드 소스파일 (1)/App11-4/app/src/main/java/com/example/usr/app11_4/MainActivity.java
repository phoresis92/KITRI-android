package com.example.usr.app11_4;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class MainActivity extends Activity {
    private ListView lv;
    private Button b1;
    private MyDBAdapter mdb;
    private SimpleCursorAdapter s;
    private Cursor mc;
    private int id;
    static final private int EDIT = Menu.FIRST;
    static final private int DELETE = Menu.FIRST + 1;
    static final private int REQ_EDIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.EditText01);
        lv = (ListView) findViewById(R.id.ListView01);
        b1 = (Button) findViewById(R.id.Button01);

        s = new SimpleCursorAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_2, mc, new String[] { "_id", "name" },
                new int[] { android.R.id.text1, android.R.id.text2 });
        lv.setAdapter(s);

        // DB 초기화 메서드 호출
        DBinit();
        registerForContextMenu(lv);

    }
    // DB 초기화 메서드
    public void DBinit() {
        // DB 처리 클래스 객체 생성
        mdb = new MyDBAdapter(this.getApplicationContext());
        // DB 오픈
        mdb.open();
        inArr();
    }

    // DB의 전체 데이터 읽어와 화면에 출력하는 메서드
    public void inArr() {

        // DB 처리 클래스의 getAll()메서드로 테이블 전체 데이터를 검색하여 커서 객
        //체를 받아온다.
        mc = mdb.getAll();
        // 어댑터에 데이터 변경을 알려 화면에 바인드 된 데이터를 다시 출력
        s.changeCursor(mc);

    }

    public void save(View view){
// 버튼이 클릭되면 EditText에 사용자가 입력한 데이터를 읽어와 데이
        //터의 값이 ""(공백문자)가 아니면
        // DB에 insert
        String name = et.getText().toString();
        if (!name.equals("")) {
            mdb.insertData(name);
        }
        // EditText에 새 데이터를 입력하도록 초기화
        et.setText("");
        // DB의 데이터가 변경되었으므로 DB 데이터를 새로 읽어와 화면에 새
        //로 출력해야 하므로 이를 처리하는 메서드 호출
        inArr();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    //컨텍스트 메뉴의 아이템 중 하나를 선택했을때 호출되는 메서드
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        String name = null;
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //리스트 뷰의 한 항목을 길게 눌러 컨텍스트 메뉴가 실행되었을때 리스트 뷰에
        //서 선택된 항목의 위치를 index 변수에 할당
        int index = menuInfo.position;
        //리스트 뷰에서 선택한 위치의 값을 꺼내 Cursor 객체 c에 할당
        Cursor c = (Cursor) lv.getItemAtPosition(index);
        //c 객체에 데이터가 존재하면
        if (c.moveToFirst()) {
            //커서의 위치를 index로 옮긴다.
            c.moveToPosition(index);
            //그 위치의 id와 name 컬럼의 값을 꺼내 변수에 저장
            id = c.getInt(0);
            name = c.getString(1);
        }
        //사용자가 선택한 컨텍스트 메뉴 항목의 id를 읽어온다.
        switch (item.getItemId()) {
            //메뉴 항목 id가 EDIT이면
            case EDIT:
                //수정 액티비티로 이동. 현재 리스트 뷰에서 선택한 항목의 이름값을
                //수정 액티비티에 전달
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("name", name);
                startActivityForResult(intent, REQ_EDIT);
                break;
            //메뉴 항목 id가 DELETE이면
            case DELETE:
                //id가 0이 아니면 즉 정상적인 id이면
                if (id != 0) {
                    //DB 처리 클래스의 삭제 메서드를 호출하여 데이터를 삭제한다.
                    mdb.removeData(id);
                }
                //데이터 변경이 수행되었으므로 화면 출력 메서드 호출하여 데이터를
                //새로 출력
                inArr();
                break;
        }
        return true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //요청코드가 REQ_EDIT이면
            case REQ_EDIT:
                //결과코드가 정상이면
                if (resultCode == Activity.RESULT_OK) {
                    //인텐트에 담겨진 변경된 이름을 꺼내 name 변수에 저장
                    String name = data.getStringExtra("name");
                    //변경된 이름으로 DB를 수정하기 위해 DB 처리 클래스의 update() 메
                    //서드 호출
                    mdb.updateData(id, name);
                    //데이터 변경을 화면에 적용
                    inArr();
                }
        }

    }
}
