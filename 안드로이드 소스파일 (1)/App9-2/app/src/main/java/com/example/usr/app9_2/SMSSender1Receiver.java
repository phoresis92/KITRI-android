package com.example.usr.app9_2;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

public class SMSSender1Receiver extends BroadcastReceiver {
    public SMSSender1Receiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // sms ���۰���� �佺Ʈ�� ���
        int result = getResultCode();
        switch (result) {
            case Activity.RESULT_OK:
                Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
                break;
            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                Toast.makeText(context, "generic failure", Toast.LENGTH_SHORT).show();
                break;
            case SmsManager.RESULT_ERROR_RADIO_OFF:
                Toast.makeText(context, "radio off", Toast.LENGTH_SHORT).show();
                break;
            case SmsManager.RESULT_ERROR_NULL_PDU:
                Toast.makeText(context, "null pdu", Toast.LENGTH_SHORT).show();
                break;
            case SmsManager.RESULT_ERROR_NO_SERVICE:
                Toast.makeText(context, "no service", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
