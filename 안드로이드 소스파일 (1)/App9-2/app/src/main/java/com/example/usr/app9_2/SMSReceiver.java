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
        // Intent�� extra������ Bundle ��ü�� ����
        Bundle bundle = intent.getExtras();

        String msg;
        String sender;
        int i;

        if (bundle != null) {

            // Ű�� "pdus"�� �����͸� Bundle���� ���� �迭�� ����
            Object rawData[] = (Object[]) bundle.get("pdus");

            SmsMessage[] sms = new SmsMessage[rawData.length];
            for (i = 0; i < rawData.length; i++) {

                // raw �����ͷ� �Ǿ��ִ� ���� �޽��� ������ SmsMessage��ü�� ��ȯ��
                //�� �迭�� ����
                sms[i] = SmsMessage.createFromPdu((byte[]) rawData[i]);
            }
            for (i = 0; i < sms.length; i++) {

                // SmsMessage ��ü���� ���� �޽��� �ؽ�Ʈ ���� ����
                msg = sms[i].getMessageBody();

                // SmsMessage ��ü���� ���� ����� ��ȣ ���� ����
                sender = sms[i].getOriginatingAddress();

                // ������ ������ 2 ������ �佺Ʈ�� ���
                Toast.makeText(context, msg + " from " + sender,
                        Toast.LENGTH_SHORT).show();
            }
        }

    }
}
