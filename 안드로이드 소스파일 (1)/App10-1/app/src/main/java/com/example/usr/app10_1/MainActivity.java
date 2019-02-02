package com.example.usr.app10_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //start��ư�� Ŭ�� �� ȣ��
    public void start(View view){
        //���� Ȱ��ȭ�� ����� ����Ʈ ����
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        //���� ����
        startService(intent);

    }

    //stop��ư�� Ŭ�� �� ȣ��
    public void stop(View view){
        //���� ���ῡ ����� ����Ʈ ����
        Intent intent = new Intent(getApplicationContext(), MyService.class);
        //���� ����
        stopService(intent);

    }
}
