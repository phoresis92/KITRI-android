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

        //����� ���� ����� ��ü ����
        adapter = new MemberAdapter(this, R.layout.list_item, members);

        //����Ʈ �信 ����� ����
        phoneList.setAdapter(adapter);

        //save��ư Ŭ�� �̺�Ʈ ó��
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //���� ��ư�� Ŭ���ϸ� �Է��� �̸��� ��ȭ��ȣ��
                // Member ��ü�� �����Ͽ� members�� ����
                Member m = new Member();
                m.setName(name.getText().toString());
                m.setTel(tel.getText().toString());
                members.add(0, m);

                //����Ʈ �� ����
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
