package com.example.addrbook.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.example.addrbook.sms.vo.SMS;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();

        Object messages[] = (Object[]) bundle.get("pdus");
        SmsMessage smsMessage[] = new SmsMessage[messages.length];
        for (int n = 0; n < messages.length; n++) {
            smsMessage[n] = SmsMessage.createFromPdu((byte[]) messages[n]);
        }


        Toast toast = Toast.makeText(context, "Received SMS: " + smsMessage[0].getMessageBody()+"\nfrom"+smsMessage[0].getOriginatingAddress(), Toast.LENGTH_LONG);
        toast.show();

        String msg = smsMessage[0].getMessageBody();
        String number = smsMessage[0].getOriginatingAddress();

        MainActivity.list.add(0, new SMS(number, msg));
        MainActivity.changeList();

    }
}
