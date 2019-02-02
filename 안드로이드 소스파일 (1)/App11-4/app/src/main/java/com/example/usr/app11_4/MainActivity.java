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

        // DB �ʱ�ȭ �޼��� ȣ��
        DBinit();
        registerForContextMenu(lv);

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

        // DB ó�� Ŭ������ getAll()�޼���� ���̺� ��ü �����͸� �˻��Ͽ� Ŀ�� ��
        //ü�� �޾ƿ´�.
        mc = mdb.getAll();
        // ����Ϳ� ������ ������ �˷� ȭ�鿡 ���ε� �� �����͸� �ٽ� ���
        s.changeCursor(mc);

    }

    public void save(View view){
// ��ư�� Ŭ���Ǹ� EditText�� ����ڰ� �Է��� �����͸� �о�� ����
        //���� ���� ""(���鹮��)�� �ƴϸ�
        // DB�� insert
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
        //����Ʈ �信�� ������ ��ġ�� ���� ���� Cursor ��ü c�� �Ҵ�
        Cursor c = (Cursor) lv.getItemAtPosition(index);
        //c ��ü�� �����Ͱ� �����ϸ�
        if (c.moveToFirst()) {
            //Ŀ���� ��ġ�� index�� �ű��.
            c.moveToPosition(index);
            //�� ��ġ�� id�� name �÷��� ���� ���� ������ ����
            id = c.getInt(0);
            name = c.getString(1);
        }
        //����ڰ� ������ ���ؽ�Ʈ �޴� �׸��� id�� �о�´�.
        switch (item.getItemId()) {
            //�޴� �׸� id�� EDIT�̸�
            case EDIT:
                //���� ��Ƽ��Ƽ�� �̵�. ���� ����Ʈ �信�� ������ �׸��� �̸�����
                //���� ��Ƽ��Ƽ�� ����
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("name", name);
                startActivityForResult(intent, REQ_EDIT);
                break;
            //�޴� �׸� id�� DELETE�̸�
            case DELETE:
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
            //��û�ڵ尡 REQ_EDIT�̸�
            case REQ_EDIT:
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
