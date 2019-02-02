package com.example.usr.app13_4;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    private EditText et;

    private Handler myHandler;
    private MyClient m;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText);


        myHandler = new Handler() {

            // 메시지를 전달받으면 호출
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                // Message 클래스의 멤버 변수 what 값이 무엇인가에 따라 처리
                // what은 메시지의 종류를 지정하는데 사용
                if (msg.what == 0) {

                    // 쓰레드가 전송한 메시지의 obj 멤버값을 토스트로 출력
                    // obj에는 서버가 전송한 데이터가 저장되어 있다.
                    Toast.makeText(getApplicationContext(), "server message : " +
                                    (String) msg.obj,
                            Toast.LENGTH_SHORT).show();
                }
            }
        };

        // 쓰레드 객체 생성
        m = new MyClient(myHandler);

        // 쓰레드 시작
        m.start();
    }
    // 버튼 클릭시 실행될 이벤트 처리
    public void send(View view) {

        // 메시지 객체 생성
        Message msg = new Message();

        // 메시지 종류 지정
        msg.what = 0;

        // 에디트 텍스트에 입력한 문자열을 메시지의 obj 멤버에 저장
        msg.obj = et.getText().toString();
        // 쓰레드쪽 핸들러에 메시지 전송
        m.getMyHandler().sendMessage(msg);
    }
}
