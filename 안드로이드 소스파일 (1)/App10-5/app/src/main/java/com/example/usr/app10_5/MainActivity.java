package com.example.usr.app10_5;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

public class MainActivity extends Activity {
    private MyThread m;
    private int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //쓰레드 객체 생성
        m = new MyThread(getApplication());

        //thread 시작
        m.start();

    }

    public void getMsg(View v) {

        //풀에서 Message 객체 반환
        Message msg= Message.obtain();

        String str="main message"+cnt;

        //메시지 종류 지정
        msg.what=0;

        //메시지에 객체 데이터 저장
        msg.obj=str;

        //Handler에 메시지 전달
        m.getH().sendMessage(msg);

        cnt++;
    }

}
