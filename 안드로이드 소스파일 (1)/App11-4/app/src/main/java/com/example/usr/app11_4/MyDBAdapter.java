package com.example.usr.app11_4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBAdapter {
    private static final String DB = "MyDB.db"; // DB ���ϸ�
    private static final String DB_TABLE = "MyTable"; // ���̺� ��
    private static final String ID = "_id"; // �÷���
    private static final String NAME = "name"; // �÷���
    private static final int DB_VERS = 2; // DB ���� ����

    private SQLiteDatabase mdb;
    private final Context context;
    private MyHelper mHelper;

    public MyDBAdapter(Context context) {
        this.context = context;

        // DB�� �����ϰų� ���� ������ ���� ����� helperŬ���� ����. �Ķ���ͷ�
        //Context��ü, DB ���ϸ�, DB ������ �����Ѵ�.
        mHelper = new MyHelper(context, DB, null, DB_VERS);
    }

    // DB ���� �޼���
    public void open() throws SQLiteException {
        try {
            // helperŬ������ getWritableDatabase() �޼���� DB�� �б�, ���� ����
            //����
            mdb = mHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            // DB ���½� ������ �߻��ϸ� DB�� �б� ���� ����
            mdb = mHelper.getReadableDatabase();
        }
    }

    // DB ����� ������ �ݴ� �޼���
    public void close() {

        mdb.close();
    }
    //DB�� �� ���� �߰��ϴ� �޼���. �߰��� �����͸� �Ķ���ͷ� �ް�, �߰��� ���� id
    //�� ��ȯ�Ѵ�
    public long insertData(String name) {

        //������ ���� ���� ContentValues ��ü ����
        ContentValues cv = new ContentValues();

        // ContentValues ��ü�� name Ű(�÷�)��, name ������ �� ����
        cv.put(NAME, name);

        // SQLiteDatabase Ŭ������ �޼��� insert()�� ���� DB�� insert ����. ������ ��
        //���� ContentValues ��ü�� �����͸� ���̺� �߰�. �߰��� ���� id�� ��ȯ.
        return mdb.insert(DB_TABLE, null, cv);
    }

    //������ ������ �Ķ���ͷ� �޾� ������ �����ϴ� ���� �����ϰ�, ������ �� ����
    //��ȯ�Ѵ�
    public int removeData(long index) {

        //������ ���̺��� id �÷��� ���� Index�� ���� ������ ���� �����ϰ� ����
        //�� �� ���� ��ȯ�Ѵ�.
        return mdb.delete(DB_TABLE, ID + "=" + index, null);
    }

    //������ ���ǰ� �����͸� �Ķ���ͷ� �޾�, ���̺��� ������ �����ϴ� ���� ã��
    //������ ����
    public int updateData(long index, String name) {

        //ID �÷��� ���� index�� ������ ���� ã�� ���� where���� ����� String����
        String where = ID + " = " + index;

        //������ ���� ���� ContentValues ��ü ����
        ContentValues cv = new ContentValues();

        // ContentValues ��ü�� name Ű(�÷�)��, name ������ �� ����
        cv.put("name", name);

        //���̺��� where���� �����ϴ� ���� ã�� ContentValues�� �����ͷ� ������
        //�� ������ �� �� ��ȯ
        return mdb.update(DB_TABLE, cv, where, null);
    }

    //���̺��� ��ü ���ڵ� �˻�
    public Cursor getAll() {
        return mdb.query(DB_TABLE, new String[] { ID, NAME}, null, null, null, null, null);
    }

    // helper Ŭ���� ����
    private static class MyHelper extends SQLiteOpenHelper {

        // ���̺� ���� sql����
        private static final String DB_CREATE = "create table " + DB_TABLE + " (" + ID +
                " integer primary key autoincrement, " + NAME + " text not null );";

        // ������
        public MyHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {

            // ���� Ŭ������ �����ڿ� ���ؽ�Ʈ, DB�̸�, Ŀ�� ���丮, ������ �����Ѵ�.
            super(context, name, factory, version);
        }

        // helperŬ���� ��ü ������ ȣ��ȴ�. ���� �ʱ�ȭ �ڵ� �ۼ�
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        // �����ͺ��̽� ������ ������ ��ġ���� ���� �� ȣ��Ǵ� �޼���
        @Override
        public void onUpgrade(SQLiteDatabase db, int oVers, int nVers) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }

}
