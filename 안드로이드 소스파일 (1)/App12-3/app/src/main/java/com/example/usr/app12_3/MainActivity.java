package com.example.usr.app12_3;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
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

    private ListView myListView;
    private EditText name;
    private EditText email;
    private ArrayAdapter s;
    private ArrayList<Member> data;
    private Cursor c;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListView = (ListView) findViewById(R.id.listView);
        name = (EditText) findViewById(R.id.editText);
        email = (EditText) findViewById(R.id.editText2);

        data = new ArrayList<Member>();

        //ArrayAdapter ����
        s = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);

        //������ cursor adapter�� ����Ʈ �信 �����Ͽ� cursor�� �ִ� �����͸� ����Ʈ
        //�信 �ڵ� ����ϰ� �Ѵ�
        myListView.setAdapter(s);

        //����Ʈ �� ��� �Լ� ȣ��
        listPrint();

        //����Ʈ �信 ���ؽ�Ʈ �޴� ����
        registerForContextMenu(myListView);
    }

    public void clear(View view) {
        ((EditText)view).setText("");
    }

    public void save(View view) {
        // ����ڰ� �Է��� �̸��� �̸����� ������ ����
        String str_name = name.getText().toString();
        String str_email = email.getText().toString();

        //�Է¹��� �����͸� contentvalues�� ����
        ContentValues cv = new ContentValues();
        cv.put(MyContentProvider.KEY_NAME, str_name);
        cv.put(MyContentProvider.KEY_EMAIL, str_email);

        //DataProvider.CONTENT_URI ����� ������ URI�� provider�� insert
        //��û
        getContentResolver().insert(MyContentProvider.CONTENT_URI, cv);

        //����Ʈ �� ��� �Լ� ȣ���Ͽ� ȭ�� ����
        listPrint();

        //����Ʈ �ؽ�Ʈ �ʱ�ȭ
        name.setText("");
        email.setText("");
    }
    //����Ʈ �� ��� �Լ�
    public void listPrint() {

        // DataProvider.CONTENT_URI ����� ������ URI�� provider�� ���̺� ��ü ����
        //�� �˻���û ����� Ŀ�� ��ü�� �޾ƿ´�.
        c = getContentResolver().query(MyContentProvider.CONTENT_URI, null, null, null, null);
        s.clear();
        if(c.moveToFirst()) {
            do{
                Member m = new Member();
                m.setId(c.getInt(0));
                m.setName(c.getString(1));
                m.setEmail(c.getString(2));
                s.add(m);
            }while (c.moveToNext());
        }
        // ����Ͱ� ȭ���� �����Ѵ�.
        s.notifyDataSetChanged();

    }

    //���ؽ�Ʈ �޴� �׸� ����
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //�޴� ���ҽ� ���Ͽ� ������ �׸��� �޴� �׸� ��ü�� ����
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //���ؽ�Ʈ �޴��� Ÿ��Ʋ ����
        menu.setHeaderTitle("Delete Data");
    }

    //���ؽ�Ʈ �޴��� ������ �� �ϳ��� ���������� ȣ��Ǵ� �޼���
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //����Ʈ ���� �� �׸��� ��� ���� ���ؽ�Ʈ �޴��� ����Ǿ����� ����Ʈ �信��
        //���õ� �׸��� ��ġ�� index ������ �Ҵ�
        int id = 0;
        int index = menuInfo.position;

        //ArrayList���� index ��ġ�� ��ü�� ������.
        Member m = data.get(index);

        id = m.getId();

        //����ڰ� ������ ���ؽ�Ʈ �޴� �׸��� id�� �о�´�.
        switch (item.getItemId()) {

            case R.id.del:

                //id�� 0�� �ƴϸ�, �� �������� id�̸�
                if (id != 0) {

                    //������ id�� URI ���� �߰��Ͽ�
                    Uri dataURI =
                            ContentUris.withAppendedId(MyContentProvider.CONTENT_URI, id);

                    //����� URI�� ���� ��û. provider�� ���� ����� �� id�� where����
                    //�߰��Ͽ� ����
                    getContentResolver().delete(dataURI, null, null);
                }
                //������ ������ ����Ǿ����Ƿ� ȭ�� ��� �޼��� ȣ���Ͽ� �����͸� ����
                //���
                listPrint();
                break;
        }
        return true;
    }

}
