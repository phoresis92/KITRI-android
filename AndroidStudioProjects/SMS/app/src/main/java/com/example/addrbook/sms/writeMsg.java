package com.example.addrbook.sms;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class writeMsg extends AppCompatActivity {

    private EditText number;
    private EditText msg;
    private final static String SENT = "ACTION_SENT";
    private final static String DELIVERED = "ACTION_DELIVERED";

    private SendReceiver sr;
    private DeliveredReceiver dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_msg);
        //EditText
        number = (EditText) findViewById(R.id.number);
        msg = (EditText) findViewById(R.id.msg);

        //Intent value
        Intent intent = getIntent();
        String num = intent.getStringExtra("number");
        if(num != null && !num.equals("")){
            number.setText(num);
            Toast.makeText(this, num, Toast.LENGTH_SHORT).show();
        }

        sr = new SendReceiver();
        dr = new DeliveredReceiver();
        registerReceiver(sr, new IntentFilter(SENT));
        registerReceiver(dr, new IntentFilter(DELIVERED));

    }

    public void onSend(View v){
        String n = number.getText().toString();
        String m = msg.getText().toString();

        if(n.equals("")||m.equals("")){
            Toast.makeText(this, "번호, 내용 모두 입력해 주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        //문자메세지 관리자를 생성
        SmsManager sm = SmsManager.getDefault();

        //메세지 전송이 성공하거나 실패하면 브로드캐스트 되는 PendingIntent
        PendingIntent sendStat = PendingIntent.getBroadcast(this,0, new Intent(SENT), 0);

        //수신자가 메세지를 받으면 브로드캐스트 되는 PendingIntent
        PendingIntent deliveredStat = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);

        sm.sendTextMessage(n, null, m, sendStat, deliveredStat);

        finish();

    }
}
