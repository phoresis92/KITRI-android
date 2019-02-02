package com.example.usr.app12_2;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class InsertActivity extends Activity {
    private EditText et1;
    private EditText et2;
    private RadioButton type1;
    private RadioButton type2;
    private RadioButton type3;
    private RadioButton type4;
    private String label="";
    private String tel="";
    private int type=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        type1=(RadioButton)findViewById(R.id.radioButton);
        type2=(RadioButton)findViewById(R.id.radioButton2);
        type3=(RadioButton)findViewById(R.id.radioButton3);
        type4=(RadioButton)findViewById(R.id.radioButton4);

        //라디오 버튼에 셋팅할 클릭 리스너 객체 생성
        OnClickListener li=new OnClickListener(){

            //핸들러
            @Override
            public void onClick(View v) {
                RadioButton r=(RadioButton)v;

                //선택된 라디오 버튼의 종류에 따라 type 값 설정
                //type 값은 한 행 추가 시 전화의 종류(집전화, 모바일 등)를 지정하는
                //값으로 사용된다.
                if(r==type1){
                    type=1;
                }else if(r==type2){
                    type=2;
                }else if(r==type3){
                    type=3;
                }else{
                    type=0;
                }

            }

        };

        //생성한 클릭 리스너를 4개의 라디오 버튼에 설정
        type1.setOnClickListener(li);
        type2.setOnClickListener(li);
        type3.setOnClickListener(li);
        type4.setOnClickListener(li);

    }

    //ok 버튼 클릭 시 호출
    public void ok(View view){
        label=et1.getText().toString();
        tel=et2.getText().toString();

        //사용자가 레이블과 전화번호를 입력하지 않으면 입력하도록 메시지
        //출력 후 현재 메서드 종료
        if(label.equals("")|| tel.equals("")){
            Toast.makeText(getApplicationContext(),
                    "input the label and number", Toast.LENGTH_SHORT).show();
            return;
        }

        //레이블과 전화번호를 모두 입력했다면 저장을 처리하는 insert() 메서드
        //호출
        insert();

        //한 행 추가 처리가 끝나면 result code 설정 후 액티비티 종료
        Intent intent=getIntent();
        setResult(RESULT_OK, intent);
        finish();

    }


    //이 액티비티가 화면에 뜰 때마다 각 입력창을 초기화한다.
    @Override
    protected void onResume() {
        super.onResume();
        label="";
        tel="";
        type=0;
        et1.setText("");
        et2.setText("");
    }

    //컨텐트 리살버를 이용해 폰북의 컨텐트 프로바이더에 한 행추가 요청
    public void insert(){

        // 데이터 insert에 사용할 ContentValues 객체 생성
        ContentValues values = new ContentValues();

        String contactId = label;

        // 데이터 테이블의 _id 컬럼에 접근할 레퍼런스
        values.put(RawContacts.CONTACT_ID, contactId);

        // ContentValues 객체를 이용해 DB에 id저장
        Uri contactUri = getContentResolver().insert(
                RawContacts.CONTENT_URI, values);

        // _id 컬럼의 값 읽어와 할당
        long contactId_l = ContentUris.parseId(contactUri);

        // ContentValues의 데이터를 모두 삭제(새 데이터를 넣기 위해서)
        values.clear();

        // ContentValues에 저장할 데이터의 id 저장
        values.put(Data.RAW_CONTACT_ID, contactId_l);

        // 데이터 마임 타입 저장
        values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);

        // 전화번호 저장
        values.put(Phone.NUMBER, tel);

        // 전화 종류(집전화, 모바일, 직장) 저장
        values.put(Phone.TYPE, type);

        // 사용자 식별 레이블 저장
        values.put(Phone.LABEL, label);

        // ContentValues의 데이터를 DB 테이블에 insert
        getContentResolver().insert(Data.CONTENT_URI, values);
    }

}
