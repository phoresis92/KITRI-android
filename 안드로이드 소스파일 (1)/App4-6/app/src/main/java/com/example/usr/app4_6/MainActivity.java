package com.example.usr.app4_6;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
