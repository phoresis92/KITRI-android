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

        // ���̾ƿ��� ������ �� ������ �ڹ� �ڵ忡�� ����� �� �ֵ��� ���۷��� �Ҵ�
        et = (EditText) findViewById(R.id.editText);
        lv = (ListView) findViewById(R.id.listView);

        // Member ��ü ������ ArrayList ����. DB���� �о���� �����͸� ListView��
        //����ϱ� �� �ӽ� �������
        names = new ArrayList<Member>();

        // ArrayList���� �����͸� �о� ListView�� ����ϴµ� ����� ArrayAdapter ��
        //ü ����. �Ķ����(context��ü, ���̾ƿ�, ������)
        aa = new ArrayAdapter<Member>(getApplicationContext(),
                android.R.layout.simple_list_item_1, names);

        // ListView�� ������ ArrayAdapter ����
        lv.setAdapter(aa);

        // DB �ʱ�ȭ �޼��� ȣ��
        DBinit();

        //����Ʈ �信 ���ؽ�Ʈ �޴� ����
        registerForContextMenu(lv);
    }

    //���� ��ư�� Ŭ���Ҷ� ����� �̺�Ʈ ó��.
    public void save(View view){

        // ��ư�� Ŭ���Ǹ� EditText�� ����ڰ� �Է��� �����͸� �о�� ����
        //���� ���� ""(���鹮��)�� �ƴϸ� DB�� insert
        String name = et.getText().toString();
        if (!name.equals("")) {
            mdb.insertData(name);
        }

        // EditText�� �� �����͸� �Է��ϵ��� �ʱ�ȭ
        et.setText("");

        // DB�� �����Ͱ� ����Ǿ����Ƿ� DB �����͸� ���� �о�� ȭ�鿡 ��
        //�� ����ؾ� �ϹǷ� �̸� ó���ϴ� �޼��� ȣ��
        inArr();

    }

    // DB �ʱ�ȭ �޼���
    public void DBinit() {
        // DB ó�� Ŭ���� ��ü ����
        mdb = new MyDBAdapter(this.getApplicationContext());

        // DB ����
        mdb.open();

        inArr();
    }

    // DB�� ��ü ������ �о�� ȭ�鿡 ����ϴ� �޼���
    public void inArr() {
        String name;
        int id;

        // DB ó�� Ŭ������ getAll()�޼���� ���̺� ��ü �����͸� �˻��Ͽ� Ŀ�� ��
        //ü�� �޾ƿ´�.
        mc = mdb.getAll();

        // �����͸� �ӽ� ������ ArrayList ����.
        names.clear();

        //Ŀ���� �����Ͱ� ������ ��쿡�� ����
        if (mc.moveToFirst()) {
            //Ŀ���� ��� ��ŭ �ݺ��ϸ鼭 id, name �÷����� �о� Member��ü�� ��
            //�� ������ Member ��ü�� ArrayList�� ����
            //�̸� ������ �ݺ��ϸ� ���̺� �ִ� ��ü �����͸� ArrayList�� ��� �ű�
            do {
                id = mc.getInt(0);
                name = mc.getString(1);
                names.add(new Member(id, name));
            } while (mc.moveToNext());

            //ListView�� �������� ������ �˷� ArrayList�� �����͸� ���� ���
            aa.notifyDataSetChanged();
        }
    }

    //���ؽ�Ʈ �޴��� �޴� �׸� ����
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    //���ؽ�Ʈ �޴��� ������ �� �ϳ��� ���������� ȣ��Ǵ� �޼���
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        String name = null;
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //����Ʈ ���� �� �׸��� ��� ���� ���ؽ�Ʈ �޴��� ����Ǿ����� ����Ʈ �信
        //�� ���õ� �׸��� ��ġ�� index ������ �Ҵ�
        int index = menuInfo.position;

        //������ �׸��� id�� �д´�.
        id = names.get(index).getId();

        //������ �׸��� name�� �д´�.
        name = names.get(index).getName();

        //����ڰ� ������ ���ؽ�Ʈ �޴� �׸��� id�� �о�´�.
        switch (item.getItemId()) {

            //�޴� �׸� id�� EDIT�̸�
            case R.id.edit:
                //���� ��Ƽ��Ƽ�� �̵�. ���� ����Ʈ �信�� ������ �׸��� �̸�����
                //���� ��Ƽ��Ƽ�� ����
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("name", name);
                startActivityForResult(intent, 1);
                break;

            //�޴� �׸� id�� DELETE�̸�
            case R.id.delete:
                //id�� 0�� �ƴϸ� �� �������� id�̸�
                if (id != 0) {
                    //DB ó�� Ŭ������ ���� �޼��带 ȣ���Ͽ� �����͸� �����Ѵ�.
                    mdb.removeData(id);
                }
                //������ ������ ����Ǿ����Ƿ� ȭ�� ��� �޼��� ȣ���Ͽ� �����͸�
                //���� ���
                inArr();
                break;
        }
        return true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            //��û�ڵ尡 1�̸�
            case 1:

                //����ڵ尡 �����̸�
                if (resultCode == Activity.RESULT_OK) {

                    //����Ʈ�� ����� ����� �̸��� ���� name ������ ����
                    String name = data.getStringExtra("name");

                    //����� �̸����� DB�� �����ϱ� ���� DB ó�� Ŭ������ update() ��
                    //���� ȣ��
                    mdb.updateData(id, name);

                    //������ ������ ȭ�鿡 ����
                    inArr();
                }
        }

    }
}
