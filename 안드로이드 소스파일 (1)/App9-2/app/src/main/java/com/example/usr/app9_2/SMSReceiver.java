package com.example.usr.app9_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // Intent의 extra정보를 Bundle 객체에 저장
        Bundle bundle = intent.getExtras();

        String msg;
        String sender;
        int i;

        if (bundle != null) {

            // 키가 "pdus"인 데이터를 Bundle에서 꺼내 배열에 저장
            Object rawData[] = (Object[]) bundle.get("pdus");

            SmsMessage[] sms = new SmsMessage[rawData.length];
            for (i = 0; i < rawData.length; i++) {

                // raw 데이터로 되어있는 문자 메시지 정보를 SmsMessage객체로 변환하
                //여 배열에 저장
                sms[i] = SmsMessage.createFromPdu((byte[]) rawData[i]);
            }
            for (i = 0; i < sms.length; i++) {

                // SmsMessage 객체에서 문자 메시지 텍스트 꺼내 저장
                msg = sms[i].getMessageBody();

                // SmsMessage 객체에서 보낸 사람의 번호 꺼내 저장
                sender = sms[i].getOriginatingAddress();

                // 위에서 추출한 2 정보를 토스트로 출력
                Toast.makeText(context, msg + " from " + sender,
                        Toast.LENGTH_SHORT).show();
            }
        }

    }
}
