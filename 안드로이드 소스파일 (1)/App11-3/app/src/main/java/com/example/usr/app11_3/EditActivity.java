package com.example.usr.app11_3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends Activity {
    private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // main.xml에 정의한 뷰위젯을 자바 코드에서 사용할 수 있도록 레퍼런스 획득
        et = (EditText) findViewById(R.id.editText2);

        //전달된 인텐트에 저장된 'name'키의 데이터를 읽어 에디트 텍스트에 출력
        Intent intent=getIntent();
        et.setText(intent.getStringExtra("name"));

    }

    public void edit(View view){
        //사용자가 에디트 텍스트에 입력한 데이터를 name 변수에 저장
        String name=et.getText().toString();

        //name이 공백문자가 아니면
        if(!name.equals("")) {

            //메인 액티비티로 돌아갈 때 전달할 데이터를 저장할 인텐트 생성
            Intent data = new Intent();

            //생성한 인텐트에 수정된 이름 저장
            data.putExtra("name", name);

            //인텐트에 결과 코드 저장
            setResult(RESULT_OK, data);

            //현 액티비티 종료. 메인 액티비티로 이동
            finish();
        }
    }
}
