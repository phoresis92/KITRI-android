package com.example.usr.app5_8;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {
    public static EditText name;
    public static EditText tel;
    public static Button save;
    public static ListView phoneList;
    private ArrayList<Member> members;
    private MemberAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        members = new ArrayList<Member>();

        //사용자 정의 어댑터 객체 생성
        adapter = new MemberAdapter(this, R.layout.list_item, members);

        //리스트 뷰에 어댑터 설정
        phoneList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Fragment fragment = null;
        //noinspection SimplifiableIfStatement
        if (id == R.id.add) {
        //프래그먼트 객체 생성
            fragment = new AddFragment();
            FragmentManager fragmentManager = getFragmentManager();
            //프래그먼트 트랜잭션 시작
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //프래그머트 교체
            fragmentTransaction.replace(R.id.fragment, fragment);
            //수정완료
            fragmentTransaction.commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addMember(View view){
//저장 버튼을 클릭하면 입력한 이름과 전화번호로
        // Member 객체를 생성하여 members에 저장
        Member m = new Member();
        m.setName(name.getText().toString());
        m.setTel(tel.getText().toString());
        members.add(0, m);

        //프래그먼트 객체 생성
        Fragment fragment = new ListFragment();
        FragmentManager fragmentManager = getFragmentManager();
        //프래그먼트 트랜잭션 시작
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //프래그머트 교체
        fragmentTransaction.replace(R.id.fragment, fragment);
        //수정완료
        fragmentTransaction.commit();

            //리스트 뷰 갱신
        adapter.notifyDataSetChanged();
       // name.setText("");
       // tel.setText("");
    }
}