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

            // �޽����� ���޹����� ȣ��
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                // Message Ŭ������ ��� ���� what ���� �����ΰ��� ���� ó��
                // what�� �޽����� ������ �����ϴµ� ���
                if (msg.what == 0) {

                    // �����尡 ������ �޽����� obj ������� �佺Ʈ�� ���
                    // obj���� ������ ������ �����Ͱ� ����Ǿ� �ִ�.
                    Toast.makeText(getApplicationContext(), "server message : " +
                                    (String) msg.obj,
                            Toast.LENGTH_SHORT).show();
                }
            }
        };

        // ������ ��ü ����
        m = new MyClient(myHandler);

        // ������ ����
        m.start();
    }
    // ��ư Ŭ���� ����� �̺�Ʈ ó��
    public void send(View view) {

        // �޽��� ��ü ����
        Message msg = new Message();

        // �޽��� ���� ����
        msg.what = 0;

        // ����Ʈ �ؽ�Ʈ�� �Է��� ���ڿ��� �޽����� obj ����� ����
        msg.obj = et.getText().toString();
        // �������� �ڵ鷯�� �޽��� ����
        m.getMyHandler().sendMessage(msg);
    }
}
