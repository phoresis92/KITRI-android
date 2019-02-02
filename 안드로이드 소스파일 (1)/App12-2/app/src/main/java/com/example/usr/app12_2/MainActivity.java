package com.example.usr.app12_2;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
    private ListView lv;
    private Cursor c;
    private ArrayList<Person> al;
    private ArrayAdapter<Person> aa;
    private int idx;
    static final private int REQ_ADD = 1;
    static final private int REQ_EDIT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);

        al = new ArrayList<Person>();

        //����� ��ü ����
        aa = new ArrayAdapter<Person>(getApplicationContext(),
                android.R.layout.simple_list_item_1, al);

        //������ ����͸� ����Ʈ�信 ��
        lv.setAdapter(aa);

        //����Ʈ �信 ������ ����ϴ� �޼��� ȣ��
        makeList();

        //����Ʈ �信 ���ؽ�Ʈ �޴� ����
        registerForContextMenu(lv);

    }
    public void save(View view){
        //InsertActivity�� Ÿ������ �� ����� ����Ʈ ����
        Intent intent = new Intent(getApplicationContext(),
                InsertActivity.class);

        //��Ƽ��Ƽ Ȱ��ȭ. ��û�ڵ� 1
        startActivityForResult(intent, REQ_ADD);
    }

    //������ �߰��� ���� �޴��� ���������� �ش� �۾��� ���� ��Ƽ��Ƽ�� �̵��ߴٰ� ��
    //���� ���� �� ȣ���
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //��û�ڵ忡 ���� ó���ϰ� ȭ�� ����
        switch (requestCode) {

            //�߰� ��Ƽ��Ƽ�� �ٳ������ ��� ��� ����
            case REQ_ADD:
                if (resultCode == Activity.RESULT_OK) {
                    makeList();
                }
                break;

            //���� ��Ƽ��Ƽ�� �ٳ������ ��� ��� ����
            case REQ_EDIT:
                if (resultCode == Activity.RESULT_OK) {
                    makeList();
                }
                break;
        }

    }

    //���ؽ�Ʈ �޴� �׸� ����
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        //�޴� ���ҽ� ���Ͽ� ������ �׸��� �޴� �׸� ��ü�� ����
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //�޴� Ÿ��Ʋ ����
        menu.setHeaderTitle("data modify");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);

        // AdapterContextMenuInfo : ���ؽ�Ʈ �޴��� ����� �� �׸��� ������ ����
        //Ŭ����
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        //����ڰ� ���ؽ�Ʈ �޴��� ���� ���� ��� Ŭ���� �� �׸��� �ε����� �о��
        idx = menuInfo.position;

        //���ؽ�Ʈ �޴����� ������ �׸��� edit�̸� edit_data()�� REMOVE�̸�
        // remove_data()�� ȣ���Ѵ�.
        switch (item.getItemId()) {
            case R.id.edit:
                edit_data();
                makeList();
                return true;
            case R.id.delete:
                remove_data();
                makeList();
                return true;
        }
        return false;

    }
    //����Ʈ ���ι��̴��� ���� �˻��� ����� ����Ʈ �信 ����ϴ� �޼���
    public void makeList() {

        //����Ʈ ������� �̿��� URI�� Data.CONTENT_URI�� ����Ʈ ���ι��̴��� �˻�
        //��û.
        c = getContentResolver().query(Data.CONTENT_URI,
                new String[] { Data._ID, Phone.NUMBER, Phone.TYPE,
                        Phone.LABEL }, null, null, null);

        String id = null, number = null, label = null;
        int type = 0;

        //ArrayList�� ��� ������ ����
        al.clear();

        // Cursor�� ���� ��ġ�� �̵��� �����͸� �Ѷ��ξ� �д´�
        if (c.moveToFirst()) {
            do {
                // ���� ������ �����͵� �� ��ȭ��ȣ, ��ȭ����, ���̺��� �д´�
                // ���� number�� type�÷��� ���� �д´�
                id = c.getString(c.getColumnIndex(Data._ID));
                number = c.getString(c.getColumnIndex(Phone.NUMBER));
                type = c.getShort((c.getColumnIndex(Phone.TYPE)));

                // ���̺� �д´�
                label = c.getString(c.getColumnIndex(Phone.LABEL));

                //���� �÷������ Person ��ü ����
                Person p = new Person(id, label, number, type);

                //������ ��ü�� ArrayList�� ����
                al.add(p);

                // Cursor�� �����Ͱ� �����ϸ� ���� �������� �̵�
            } while (c.moveToNext());
        }

        //�������� ������ ȭ�鿡 ����. �� ȭ�� ����
        aa.notifyDataSetChanged();
    }

    //���ؽ�Ʈ �޴����� edit �׸� ���ý� ȣ���
    public void edit_data() {

        //ArrayList���� ����ڰ� ������ ��ġ�� Person��ü�� ������.
        Person p = al.get(idx);

        //���� ��Ƽ��Ƽ�� �̵��ϱ� ���� ����Ʈ ����
        Intent intent = new Intent(getApplicationContext(), EditActivity.class);

        //������ ����Ʈ�� ����ڰ� ������ �׸��� id �����Ͽ� ���� â���� ����
        intent.putExtra("id", p.getId());

        //���� ��Ƽ��Ƽ ����
        startActivityForResult(intent, REQ_EDIT);
    }

    //���ؽ�Ʈ �޴����� remove �׸� ���ý� ȣ���
    public void remove_data() {

        //ArrayList���� ����ڰ� ������ ��ġ�� Person��ü�� ������.
        Person p = al.get(idx);

        //����ڰ� ������ �׸��� id �о� ����
        String id = p.getId();

        //����Ʈ ������� id�� ������ ���� ���� ��û
        getContentResolver().delete(Data.CONTENT_URI, "DATA._ID=?",
                new String[] { id });
    }

}
