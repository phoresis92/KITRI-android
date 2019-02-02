package com.example.addrbook.dbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBAdapter {
    private static final String DB = "MyDB.db"; //DB 파일명
    private static final String DB_TABLE = "MyTable"; //테이블명
    private static final String ID = "_id"; //컬럼명
    private static final String NAME = "name"; //컬럼명
    private static final int DB_VERS = 1; //DB 파일 버전

    private SQLiteDatabase mdb; //DB API 클래스
    private final Context context; //
    private MyHelper mHelper;

    public MyDBAdapter(Context context) {
        this.context = context;
        mHelper = new MyHelper(context, DB, null, DB_VERS);
    }

    public void open() throws SQLiteException {
        try {
            mdb = mHelper.getWritableDatabase(); //읽고 쓰기 모드로 DB 파일 오픈
        } catch (SQLiteException ex) {
            mdb = mHelper.getReadableDatabase(); // 읽기 모드로 DB 파일 오픈
        }
    }

    public void close() {

        mdb.close();
    }

    public long insertData( String name) {

        // ContentValues => 값을 쓸 컬럼의 이름과 값을 저장하는 객체
        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        // insert 문을 실행하는 메서드
        return mdb.insert(DB_TABLE, null, cv);
    }
    public int removeData(long index) {

        return mdb.delete(DB_TABLE, ID + "=" + index, null);
    }

    public int updateData(long index, String name) {

        String where = ID + " = " + index;
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        return mdb.update(DB_TABLE, cv, where, null);

    }


    public Cursor getAll() {
        return mdb.query(DB_TABLE, new String[] { ID, NAME}, null, null, null, null, null);
    }

    public String getName(long _id){
        Cursor cursor = mdb.query(DB_TABLE, new String[] {ID, NAME}, ID+"="+_id, null, null, null, null);
        if(cursor.moveToFirst()){
            return cursor.getString(1);
        }
        return null;
    }

    //DB 파일을 오픈하거나 버전을 관리해주는 클래스
    private static class MyHelper extends SQLiteOpenHelper {


        private static final String DB_CREATE = "create table " + DB_TABLE + " (" + ID +
                " integer primary key autoincrement, " + NAME + " text not null );";


        public MyHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {

            super(context, name, factory, version);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oVers, int nVers) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }

}
