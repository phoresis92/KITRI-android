package com.example.addrbook.phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import vo.Member;

public class PhoneBookDBA {

    private  static final String DBFILE = "PhoneBook.db";
    private  static final String DB_TABLE = "PBList";
    private  static final String ID = "_id";
    private  static final String NAME = "name";
    private  static final String TEL = "tel";
    private  static final String IMG = "img";
    private  static final int DB_VERS = 1;

    private SQLiteDatabase sdb;
    private final Context context;
    private MyHelper helper;

    public PhoneBookDBA(Context context){
        this.context = context;
        helper = new MyHelper(context, DBFILE, null, DB_VERS);
    }

    public void open(){
        try {
            sdb = helper.getWritableDatabase();
        }catch (SQLException ex){
            sdb = helper.getReadableDatabase();
        }
    }

    public void close(){
        sdb.close();
    }

    public Cursor select(Member m , int rg_id){
        String where = "";
        String[] cols = {"_id", "name", "tel", "img"};
        if(rg_id == R.id.idS){
            where = "_id=" + m.get_id();
        }else if(rg_id == R.id.nameS){
            where = "name='" + m.getName()+"'";
        }else if(rg_id == R.id.telS){
            where = "tel='" + m.getTel()+"'";
        }
        return sdb.query(DB_TABLE, cols, where,null,null,null,null);
    }

    public long insertData(String name, String tel, int img){
        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(TEL, tel);
        cv.put(IMG, img);
        return sdb.insert(DB_TABLE, null, cv);
    }

    public int removeData(int index){
        return sdb.delete(DB_TABLE, ID + "=" + index, null);
    }

    public int updateData(long index, String name, String tel, int img){
        String where = ID + "=" + index;

        ContentValues cv = new ContentValues();
        cv.put(NAME, name);
        cv.put(TEL, tel);
        cv.put(IMG, img);
        return sdb.update(DB_TABLE, cv, where, null);
    }

    public Cursor getAll(){
        return sdb.query(DB_TABLE, new String[] {ID,NAME,TEL,IMG}, null,null,null,null,null);
    }

    private static class MyHelper extends SQLiteOpenHelper{

        private static final String DB_CREATE = "create table " + DB_TABLE + "( "
                + ID + " integer primary key autoincrement,"
                + NAME + " text not null, "
                + TEL + " text not null,"
                + IMG + " integer "
                + ");";

        public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
            super(context, name, factory, version);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ DB_TABLE);
            onCreate(db);
        }
    }

}
