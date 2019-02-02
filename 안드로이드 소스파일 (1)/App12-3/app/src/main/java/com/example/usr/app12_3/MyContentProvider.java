package com.example.usr.app12_3;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class MyContentProvider extends ContentProvider {
    //DB파일명, 테이블명, DB파일 버전에 사용할 상수 정의
    public static final String DB_NAME = "memberdata.db";
    public static final String DB_TABLE = "member";
    public static final int DB_VERS = 1;

    //컬럼명 상수 정의
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_EMAIL = "email";

    //컬럼 인덱스 상수 정의
    public static final int ID_COLUMN = 0;
    public static final int NAME_COLUMN = 1;
    public static final int EMAIL_COLUMN = 2;

    private SQLiteDatabase db;
    private Context context;
    private myDBHelper dbHelper;

    //같은 어플리케이션 내에서 URI를  사용할 수 있도록 URI를 상수로 정의
    public static final Uri CONTENT_URI = Uri
            .parse("content://com.example.usr.app12.myProvider/email");

    //UriMatcher에서 URI의 형태를 구분할 때 사용할 상수 정의
    private static final int DATAS = 1;
    private static final int DATA_ID = 2;

    //컨텐트 리살버가 전달하는 URI의 형태를 비교해 주는 UriMatcher 선언
    private static final UriMatcher myURIMatcher;

    //UriMatcher 객체 생성 및 비교 대상이 될 URI 등록
    static {
        myURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        myURIMatcher.addURI("com.example.usr.app12.myProvider", "email", DATAS);
        myURIMatcher.addURI("com.example.usr.app12.myProvider", "email/#", DATA_ID);
    }


    public MyContentProvider() {
    }

    //컨텐트 프로바이더가 생성되자마자 호출되는 onCreate()에서 사용할 DB Helper 클래
    //스 객체 생성하고 DB 오픈. 정상으로 오픈되면 true, 아니면 false 반환
    @Override
    public boolean onCreate() {
        context = getContext();
        dbHelper = new myDBHelper(context, DB_NAME, null, DB_VERS);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
        return (db == null) ? false : true;
    }

    //URI 형태에 따라 MIME 반환
    //URI가 집합형이면 집합형 MIME을 URI가 단일형이면 단일형 MIME 반환
    @Override
    public String getType(Uri uri) {
        switch (myURIMatcher.match(uri)) {
            case DATAS:
                return "vnd.android.cursor.dir/vnd.example.usr.app12.myProvider";
            case DATA_ID:
                return "vnd.android.cursor.item/vnd.example.usr.app12.myProvider";
            default:
                return null;
        }
    }

    //ContentResolver.query()를 호출하면 호출되는 메서드로 검색을 수행.
    //결과로 cursor 반환
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(DB_TABLE);

        //URI 형태가 만약 단일형이라면 즉 URI 끝에 ID가 있다면 where절에 이를 추가
        if (myURIMatcher.match(uri) == DATA_ID) {
            String id = uri.getPathSegments().get(1);
            qb.appendWhere(KEY_ID + "=" + id);
        }

        //검색
        Cursor c = qb.query(db, projection, selection, selectionArgs, null, null, null);
        return c;
    }

    //ContentResolver.insert()를 호출하면 호출되는 메서드로 한 행 추가하고 새로 추가된
    //항목의 URI를 반환
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //파라메터로 받은 ContentValues를 테이블에 추가.
        //insert() 메서드는 추가된 항목의 id를 반환
        long rowID = db.insert(DB_TABLE, null, values);

        //정상적으로 데이터가 추가 되었으면 새로 추가된 항목의 URI를 반환한다.
        if (rowID > 0) {

            //새 항목의 URI 생성
            Uri uri_id = ContentUris.withAppendedId(CONTENT_URI, rowID);

            //URI 행의 데이터가 변경되었음을 리스너에 알림
            getContext().getContentResolver().notifyChange(uri_id, null);
            return uri;
        }
        return null;
    }

    //ContentResolver.update()를 호출하면 호출되는 메서드. 정상처리된 행 수 반환
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int cnt;
        //URI 형태 비교
        switch (myURIMatcher.match(uri)) {

            //URI가 집합형이면 바로 수정 실행
            case DATAS:
                cnt = db.update(DB_TABLE, values, selection, selectionArgs);
                break;

            //URI가 단일형이면 where절에 id비교 문구도 추가하여 수정 실행
            case DATA_ID:
                String id = uri.getPathSegments().get(1);
                cnt = db.update(DB_TABLE, values, KEY_ID + "="	+ id
                        + (!TextUtils.isEmpty(selection) ? " AND ("
                        + selection + ')' : ""), selectionArgs);
                break;
            default:
                return 0;
        }
        //URI 행의 데이터가 변경되었음을 리스너에 알림
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    //ContentResolver.delete()를 호출하면 호출되는 메서드. 정상처리된 행 수 반환
    @Override
    public int delete(Uri arg0, String arg1, String[] arg2) {
        int cnt;

        //URI 형태 비교
        switch (myURIMatcher.match(arg0)) {

            //URI가 집합형이면 바로 삭제 실행
            case DATAS:
                cnt = db.delete(DB_TABLE, arg1, arg2);
                break;

            //URI가 단일형이면 where절에 id비교 문구도 추가하여 삭제 실행
            case DATA_ID:
                String id = arg0.getPathSegments().get(1);
                cnt = db.delete(DB_TABLE, KEY_ID + "="	+ id
                        + (!TextUtils.isEmpty(arg1) ? " AND (" + arg1 + ')' : ""), arg2);

                break;
            default:
                return 0;
        }
        //URI 행의 데이터가 변경되었음을 리스너에 알림
        getContext().getContentResolver().notifyChange(arg0, null);

        return cnt;
    }

    //DB helper 클래스 정의
    public static class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
            super(context, name, factory, version);
        }

        private static final String DB_CREATE = "create table " + DB_TABLE
                + " (" + KEY_ID + " integer primary key autoincrement, "
                + KEY_NAME + " text not null," + KEY_EMAIL
                + " text not null );";

        //db 파일 생성시 테이블 생성
        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DB_CREATE);
        }

        //db 파일 버전이 다르면 테이블 삭제 후 다시 생성
        @Override
        public void onUpgrade(SQLiteDatabase _db, int _oldVersion,
                              int _newVersion) {
            _db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(_db);
        }
    }

}
