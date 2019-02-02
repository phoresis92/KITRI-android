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
        // 사용자가 입력한 메시지와 전화번호를 읽음
        msg = content.getText().toString();
        numstr = number.getText().toString();

        // 메시지와 전화번호 중 하나라도 입력받지 못했으면 메서드 종료
        if (msg.length() <= 0 || numstr.length() <= 0) {

            Toast.makeText(getApplicationContext(),
                    "input the message and phone number!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        // 메시지 전송이 성공하거나 실패하면 브로드캐스트 되는 PendingIntent
        PendingIntent sendStat = PendingIntent.getBroadcast(this, 0,	new Intent("SENT"), 0);

        // 수신자가 메시지를 받으면 브로드캐스트 되는 PendingIntent
        PendingIntent deliveredStat = PendingIntent.getBroadcast(this, 0,
                new Intent("DELIVERED"), 0);

        // 기본 SmsManager 객체 획득
        SmsManager sms = SmsManager.getDefault();

        // 메시지 전송
        sms.sendTextMessage(numstr, null, msg, sendStat, deliveredStat);

    }

}
