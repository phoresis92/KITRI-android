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

    //send��ư Ŭ�� �� ȣ��
    public void send(View view){
        //MyReceiver�� ��������� Ȱ��ȭ�� ����Ʈ
        Intent intent = new Intent(this, MyReceiver.class);

        //����Ʈ�� �߰� ���� ����
        intent.putExtra("name", name.getText().toString());

        //��ε�ĳ��Ʈ ���ù�
        sendBroadcast(intent);
        name.setText("");
    }
}
