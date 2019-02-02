package com.example.usr.app7_5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
    }

    public void editText(View view){

        //EditActivity를 명시적으로 활성화할 인텐트 생성
        Intent intent = new Intent(getApplicationContext(), EditActivity.class);

        //인텐트에 추가 정보로 텍스트 뷰의 텍스트를 저장한다. 키이름은 "text"로 한다.
        intent.putExtra("text", textView.getText().toString());

        //액티비티 활성화. 요청코드=1
        startActivityForResult(intent, 1);
    }

    //다른 액티비티를 방문하고 되돌아오면 자동 호출됨
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //요청코드 비교
        switch (requestCode){
            //요청코드가 1이고 결과코드가 RESULT_OK이면
            // 인텐트에서 키이름이 "text"인 값을 꺼내 텍스트 뷰에 출력
            case 1:
                if(resultCode == RESULT_OK){
                    String str = data.getStringExtra("text");
                    textView.setText(str);
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
