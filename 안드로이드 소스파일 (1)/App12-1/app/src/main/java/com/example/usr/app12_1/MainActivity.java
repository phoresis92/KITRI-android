package com.example.usr.app12_1;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ������ insert�� ����� ContentValues ��ü ����
        ContentValues values = new ContentValues();

        String contactId = "aaa";
        // ������ ���̺��� _id �÷��� ������ ���۷���
        values.put(ContactsContract.RawContacts.CONTACT_ID, contactId);
        // ContentValues ��ü�� �̿��� DB�� id����
        Uri contactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);

        // _id �÷��� �� �о�� �Ҵ�
        long contactId_l = ContentUris.parseId(contactUri);

        // ContentValues�� �����͸� ��� ����(�� �����͸� �ֱ� ���ؼ�)
        values.clear();

        // ContentValues�� ������ �������� id ����
        values.put(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, contactId_l);


        // ������ ���� Ÿ�� ����
        values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
        // ��ȭ��ȣ ����
        values.put(Phone.NUMBER, "010-1212-3434");
        // ��ȭ ����(����ȭ, �����, ����) ����
        values.put(Phone.TYPE, Phone.TYPE_MOBILE);
        // ����� �ĺ� ���̺� ����
        values.put(Phone.LABEL, "aaa");
        // ContentValues�� �����͸� DB ���̺� insert
        Uri dataUri = getContentResolver().insert(Data.CONTENT_URI, values);

        // content provider�� �˻� ��û. ù �Ķ���ʹ� ����� content provider��
        //uri ����, �� ��° �Ķ���ʹ� �˻��� �÷���
        // �˻� ����� Cursor ��ü�� ����
        Cursor c = getContentResolver().query(Data.CONTENT_URI,
                new String[] { Data._ID, Phone.NUMBER, Phone.TYPE,
                        Phone.LABEL }, null, null, null);
        String id = null, number = null, type = null, label = null;
        int num;
        // Cursor�� ���� ��ġ�� �̵��� �����͸� �Ѷ��ξ� �д´�
        if (c.moveToFirst()) {
            do {
                // ���� ������ �����͵� �� ��ȭ��ȣ, ��ȭ����, ���̺��� �д´�
                // ���� number�� type�÷��� ���� �д´�
                number = c.getString(c.getColumnIndex(Phone.NUMBER));
                num = c.getShort((c.getColumnIndex(Phone.TYPE)));

                // ��ȭ�� Ÿ���� ��Ÿ���� ���� ���� �̹Ƿ� �̸� ����ڰ� ������ �� �ִ�
                //����(����ȭ, �繫�� ��)�� ��ȯ
                switch (num) {
                    case 1:
                        type = "HOME";
                        break;
                    case 2:
                        type = "MOBILE";
                        break;
                    case 3:
                        type = "WORK";
                        break;
                    default:
                        type = "ETC";
                }
                // ���̺� �д´�
                label = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.LABEL));
                //Cursor�� ���� ���� �����͵��� �ϳ��� ���ڿ��� ����
                String str = "label:" + label + ", number:" + number+ ", type:" + type;
                //������ �����͸� �佺Ʈ�� ���
                Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
                toast.show();
                //Cursor�� �����Ͱ� �����ϸ� ���� �������� �̵�
            } while (c.moveToNext());
        }

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
