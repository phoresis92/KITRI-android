package com.example.usr.app11_3;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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
    private EditText et;
    private ListView lv;
    private MyDBAdapter mdb;
    private Cursor mc;
    private ArrayList<Member> names;
    private ArrayAdapter<Member> aa;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 레이아웃에 정의한 뷰 위젯을 자바 코드에서 사용할 수 있도록 레퍼런스 할당
        et = (EditText) findViewById(R.id.editText);
        lv = (ListView) findViewById(R.id.listView);

        // Member 객체 저장할 ArrayList 생성. DB에서 읽어들인 데이터를 ListView에
        //출력하기 전 임시 저장공간
        names = new ArrayList<Member>();

        // ArrayList에서 데이터를 읽어 ListView에 출력하는데 사용할 ArrayAdapter 객
        //체 생성. 파라메터(context객체, 레이아웃, 데이터)
        aa = new ArrayAdapter<Member>(getApplicationContext(),
                android.R.layout.simple_list_item_1, names);

        // ListView에 생성한 ArrayAdapter 설정
        lv.setAdapter(aa);

        // DB 초기화 메서드 호출
        DBinit();

        //리스트 뷰에 컨텍스트 메뉴 설정
        registerForContextMenu(lv);
    }

    //저장 버튼을 클릭할때 실행될 이벤트 처리.
    public void save(View view){

        // 버튼이 클릭되면 EditText에 사용자가 입력한 데이터를 읽어와 데이
        //터의 값이 ""(공백문자)가 아니면 DB에 insert
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
        String name;
        int id;

        // DB 처리 클래스의 getAll()메서드로 테이블 전체 데이터를 검색하여 커서 객
        //체를 받아온다.
        mc = mdb.getAll();

        // 데이터를 임시 저장할 ArrayList 비운다.
        names.clear();

        //커서에 데이터가 존재할 경우에만 실행
        if (mc.moveToFirst()) {
            //커서의 행수 만큼 반복하면서 id, name 컬럼값을 읽어 Member객체를 생
            //성 생성한 Member 객체를 ArrayList에 저장
            //이를 끝까지 반복하면 테이블에 있는 전체 데이터를 ArrayList에 모두 옮김
            do {
                id = mc.getInt(0);
                name = mc.getString(1);
                names.add(new Member(id, name));
            } while (mc.moveToNext());

            //ListView에 데이터의 변경을 알려 ArrayList의 데이터를 새로 출력
            aa.notifyDataSetChanged();
        }
    }

    //컨텍스트 메뉴에 메뉴 항목 생성
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

        //선택한 항목의 id를 읽는다.
        id = names.get(index).getId();

        //선택한 항목의 name을 읽는다.
        name = names.get(index).getName();

        //사용자가 선택한 컨텍스트 메뉴 항목의 id를 읽어온다.
        switch (item.getItemId()) {

            //메뉴 항목 id가 EDIT이면
            case R.id.edit:
                //수정 액티비티로 이동. 현재 리스트 뷰에서 선택한 항목의 이름값을
                //수정 액티비티에 전달
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("name", name);
                startActivityForResult(intent, 1);
                break;

            //메뉴 항목 id가 DELETE이면
            case R.id.delete:
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

            //요청코드가 1이면
            case 1:

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
