package com.example.usr.crtest;

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
        // 데이터 insert에 사용할 ContentValues 객체 생성
        ContentValues values = new ContentValues();

        String contactId = "aaa";
        // 데이터 테이블의 _id 컬럼에 접근할 레퍼런스
        values.put(ContactsContract.RawContacts.CONTACT_ID, contactId);
        // ContentValues 객체를 이용해 DB에 id저장
        Uri contactUri = getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);

        // _id 컬럼의 값 읽어와 할당
        long contactId_l = ContentUris.parseId(contactUri);

        // ContentValues의 데이터를 모두 삭제(새 데이터를 넣기 위해서)
        values.clear();

        // ContentValues에 저장할 데이터의 id 저장
        values.put(ContactsContract.RawContacts.Data.RAW_CONTACT_ID, contactId_l);


        // 데이터 마임 타입 저장
        values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
        // 전화번호 저장
        values.put(Phone.NUMBER, "010-1212-3434");
        // 전화 종류(집전화, 모바일, 직장) 저장
        values.put(Phone.TYPE, Phone.TYPE_MOBILE);
        // 사용자 식별 레이블 저장
        values.put(Phone.LABEL, "aaa");
        // ContentValues의 데이터를 DB 테이블에 insert
        Uri dataUri = getContentResolver().insert(Data.CONTENT_URI, values);

        // content provider에 검색 요청. 첫 파라메터는 사용할 content provider의
        //uri 지정, 두 번째 파라메터는 검색할 컬럼명
        // 검색 결과를 Cursor 객체에 저장
        Cursor c = getContentResolver().query(Data.CONTENT_URI,
                new String[]{Data._ID, Phone.NUMBER, Phone.TYPE,
                        Phone.LABEL}, null, null, null);
        String id = null, number = null, type = null, label = null;
        int num;
        // Cursor의 시작 위치로 이동해 데이터를 한라인씩 읽는다
        if (c.moveToFirst()) {
            do {
                // 현재 라인의 데이터들 즉 전화번호, 전화종류, 레이블을 읽는다
                // 먼저 number와 type컬럼의 값을 읽는다
                number = c.getString(c.getColumnIndex(Phone.NUMBER));
                num = c.getShort((c.getColumnIndex(Phone.TYPE)));

                // 전화의 타입을 나타내는 값이 정수 이므로 이를 사용자가 이해할 수 있는
                //형태(집전화, 사무실 등)로 변환
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
                // 레이블 읽는다
                label = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.LABEL));
                //Cursor로 부터 읽은 데이터들을 하나의 문자열로 연결
                String str = "label:" + label + ", number:" + number + ", type:" + type;
                //연결한 데이터를 토스트로 출력
                Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
                toast.show();
                //Cursor에 데이터가 존재하면 다음 라인으로 이동
            } while (c.moveToNext());
        }

    }
}