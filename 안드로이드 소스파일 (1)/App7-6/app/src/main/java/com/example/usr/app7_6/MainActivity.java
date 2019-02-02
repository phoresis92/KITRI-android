package com.example.usr.app7_6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private EditText name;
    private EditText tel;
    private Button save;
    private ListView phoneList;
    private ArrayList<Member> members;
    private MemberAdapter adapter;
    private int idx;
    private final int EDIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.editText);
        tel = (EditText) findViewById(R.id.editText2);
        save = (Button) findViewById(R.id.button);
        phoneList = (ListView) findViewById(R.id.listView);

        members = new ArrayList<Member>();

        //사용자 정의 어댑터 객체 생성
        adapter = new MemberAdapter(this, R.layout.list_item, members);

        //리스트 뷰에 어댑터 설정
        phoneList.setAdapter(adapter);
        registerForContextMenu(phoneList);
        //save버튼 클릭 이벤트 처리
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //저장 버튼을 클릭하면 입력한 이름과 전화번호로
                // Member 객체를 생성하여 members에 저장
                Member m = new Member();
                m.setName(name.getText().toString());
                m.setTel(tel.getText().toString());
                members.add(0, m);

                //리스트 뷰 갱신
                adapter.notifyDataSetChanged();
                name.setText("");
                tel.setText("");
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
      //  super.onCreateContextMenu(menu, v, menuInfo);
        //컨텍스트 메뉴에 항목 추가
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //컨텍스트 메뉴의 타이틀 설정
        menu.setHeaderTitle("UPDATE");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //컨텍스트 메뉴를 실행할 때 길게 클릭한 뷰의 정보를 갖는 객체
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //길게 클릭한 뷰의 선택한 위치 값
        idx = info.position;

        switch (item.getItemId()){
            //선택한 메뉴 항목이 edit이면 edit()호출
            case R.id.edit:
                edit();
                return true;

            //선택한 메뉴 항목이 delete이면 del()호출
            case R.id.delete:
                del();
                return true;
        }
        return false;
    }

    public void edit(){
        //수정 액티비티를 명시적으로 활성화할 인텐트 생성
        Intent intent = new Intent(getApplicationContext(), EditActivity.class);

        //인텐트에 수정할 데이터의 원본을 저장
        intent.putExtra("member", members.get(idx));

        //수정 액티비티 활성화
        startActivityForResult(intent, EDIT);
    }

    public void del(){
        //삭제로 선택한 항목 데이터를 ArrayList에서 삭제
        members.remove(idx);

        //데이터변경을 어댑터에 알려 화면 갱신
        adapter.notifyDataSetChanged();
    }

    //startActivityForResult()에 의해 다른 액티비티를 다녀오면 호출됨
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            //반환된 요청코드가 1이면 수정 액티비티를 다녀온 것이므로
            case EDIT:
                //결과코드가 정상이면
                if(resultCode == RESULT_OK){
                    //인텐트에서 "member"라는 이름을 갖는 객체를 꺼내 변수 m에 저장
                    Member m = (Member) data.getSerializableExtra("member");

                    //m이 널이 아니면
                    if(m != null){
                        //ArrayList의 원본 데이터를 수정된 데이터로 변경
                        members.set(idx, m);

                        //데이터 변경을 어댑터에 알려 화면 갱신
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
        }
    }
}
