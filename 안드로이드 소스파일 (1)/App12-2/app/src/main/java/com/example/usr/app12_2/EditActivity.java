package com.example.usr.app12_2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class EditActivity extends Activity {
    private EditText et1;
    private EditText et2;
    private RadioButton type1;
    private RadioButton type2;
    private RadioButton type3;
    private RadioButton type4;
    private Button edit;
    private String label = "";
    private String tel = "";
    private int type = 0;
    private Cursor c;
    private Person p;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editText2);
        type1=(RadioButton)findViewById(R.id.radioButton);
        type2=(RadioButton)findViewById(R.id.radioButton2);
        type3=(RadioButton)findViewById(R.id.radioButton3);
        type4=(RadioButton)findViewById(R.id.radioButton4);

        //이 액티비티를 활성화하기 위해 던진 인텐트를 전달받음.
        intent = getIntent();

        //인텐트에 엑스트라 정보 id키 값을 꺼냄. 이는 편집할 행의 id를 나타냄
        String id = intent.getStringExtra("id");

        //DB에서 id가 동일한 행을 찾도록 컨텐트 프로바이더에게 요청하는 검색 메서드
        //호출. 반환값으로 검색 결과 데이터를 Person 객체 형태로 받는다.
        p = search(id);

        //검색 결과를 입력창에 출력한다.
        //즉 검색 결과 p의 레이블, 전화번호, 전화타입을 에디트 텍스트와 라디오 버튼에
        //출력한다.
        et1.setText(p.getLabel());
        et2.setText(p.getTel());
        switch(p.getType()){
            case 1:
                type1.setChecked(true);
                break;
            case 2:
                type2.setChecked(true);
                break;
            case 3:
                type3.setChecked(true);
                break;
            default:
                type4.setChecked(true);

        }

        OnClickListener li = new OnClickListener() {

            @Override
            public void onClick(View v) {
                RadioButton r = (RadioButton) v;
                if (r == type1) {
                    type = 1;
                } else if (r == type2) {
                    type = 2;
                } else if (r == type3) {
                    type = 3;
                } else {
                    type = 0;
                }

            }

        };

        type1.setOnClickListener(li);
        type2.setOnClickListener(li);
        type3.setOnClickListener(li);
        type4.setOnClickListener(li);

    }

    //편집 버튼을 클릭하면
    public void edit(View view){
        label = et1.getText().toString();
        tel = et2.getText().toString();

        //레이블이나 전화번호가 공백이면 메서드 종료
        if (label.equals("") || tel.equals("")) {
            Toast.makeText(getApplicationContext(),
                    "input the label and number", Toast.LENGTH_SHORT).show();
            return;
        }

        //레이블과 전화번호가 정상입력되어 있다면 수정처리 메서드 호출
        update();

        //인텐트에 결과코드 셋팅하고
        setResult(RESULT_OK, intent);

        //액티비티 종료
        finish();

    }

    public void update() {
        String where = "Data._ID=?";
        String id = p.getId();
        String[] args = new String[]{id};

        ContentValues values = new ContentValues();

        // 전화번호 저장
        values.put(Phone.NUMBER, tel);

        // 전화 종류(집전화, 모바일, 직장) 저장
        values.put(Phone.TYPE, type);

        // 사용자 식별 레이블 저장
        values.put(Phone.LABEL, label);

        //컨텐트 프로바이터에 데이터 수정 요청
        getContentResolver().update(Data.CONTENT_URI, values, where, args);
    }

    //검색 메서드
    public Person search(String id) {

        //컨텐트 리살버로 컨텐트 프로바이더에 파라메터 id와 동일한 행을 검색하도록
        //요청. 검색 결과는 커서형태로 받는다.
        c = getContentResolver().query(Data.CONTENT_URI,
                new String[] { Data._ID, Phone.NUMBER, Phone.TYPE,	Phone.LABEL },
                "Data._ID=?", new String[]{id}, null);

        String number = null, label = null;
        int type = 0;

        // Cursor의 시작 위치로 이동해 데이터를 한라인씩 읽는다
        if (c.moveToFirst()) {
            do {

                // 현재 라인의 데이터들 즉 전화번호, 전화종류, 레이블을 읽는다
                // 먼저 number와 type컬럼의 값을 읽는다
                number = c.getString(c.getColumnIndex(Phone.NUMBER));
                type = c.getShort((c.getColumnIndex(Phone.TYPE)));

                // 레이블 읽는다
                label = c.getString(c.getColumnIndex(Phone.LABEL));

                //커서에서 읽은 데이터로 Person객체 생성
                Person p = new Person(id, label, number, type);
                return p;

                // Cursor에 데이터가 존재하면 다음 라인으로 이동
            } while (c.moveToNext());
        }

        //검색 결과가 없으면 null 반환.
        return null;
    }

}
