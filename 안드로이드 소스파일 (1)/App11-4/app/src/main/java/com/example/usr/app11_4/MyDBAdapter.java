package com.example.usr.app11_4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBAdapter {
    private static final String DB = "MyDB.db"; // DB 파일명
    private static final String DB_TABLE = "MyTable"; // 테이블 명
    private static final String ID = "_id"; // 컬럼명
    private static final String NAME = "name"; // 컬럼명
    private static final int DB_VERS = 2; // DB 파일 버전

    private SQLiteDatabase mdb;
    private final Context context;
    private MyHelper mHelper;

    public MyDBAdapter(Context context) {
        this.context = context;

        // DB를 오픈하거나 버전 관리를 위해 사용할 helper클래스 생성. 파라메터로
        //Context객체, DB 파일명, DB 버전을 전달한다.
        mHelper = new MyHelper(context, DB, null, DB_VERS);
    }

    // DB 오픈 메서드
    public void open() throws SQLiteException {
        try {
            // helper클래스의 getWritableDatabase() 메서드로 DB를 읽기, 쓰기 모드로
            //오픈
            mdb = mHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            // DB 오픈시 문제가 발생하면 DB를 읽기 모드로 오픈
            mdb = mHelper.getReadableDatabase();
        }
    }

    // DB 사용이 끝난뒤 닫는 메서드
    public void close() {

        mdb.close();
    }
    //DB에 새 행을 추가하는 메서드. 추가할 데이터를 파라메터로 받고, 추가된 행의 id
    //를 반환한다
    public long insertData(String name) {

        //데이터 셋을 위한 ContentValues 객체 생성
        ContentValues cv = new ContentValues();

        // ContentValues 객체에 name 키(컬럼)에, name 변수의 값 저장
        cv.put(NAME, name);

        // SQLiteDatabase 클래스의 메서드 insert()로 실제 DB에 insert 수행. 위에서 생
        //성한 ContentValues 객체의 데이터를 테이블에 추가. 추가된 행의 id를 반환.
        return mdb.insert(DB_TABLE, null, cv);
    }

    //삭제할 조건을 파라메터로 받아 조건을 만족하는 행을 삭제하고, 성공한 행 수를
    //반환한다
    public int removeData(long index) {

        //지정된 테이블에서 id 컬럼의 값이 Index의 값과 동일한 행을 삭제하고 성공
        //한 행 수를 반환한다.
        return mdb.delete(DB_TABLE, ID + "=" + index, null);
    }

    //수정할 조건과 데이터를 파라메터로 받아, 테이블에서 조건을 만족하는 행을 찾아
    //데이터 수정
    public int updateData(long index, String name) {

        //ID 컬럼의 값이 index와 동일한 행을 찾기 위한 where절에 사용할 String생성
        String where = ID + " = " + index;

        //데이터 셋을 위한 ContentValues 객체 생성
        ContentValues cv = new ContentValues();

        // ContentValues 객체에 name 키(컬럼)에, name 변수의 값 저장
        cv.put("name", name);

        //테이블에서 where절을 만족하는 행을 찾아 ContentValues의 데이터로 수정하
        //고 수정된 행 수 반환
        return mdb.update(DB_TABLE, cv, where, null);
    }

    //테이블의 전체 레코드 검색
    public Cursor getAll() {
        return mdb.query(DB_TABLE, new String[] { ID, NAME}, null, null, null, null, null);
    }

    // helper 클래스 정의
    private static class MyHelper extends SQLiteOpenHelper {

        // 테이블 생성 sql문장
        private static final String DB_CREATE = "create table " + DB_TABLE + " (" + ID +
                " integer primary key autoincrement, " + NAME + " text not null );";

        // 생성자
        public MyHelper(Context context, String name,
                        SQLiteDatabase.CursorFactory factory, int version) {

            // 상위 클래스의 생성자에 컨텍스트, DB이름, 커서 팩토리, 버전을 전달한다.
            super(context, name, factory, version);
        }

        // helper클래스 객체 생성시 호출된다. 보통 초기화 코드 작성
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        // 데이터베이스 파일의 버전이 일치하지 않을 때 호출되는 메서드
        @Override
        public void onUpgrade(SQLiteDatabase db, int oVers, int nVers) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }

}
