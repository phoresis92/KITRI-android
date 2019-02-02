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

        //������ ��ü ����
        m = new MyThread(getApplication());

        //thread ����
        m.start();

    }

    public void getMsg(View v) {

        //Ǯ���� Message ��ü ��ȯ
        Message msg= Message.obtain();

        String str="main message"+cnt;

        //�޽��� ���� ����
        msg.what=0;

        //�޽����� ��ü ������ ����
        msg.obj=str;

        //Handler�� �޽��� ����
        m.getH().sendMessage(msg);

        cnt++;
    }

}
