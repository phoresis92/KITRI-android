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

        //����� ���� ����� ��ü ����
        adapter = new MemberAdapter(this, R.layout.list_item, members);

        //����Ʈ �信 ����� ����
        phoneList.setAdapter(adapter);
        registerForContextMenu(phoneList);
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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
      //  super.onCreateContextMenu(menu, v, menuInfo);
        //���ؽ�Ʈ �޴��� �׸� �߰�
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //���ؽ�Ʈ �޴��� Ÿ��Ʋ ����
        menu.setHeaderTitle("UPDATE");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //���ؽ�Ʈ �޴��� ������ �� ��� Ŭ���� ���� ������ ���� ��ü
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //��� Ŭ���� ���� ������ ��ġ ��
        idx = info.position;

        switch (item.getItemId()){
            //������ �޴� �׸��� edit�̸� edit()ȣ��
            case R.id.edit:
                edit();
                return true;

            //������ �޴� �׸��� delete�̸� del()ȣ��
            case R.id.delete:
                del();
                return true;
        }
        return false;
    }

    public void edit(){
        //���� ��Ƽ��Ƽ�� ��������� Ȱ��ȭ�� ����Ʈ ����
        Intent intent = new Intent(getApplicationContext(), EditActivity.class);

        //����Ʈ�� ������ �������� ������ ����
        intent.putExtra("member", members.get(idx));

        //���� ��Ƽ��Ƽ Ȱ��ȭ
        startActivityForResult(intent, EDIT);
    }

    public void del(){
        //������ ������ �׸� �����͸� ArrayList���� ����
        members.remove(idx);

        //�����ͺ����� ����Ϳ� �˷� ȭ�� ����
        adapter.notifyDataSetChanged();
    }

    //startActivityForResult()�� ���� �ٸ� ��Ƽ��Ƽ�� �ٳ���� ȣ���
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            //��ȯ�� ��û�ڵ尡 1�̸� ���� ��Ƽ��Ƽ�� �ٳ�� ���̹Ƿ�
            case EDIT:
                //����ڵ尡 �����̸�
                if(resultCode == RESULT_OK){
                    //����Ʈ���� "member"��� �̸��� ���� ��ü�� ���� ���� m�� ����
                    Member m = (Member) data.getSerializableExtra("member");

                    //m�� ���� �ƴϸ�
                    if(m != null){
                        //ArrayList�� ���� �����͸� ������ �����ͷ� ����
                        members.set(idx, m);

                        //������ ������ ����Ϳ� �˷� ȭ�� ����
                        adapter.notifyDataSetChanged();
                    }
                }
                break;
        }
    }
}
