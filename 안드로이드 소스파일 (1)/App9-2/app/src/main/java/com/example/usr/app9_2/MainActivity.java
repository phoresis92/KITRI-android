package com.example.usr.app9_2;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText content;
    private EditText number;
    private String msg;
    private String numstr;
    private final static String SEND_ACTION = "SENT";
    private final static String DELIVER_ACTION = "DELIVERED";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content = (EditText) findViewById(R.id.editText);
        number = (EditText) findViewById(R.id.editText2);
        Intent intent = getIntent();
        String tel = intent.getStringExtra("tel");
        if(tel!="" && tel!=null){
            number.setText(tel);
        }
    }

    public void sendMsg(View view){
        // ����ڰ� �Է��� �޽����� ��ȭ��ȣ�� ����
        msg = content.getText().toString();
        numstr = number.getText().toString();

        // �޽����� ��ȭ��ȣ �� �ϳ��� �Է¹��� �������� �޼��� ����
        if (msg.length() <= 0 || numstr.length() <= 0) {

            Toast.makeText(getApplicationContext(),
                    "input the message and phone number!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // �޽��� ������ �����ϰų� �����ϸ� ��ε�ĳ��Ʈ �Ǵ� PendingIntent
        PendingIntent sendStat = PendingIntent.getBroadcast(this, 0,	new Intent("SENT"), 0);

        // �����ڰ� �޽����� ������ ��ε�ĳ��Ʈ �Ǵ� PendingIntent
        PendingIntent deliveredStat = PendingIntent.getBroadcast(this, 0,
                new Intent("DELIVERED"), 0);

        // �⺻ SmsManager ��ü ȹ��
        SmsManager sms = SmsManager.getDefault();

        // �޽��� ����
        sms.sendTextMessage(numstr, null, msg, sendStat, deliveredStat);

    }

}
