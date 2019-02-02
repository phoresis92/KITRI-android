package com.example.usr.app7_6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends Activity {
    private EditText name;
    private EditText tel;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        name = (EditText) findViewById(R.id.editText3);
        tel = (EditText) findViewById(R.id.editText4);

        //이 액티비티를 활성화하기 위해 전달한 인텐트 반환
        intent = getIntent();

        //인텐트에 저장된 "member"라는 이름의 Member 객체를 읽는다.
        Member m = (Member) intent.getSerializableExtra("member");

        //읽은 객체가 널이 아니면 에디트 텍스트에 이름과 전화번호 출력
        if(m != null){
            name.setText(m.getName());
            tel.setText(m.getTel());
        }
    }

    //save버튼을 클릭하면 호출됨
    public void save(View view){

        //수정된 이름, 전화번호를 Member 객체에 저장
        Member m = new Member();
        m.setName(name.getText().toString());
        m.setTel(tel.getText().toString());

        //생성한 Member 객체 m을 인텐트에 저장
        intent.putExtra("member", m);

        //현재 액티비티의 결과코드 셋팅
        setResult(RESULT_OK, intent);

        //현재 액티비티 종료
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
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
