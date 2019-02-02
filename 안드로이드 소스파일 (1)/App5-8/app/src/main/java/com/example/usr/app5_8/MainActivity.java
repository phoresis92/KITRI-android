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

        //����� ���� ����� ��ü ����
        adapter = new MemberAdapter(this, R.layout.list_item, members);

        //����Ʈ �信 ����� ����
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
        //�����׸�Ʈ ��ü ����
            fragment = new AddFragment();
            FragmentManager fragmentManager = getFragmentManager();
            //�����׸�Ʈ Ʈ����� ����
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //�����׸�Ʈ ��ü
            fragmentTransaction.replace(R.id.fragment, fragment);
            //�����Ϸ�
            fragmentTransaction.commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addMember(View view){
//���� ��ư�� Ŭ���ϸ� �Է��� �̸��� ��ȭ��ȣ��
        // Member ��ü�� �����Ͽ� members�� ����
        Member m = new Member();
        m.setName(name.getText().toString());
        m.setTel(tel.getText().toString());
        members.add(0, m);

        //�����׸�Ʈ ��ü ����
        Fragment fragment = new ListFragment();
        FragmentManager fragmentManager = getFragmentManager();
        //�����׸�Ʈ Ʈ����� ����
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //�����׸�Ʈ ��ü
        fragmentTransaction.replace(R.id.fragment, fragment);
        //�����Ϸ�
        fragmentTransaction.commit();

            //����Ʈ �� ����
        adapter.notifyDataSetChanged();
       // name.setText("");
       // tel.setText("");
    }
}