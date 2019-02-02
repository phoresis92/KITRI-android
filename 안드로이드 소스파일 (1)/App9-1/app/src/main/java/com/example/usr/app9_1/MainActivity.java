package com.example.usr.app9_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.editText);
    }

    //send버튼 클릭 시 호출
    public void send(View view){
        //MyReceiver를 명시적으로 활성화할 인텐트
        Intent intent = new Intent(this, MyReceiver.class);

        //인텐트에 추가 정보 저장
        intent.putExtra("name", name.getText().toString());

        //브로드캐스트 리시버
        sendBroadcast(intent);
        name.setText("");
    }
}
