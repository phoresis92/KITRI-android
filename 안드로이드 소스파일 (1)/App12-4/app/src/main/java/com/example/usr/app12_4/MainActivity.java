package com.example.usr.app12_4;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private EditText et1;
    private EditText et2;
    private ListView myListView;
    private ArrayAdapter<Member> s;
    private ArrayList<Member> data;
    private Cursor c;
    public static final Uri CONTENT_URI = Uri
            .parse("content://com.example.usr.app12.myProvider/email");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myListView = (ListView) findViewById(R.id.listView);
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);

        data = new ArrayList<Member>();

        //ArrayAdapter ����
        s = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);

        // ������ cursor adapter�� ����Ʈ �信 �����Ͽ� cursor�� �ִ� �����͸� ����Ʈ
        //�信 �ڵ� ����ϰ� �Ѵ�
        myListView.setAdapter(s);

        // ����Ʈ �� ��� �޼��� ȣ��
        show();

    }
    public void searchById(View v) {

        //����Ʈ �ؽ�Ʈ�� �Է��� id�� URI ���� �߰�
        Uri idUri = Uri.withAppendedPath(CONTENT_URI, et1.getText()+ "");

        //ContentResolver�� �̿��� ContentProvider�� �˻� ��û
        //URI ���� id�� �����Ƿ� provider���� �˻��� �� id �񱳹��� where��
        //�� �߰��Ͽ� ó���Ѵ�. �˻� ����� cursor�� �޴´�.
        Cursor cursor = getContentResolver().query(idUri, null, null, null, null);

        //email �÷� �ε��� ����
        int emailIdx = cursor.getColumnIndexOrThrow("email");

        //cursor ù ���κ��� ������ email �÷� ���� ���� �佺Ʈ�� ���
        if (cursor.moveToFirst())
            do {
                String email = cursor.getString(emailIdx);
                Toast.makeText(getApplicationContext(),
                        email, Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
    }

    public void searchByName(View v) {

        //����Ʈ �ؽ�Ʈ�� �Է��� �̸� ����
        String name = et2.getText() + "";

        //�˻��� �÷���
        String[] col1 = new String[] { "email" };

        //where������ ����� ? ��
        String[] col2 = new String[] { name };

        //where ��
        String where = "name=?";

        //ContentResolver�� �̿��� ContentProvider�� �˻� ��û
        //�Ķ���ͷ� ������ where���� �˻��ϰ� ����� cursor�� ����
        Cursor cursor = getContentResolver().query(CONTENT_URI, col1,
                where, col2, null);

        //email �÷� �ε��� ����
        int emailIdx = cursor.getColumnIndexOrThrow("email");

        //cursor ù ���κ��� ������ email �÷� ���� ���� �佺Ʈ�� ���
        if (cursor.moveToFirst())
            do {
                String email = cursor.getString(emailIdx);
                Toast.makeText(getApplicationContext(),
                        email, Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
    }
    public void clear(View view) {
        ((EditText)view).setText("");
    }
    public void show() {
        // DataProvider.CONTENT_URI ����� ������ URI�� provider�� ���̺� ��ü
        //������ �˻���û. ����� Ŀ�� ��ü�� �޾ƿ´�.
        c = getContentResolver().query(CONTENT_URI, null, null, null, null);
        s.clear();
        if(c.moveToFirst()) {
            do{
                Member m = new Member();
                m.setId(c.getInt(0));
                m.setName(c.getString(1));
                s.add(m);
            }while (c.moveToNext());
        }
        // ����Ͱ� ȭ���� �����Ѵ�.
        s.notifyDataSetChanged();
    }

}
