package com.example.usr.app7_5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends Activity {
    private EditText editText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editText = (EditText) findViewById(R.id.editText);

        //이 액티비티를 활성화하기 위해 전달한 인테트를 리턴한다.
        intent = getIntent();

        //인텐트에 저장된 추가 정보 중
        // 이름이 "text"인 값을 꺼내 변수 str에 저장
        String str = intent.getStringExtra("text");

        //str 값을 에디트 텍스트에 출력
        editText.setText(str);
    }

    //버튼 클릭 시 호출됨
    public void save(View view){

        //에디트 텍스트에 사용자가 수정한 텍스트를 변수 str에 저장
        String str = editText.getText().toString();

        //str 값을 인텐트에 추가 정보로 저장. 키 이름을 "text"로 한다.
        intent.putExtra("text", str);

        //이 액티비티의 작업 결과코드를 작성한다.
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
